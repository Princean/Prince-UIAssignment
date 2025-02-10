package com.infosys.purchase.rewardpoints.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RewardPointsUtilTest {

    private RewardPointsUtil rewardPointsUtil;

    @BeforeEach
    public void setUp() {
        rewardPointsUtil = new RewardPointsUtil();
    }

    @Test
    public void calculateRewardPointsForAmountLessThanSlab1() {
        double amount = 30.0;
        int expectedPoints = 0;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void calculateRewardPointsForAmountEqualToSlab1() {
        double amount = 50.0;
        int expectedPoints = 0;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void calculateRewardPointsForAmountBetweenSlab1AndSlab2() {
        double amount = 75.0;
        int expectedPoints = 25;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void calculateRewardPointsForAmountEqualToSlab2() {
        double amount = 100.0;
        int expectedPoints = 50;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void calculateRewardPointsForAmountGreaterThanSlab2() {
        double amount = 150.0;
        int expectedPoints = 150;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void calculateRewardPointsForNegativeAmount() {
        double amount = -50.0;
        int expectedPoints = 0;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void calculateRewardPointsForZeroAmount() {
        double amount = 0.0;
        int expectedPoints = 0;
        int actualPoints = rewardPointsUtil.calculateRewardPoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }
}
