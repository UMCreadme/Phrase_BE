package com.example.phrasebe.test;

import com.example.phrasebe.common.status.ErrorStatus;
import com.example.phrasebe.common.status.SuccessStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("getData(): 데이터가 포함된 ApiResponse 객체가 Http Response body로 설정된다.")
    void getDataTest() throws Exception {
        String expectResult = "테스트 성공!";

        mockMvc.perform(MockMvcRequestBuilders.get("/data")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value(SuccessStatus._OK.getCode()))
                .andExpect(jsonPath("$.message").value(SuccessStatus._OK.getMessage()))
                .andExpect(jsonPath("$.result").value(expectResult));
    }

    @Test
    @DisplayName("errorTest(): GeneralException 발생 시 에러 응답이 반환된다.")
    void errorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/error")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.isSuccess").value(false))
                .andExpect(jsonPath("$.code").value(ErrorStatus.MEMBER_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorStatus.MEMBER_NOT_FOUND.getMessage()));
    }
}