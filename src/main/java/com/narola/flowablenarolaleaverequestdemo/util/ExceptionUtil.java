package com.narola.flowablenarolaleaverequestdemo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

    public static void throwBadRequestException(String message) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    public static ResponseStatusException notFoundException(String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

    public static void throwNotFoundException(String message) {
        throw notFoundException(message);
    }

}
