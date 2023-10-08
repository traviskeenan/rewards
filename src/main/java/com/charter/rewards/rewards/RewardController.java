package com.charter.rewards.rewards;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
class RewardController {
    
    private RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{total}")
    public RewardResponse calulatePoints(@PathVariable Double total) {
        int calulatePoints = rewardService.calculatePoints(total);

        return new RewardResponse(calulatePoints);
    }
}
