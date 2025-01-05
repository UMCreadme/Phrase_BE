package com.example.phrasebe.test;

import com.example.phrasebe.common.exception.GeneralException;
import com.example.phrasebe.common.status.ErrorStatus;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String testData() {
        return "테스트 성공!";
    }

    public void testError() {
        throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
    }
}
