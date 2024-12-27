package com.project.core.viewmodel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {
    private boolean status;
    private Object message;
    private T data;

    private static final String SUCCESS = "Success";
    private static final String FAILED = "Failed";

    public ApiResponse(boolean status) {
        this.status = status;
        if (this.status) {
            this.message = SUCCESS;
        } else {
            this.message = FAILED;
        }
    }

    public ApiResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(boolean status, T data) {
        this.status = status;
        if (this.status) {
            this.message = SUCCESS;
        } else {
            this.message = FAILED;
        }
        this.data = data;
    }
}
