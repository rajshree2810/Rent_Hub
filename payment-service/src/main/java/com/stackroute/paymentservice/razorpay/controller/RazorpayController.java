package com.stackroute.paymentservice.razorpay.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentservice.model.ObjectSendToProdectHistory;
import com.stackroute.paymentservice.model.OrderSuccesDetail;
import com.stackroute.paymentservice.repository.PaymentRepository;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/ps")
public class RazorpayController {
    @Autowired
    PaymentRepository repo;

    private RazorpayClient client;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private static final String SECRET_ID1 = "rzp_test_MHJ49Uf8gqkxlQ";
    private static final String SECRET_KEY1 = "X9I6Qx0HyDWe3eLTTeYPqEkY";
    private static final String SECRET_ID2 = "rzp_test_XZZchgGAclYnQH";
    private static final String SECRET_KEY2 = "Yyb1RHIMYZg4jo5WgEu86o38";
    OrderSuccesDetail orderSuccesDetail=new OrderSuccesDetail();

    @RequestMapping(path = "/createOrder", method = RequestMethod.POST)
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = new OrderResponse();
        try {

            if (orderRequest.getAmount().intValue() > 1000) {
                client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
            } else {
                client = new RazorpayClient(SECRET_ID2, SECRET_KEY2);
            }

            Order order = createRazorPayOrder(orderRequest.getAmount());
            System.out.println("---------------------------");
            String orderId = order.get("id");
            System.out.println("Order ID: " + orderId);
            System.out.println("---------------------------");
            response.setRazorpayOrderId(orderId);
            response.setApplicationFee("" + orderRequest.getAmount());
            if (orderRequest.getAmount().intValue() > 1000) {
                response.setSecretKey(SECRET_KEY1);
                response.setSecretId(SECRET_ID1);
                response.setPsName("razor1");
            } else {
                response.setSecretKey(SECRET_KEY2);
                response.setSecretId(SECRET_ID2);
                response.setPsName("razor2");
            }




            System.out.println(orderRequest.getProductId());
            System.out.println(orderRequest.getCustomerId());
            System.out.println(response.getRazorpayOrderId());
            orderSuccesDetail.setCustomerId(orderRequest.getCustomerId());
            orderSuccesDetail.setProductId(orderRequest.getProductId());
            orderSuccesDetail.setPaymentId(response.getRazorpayOrderId());

            return repo.save(response);
        } catch (RazorpayException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return repo.save(response);

    }

    private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(new BigInteger("100")));
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
        return client.orders.create(options);
    }

    @GetMapping("update")
    private void updateDetails(){
        ObjectSendToProdectHistory prodectHistory=new ObjectSendToProdectHistory();
        for (int i=0;i<orderSuccesDetail.getProductId().length;i++){
            System.out.println(orderSuccesDetail.getProductId()[i]);
            System.out.println(orderSuccesDetail.getCustomerId());
            System.out.println(orderSuccesDetail.getPaymentId());
            prodectHistory.setCustomerId(orderSuccesDetail.getCustomerId());
            prodectHistory.setProductId(orderSuccesDetail.getProductId()[i]);
            prodectHistory.setPaymentId(orderSuccesDetail.getPaymentId());
            rabbitTemplate.convertAndSend(exchange,routingKey,prodectHistory);
        }
    }
}
