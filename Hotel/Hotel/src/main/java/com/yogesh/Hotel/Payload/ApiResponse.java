package com.yogesh.Hotel.Payload;

import jakarta.persistence.Entity;
/*import lombok.*;*/
import org.springframework.http.HttpStatus;

/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder*/
public class ApiResponse {

    private String message;
    private HttpStatus status;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", success=" + success +
                '}';
    }

    public ApiResponse(String message, HttpStatus status, boolean success) {
        this.message = message;
        this.status = status;
        this.success = success;
    }

    public ApiResponse(){}
}
