package com.infosys.purchase.rewardpoints.controller;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import java.util.Locale;
import java.util.List;
import com.infosys.purchase.rewardpoints.request.RewardPointsRequest;
import com.infosys.purchase.rewardpoints.response.RewardPointsResponse;
import com.infosys.purchase.rewardpoints.service.RewardPointsService;
import com.infosys.purchase.rewardpoints.util.ResponseUtil;

public class RewardPointsControllerTest {

    private RewardPointsController rewardPointsController;
    private RewardPointsService rewardPointsService;
    private ResponseUtil responseUtil;
    private MessageSource messageSource;

    @BeforeEach
    public void setUp() {
        rewardPointsService = mock(RewardPointsService.class);
        responseUtil = mock(ResponseUtil.class);
        messageSource = mock(MessageSource.class);
        rewardPointsController = new RewardPointsController();
        rewardPointsController.rewardPointsService = rewardPointsService;
        rewardPointsController.responseUtil = responseUtil;
        rewardPointsController.messageSource = messageSource;
    }

    @Test
    public void getRewardPointsSuccessfully() {
        RewardPointsRequest request = new RewardPointsRequest();
        List<RewardPointsResponse> response = List.of(new RewardPointsResponse(101, 2, 2025,150));
        when(rewardPointsService.get(request)).thenReturn(response);
        when(responseUtil.createResponse(null, response)).thenAnswer(invocation -> ResponseEntity.ok(response));

        ResponseEntity<?> result = rewardPointsController.get(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(rewardPointsService).get(request);
    }
    
    @Test
    public void getRewardPointsSuccess() {
        RewardPointsRequest request = new RewardPointsRequest();
        List<RewardPointsResponse> response = List.of(new RewardPointsResponse(101, 2, 2025,Long.valueOf(227)));
        when(rewardPointsService.get(request)).thenReturn(response);
        when(responseUtil.createResponse(null, response)).thenAnswer(invocation -> ResponseEntity.ok(response));

        ResponseEntity<?> result = rewardPointsController.get(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(rewardPointsService).get(request);
    }

    @Test
    public void getRewardPointsFails() {
        RewardPointsRequest request = new RewardPointsRequest();
        when(rewardPointsService.get(request)).thenThrow(new RuntimeException());
        when(messageSource.getMessage("rewardpoints.get.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> result = rewardPointsController.get(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), result);
        verify(rewardPointsService).get(request);
    }

    @Test
    public void getAllRewardPointsSuccessfully() {
        RewardPointsRequest request = new RewardPointsRequest();
        List<RewardPointsResponse> response = List.of(new RewardPointsResponse(101, 2, 2025,150));
        when(rewardPointsService.getAll(request)).thenReturn(response);
        when(responseUtil.createResponse(null, response)).thenAnswer(invocation -> ResponseEntity.ok(response));

        ResponseEntity<?> result = rewardPointsController.getAll(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(rewardPointsService).getAll(request);
    }

    @Test
    public void getAllRewardPointsFails() {
        RewardPointsRequest request = new RewardPointsRequest();
        when(rewardPointsService.getAll(request)).thenThrow(new RuntimeException());
        when(messageSource.getMessage("rewardpoints.get.failed", null, Locale.getDefault())).thenReturn("Failed");
        when(responseUtil.createErrorResponse("Failed", null)).thenAnswer(invocation -> ResponseEntity.status(500).body("Failed"));

        ResponseEntity<?> result = rewardPointsController.getAll(request);

        assertEquals(ResponseEntity.status(500).body("Failed"), result);
        verify(rewardPointsService).getAll(request);
    }
}

