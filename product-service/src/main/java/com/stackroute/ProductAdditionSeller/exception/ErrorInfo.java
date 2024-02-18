package com.stackroute.ProductAdditionSeller.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime time;

    public void setErrorMessage(String property) {
    }

    public void setErrorCode(String s) {
    }

    public void setTime(LocalDateTime now) {
    }
}
