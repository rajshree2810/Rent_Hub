package com.authentication.login.service;

import com.authentication.login.model.Credential;
import com.authentication.login.repository.CredentialRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CredentialsImp implements CredentialServiceDAO{
    @Autowired
    CredentialRepository repository;

    @Override
    public boolean validate(Credential credential) {
        Credential credential1 = repository.findCredentialByUserIdAndPassword(credential.getUserId(), credential.getPassword());
        if(credential1==null){
            return false;
        }else {
            return true;
        }
    }

    //stores credentials of a new user
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(Credential credential){
        Optional<Credential> optionalCredential = repository.findById(credential.getUserId());
        if (optionalCredential.isEmpty()){
            repository.save(credential);
        }else {
            repository.delete(optionalCredential.get());
            repository.save(credential);
        }
    }
}
