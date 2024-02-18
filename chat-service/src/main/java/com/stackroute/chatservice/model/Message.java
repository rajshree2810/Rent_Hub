package com.stackroute.chatservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String senderEmail;
    private Date time = new Date(System.currentTimeMillis());
    private String replymessage;
//    public String getFormattedTime() {
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // IST timezone
//        return sdf.format(time);
//    }
//    public Message() {
//    }
//
//    public Message(String senderEmail, Date time, String replymessage) {
//        this.senderEmail = senderEmail;
//        this.time = time;
//        this.replymessage = replymessage;
//    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReplymessage() {
        return replymessage;
    }

    public void setReplymessage(String replymessage) {
        this.replymessage = replymessage;
    }

}
