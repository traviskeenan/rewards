package com.charter.rewards.rewards;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
class RewardService {
    private static final int SINGLE_POINT_THRESHOLD = 50;
    private static final int DOUBLE_POINT_THRESHOLD = 100;

    Function<Integer, Integer> singlePointsFunc = t -> t <= DOUBLE_POINT_THRESHOLD ? t - SINGLE_POINT_THRESHOLD : SINGLE_POINT_THRESHOLD;
    Function<Integer, Integer> doublePointsFunc = t -> t > DOUBLE_POINT_THRESHOLD ? (t - DOUBLE_POINT_THRESHOLD) * 2 : 0;

    /**
     * Calculates the reward points for a gien total.
     * Returns 2 points for amounts over 100 and 1 point for amounts between 50 and
     * 100.
     * (e.g. total: 120 -- 2 x 20 + 1 x 50 = 90 reward points).
     * 
     * @param total dollar amount
     * @return number of reward points for a given total
     */
    public int calculatePoints(Double total) {
        int intVal = total.intValue();

        if (intVal <= SINGLE_POINT_THRESHOLD) {
            return 0;
        }

        List<Function<Integer, Integer>> pointCalulators = List.of(singlePointsFunc, doublePointsFunc);

        // Apply all rules and add their results
        return pointCalulators.stream().map(pc -> pc.apply(intVal)).reduce(0, Integer::sum);
    }
}
