package com.example.phrasebe.apiPayload.exception.handler;

import com.example.phrasebe.apiPayload.code.BaseErrorCode;
import com.example.phrasebe.apiPayload.exception.GeneralException;

public class ExceptionHandler extends GeneralException {
    public ExceptionHandler(BaseErrorCode code) {
        super(code);
    }
}
