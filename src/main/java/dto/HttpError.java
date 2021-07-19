package dto;

public class HttpError {

    private String message;
    
    public HttpError() {}
    
    public HttpError(String message) {
    	this.message = message;
    }
    
    public String getMessage() {
    	return message;
    }
    public void setMessage(String message) {
    	this.message = message;
    }
}
