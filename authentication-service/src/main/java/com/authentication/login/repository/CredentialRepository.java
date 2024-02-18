package com.authentication.login.repository;

import com.authentication.login.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential,String> {
    public Credential findCredentialByUserIdAndPassword(String userId,String password);
}
