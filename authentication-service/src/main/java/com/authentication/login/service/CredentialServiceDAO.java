package com.authentication.login.service;

import com.authentication.login.model.Credential;

public interface CredentialServiceDAO {
    public boolean validate(Credential credential);
}
