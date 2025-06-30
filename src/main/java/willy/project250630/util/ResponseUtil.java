package willy.project250630.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUtil {
    public static ResponseEntity<Object> createdResponse(String message, Object data){
        Map<String, Object> body = Map.of(
                "status", HttpStatus.CREATED.value(),
                "message", message,
                "data", data
        );
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static ResponseEntity<Object> createdResponse(String message){
        Map<String, Object> body = Map.of(
                "status",HttpStatus.CREATED.value(),
                "message", message
        );
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static ResponseEntity<Object> okResponse(String message, Object data){
        Map<String,Object> body = Map.of(
                "status", HttpStatus.OK.value(),
                "message", message,
                "data", data
        );
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static ResponseEntity<Object> notFoundDataResponse(String message){
        Map<String,Object> body = Map.of(
                "status", HttpStatus.OK.value(),
                "message", message
        );
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
