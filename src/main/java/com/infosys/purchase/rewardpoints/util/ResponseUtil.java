package com.infosys.purchase.rewardpoints.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.infosys.purchase.rewardpoints.response.Response;

@Component
public class ResponseUtil {

	@Autowired
	MessageSource messageSource;
	
	/**
     * Creates a success response with the given message and object.
     *
     * @param message the message to be included in the response
     * @param object the object to be included in the response
     * @return a ResponseEntity containing the success status, message, and object
     */
    public ResponseEntity<?> createResponse(String message,Object object) {
        return ResponseEntity
        		.ok()
        		.body(new Response(messageSource.getMessage("status.success", null, Locale.getDefault()),
        				message,
        				object));
    }

    /**
     * Creates an error response with the given message and object.
     *
     * @param message the message to be included in the response
     * @param object the object to be included in the response
     * @return a ResponseEntity containing the error status, message, and object
     */
    public ResponseEntity<?> createErrorResponse(String message, Object object) {
        return ResponseEntity
        		.status(500)
        		.body(new Response(messageSource.getMessage("status.failed", null, Locale.getDefault()),
        				message,
        				object));
    }
}