package com.infosys.purchase.rewardpoints.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import com.infosys.purchase.rewardpoints.events.CustomerTransactionEvent;
import com.infosys.purchase.rewardpoints.model.CustomerTransaction;
import com.infosys.purchase.rewardpoints.model.RewardPoints;
import com.infosys.purchase.rewardpoints.repo.RewardPointsRepo;
import com.infosys.purchase.rewardpoints.request.RewardPointsRequest;
import com.infosys.purchase.rewardpoints.response.RewardPointsResponse;
import com.infosys.purchase.rewardpoints.util.RewardPointsUtil;

public class RewardPointsServiceTest {

    @Mock
    private RewardPointsUtil rewardPointsUtil;

    @Mock
    private RewardPointsRepo rewardPointsRepo;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private RewardPointsService rewardPointsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void rewardPointSaveEventSuccessfully() {
        CustomerTransactionEvent event = mock(CustomerTransactionEvent.class);
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setCustomerId(1);
        transaction.setDate(LocalDate.now());
        when(event.getSource()).thenReturn(transaction);
        when(event.getAmount()).thenReturn(100.0);
        when(rewardPointsUtil.calculateRewardPoints(100.0)).thenReturn(10);

        rewardPointsService.rewardPointSaveEvent(event);

        verify(rewardPointsRepo, times(1)).save(any(RewardPoints.class));
    }

    @Test
    void rewardPointSaveEventWithException() {
        CustomerTransactionEvent event = mock(CustomerTransactionEvent.class);
        when(event.getSource()).thenThrow(new RuntimeException("Error"));

        Assertions.assertThrows(RuntimeException.class, () -> rewardPointsService.rewardPointSaveEvent(event));
    }

    @Test
    void getAllRewardPointsSuccessfully() {
        RewardPointsRequest request = new RewardPointsRequest();
        request.setMonth(1);
        LocalDate period = LocalDate.now().minusMonths(1);
        when(rewardPointsRepo.getRewardPoints(null, period)).thenReturn(List.of(new RewardPointsResponse(101, 12, 2024, 250)));

        List<RewardPointsResponse> response = rewardPointsService.getAll(request);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
    }
    
    @Test
    void getAllRewardPointsWithNullRequest() {
    	RewardPointsRequest request = null;

        Assertions.assertThrows(Exception.class, () -> rewardPointsService.getAll(request));
    }

    @Test
    void getRewardPointsSuccessfully() {
        RewardPointsRequest request = new RewardPointsRequest();
        request.setCustomerId(1);
        request.setMonth(1);
        LocalDate period = LocalDate.now().minusMonths(1);
        when(rewardPointsRepo.getRewardPoints(1, period)).thenReturn(List.of(new RewardPointsResponse(101, 12, 2024, 250)));

        List<RewardPointsResponse> response = rewardPointsService.get(request);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void getRewardPointsWithException() {
        RewardPointsRequest request = new RewardPointsRequest();
        request.setCustomerId(1);
        request.setMonth(1);
        LocalDate period = LocalDate.now().minusMonths(1);
        when(rewardPointsRepo.getRewardPoints(1, period)).thenThrow(new RuntimeException("Error"));

        Assertions.assertThrows(RuntimeException.class, () -> rewardPointsService.get(request));
    }
}

