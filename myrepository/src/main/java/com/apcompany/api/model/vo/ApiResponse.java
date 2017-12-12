package com.apcompany.api.model.vo;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
   private T data;
   private String message;
   private boolean success;

    public static <T> ApiResponse<T> buildSuccess(T date) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setData(date);
        response.setSuccess(true);
        return response;
    }

    public static <T> ApiResponse<T> buildSuccess(String message) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(true);
        response.setMessage(message);
        return response;
    }

    public static <T> ApiResponse<T> buildSuccess(T data,String message) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static <T> ApiResponse<T> buildSuccess() {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(true);
        return response;
    }

    public static <T> ApiResponse<T> buildFailure() {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(false);
        return response;
    }

    public static <T> ApiResponse<T> buildFailure(String errorMsg) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(false);
        response.setMessage(errorMsg);
        return response;
    }

    public static <T> ApiResponse<T> buildFailure(int errorCode, String errorMsg) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(false);
        response.setMessage(errorMsg);
        return response;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
