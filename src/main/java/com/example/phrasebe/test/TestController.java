package com.example.phrasebe.test;

import com.example.phrasebe.common.response.ApiResponse;
import com.example.phrasebe.common.status.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/data")
    public ResponseEntity<ApiResponse> getDataTest() {
        String result = testService.testData();
        return ApiResponse.onSuccess(SuccessStatus._OK, result);
    }

    @GetMapping("/error")
    public ResponseEntity<ApiResponse> errorTest() {
        testService.testError();
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}
