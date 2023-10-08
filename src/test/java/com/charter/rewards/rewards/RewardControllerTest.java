package com.charter.rewards.rewards;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalulatePoints() throws Exception {
        RewardResponse exptected = new RewardResponse(350);

        when(rewardService.calculatePoints(250.57)).thenReturn(exptected.points());

        mockMvc.perform(get("/api/rewards/250.57"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exptected)));
    }
}
