package com.example.bankdddspeedtraining;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankDddSpeedTrainingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void open_a_bank_account() throws Exception {
        //language=JSON
        String createAccountBody = "{\n" +
                "  \"clientId\": \"1234\"\n" +
                "}";
        mockMvc.perform(post("/accounts")
                .content(createAccountBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
