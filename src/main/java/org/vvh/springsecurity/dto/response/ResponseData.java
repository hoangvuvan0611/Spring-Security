package org.vvh.springsecurity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class ResponseData<T> {
    private boolean success = true;
    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * Response data for API retrieve data successfully, For GET, POST only
     * @param status
     * @param message
     * @param data
     */
    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Response data for API retrieve data successfully, For GET, POST only or getting error
     * @param status
     * @param message
     */
    public ResponseData(int status, String message) {
        this.success = false;
        this.status = status;
        this.message = message;
    }
}
