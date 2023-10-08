package com.charter.rewards.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RewardServiceTest {
    static RewardService rewards;

    @BeforeAll
    public static void init() {
        rewards = new RewardService();
    }

    @ParameterizedTest
    @MethodSource("rewardTests")
    void testCalulatePoints(double transactionTotal, int expected) {
        assertEquals(expected, rewards.calculatePoints(transactionTotal));
    }

    private static Stream<Arguments> rewardTests() {
        return Stream.of(
                Arguments.of(120, 90),
                Arguments.of(120.99, 90),
                Arguments.of(40, 0),
                Arguments.of(-40, 0),
                Arguments.of(40.50, 0),
                Arguments.of(49, 0),
                Arguments.of(49.60, 0),
                Arguments.of(51, 1),
                Arguments.of(51.99, 1),
                Arguments.of(61, 11),
                Arguments.of(61.00, 11),
                Arguments.of(100, 50),
                Arguments.of(101, 52),
                Arguments.of(200, 250),
                Arguments.of(250, 350),
                Arguments.of(250.57, 350));
    }
}
