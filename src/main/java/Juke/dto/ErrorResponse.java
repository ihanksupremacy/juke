package Juke.dto;

public class ErrorResponse {
    private boolean success = false;
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
