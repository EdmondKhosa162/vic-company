package za.co.vic.VIC_Company.response;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ResponseHandler {
	
    private String message;
    private HttpStatus status;

    public ResponseHandler(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object respoObject) {
        Map<String, Object> response = new LinkedHashMap<>();  // Use LinkedHashMap to maintain the order

        response.put("message", message);  // Message at the top
        response.put("status", httpStatus);  // Status follows the message
        response.put("data", respoObject);  // Data comes last

        return new ResponseEntity<>(response, httpStatus);
    }


}
