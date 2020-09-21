package gasi.ewf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Response<T> {
	
	private int status;
    private HttpStatus httpStatus;
    private String message = "";
    private T data;
    private List<String> error = new ArrayList<>();
    
    public Response() {
    	
    }
    
	public Response(HttpStatus status, String message, T data, List<String> error) {
		super();
		this.httpStatus = status;
		this.status = this.httpStatus.value();
		this.message = message;
		this.data = data;
		this.error = error;
	}
	
	public Response(HttpStatus status, String message, T data, String error) {
        super();
        this.httpStatus = status;
        this.status = this.httpStatus.value();
        this.message = message;
        this.data = data;
        this.error = Arrays.asList(error);
    }
	
	@JsonIgnore
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public void setStatus(HttpStatus status) {
		this.httpStatus = status;
		this.status = this.httpStatus.value();
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}
    
}
