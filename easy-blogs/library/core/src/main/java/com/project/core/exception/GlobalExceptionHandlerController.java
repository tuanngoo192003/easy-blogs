package com.project.core.exception;

import com.project.core.viewmodel.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandlerController {

    protected ApiResponse<Map<String, Object>> response;

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<Map<String, Object>> handleForbiddenException(HttpServletRequest req, AccessDeniedException ex) {
        response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SystemBadRequestException.class)
    public ApiResponse<Map<String, Object>> handleBadRequestException(HttpServletRequest req, SystemBadRequestException ex) {
        response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(HttpServletRequest req,
                                                                     MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", System.currentTimeMillis());
        errors.put("status", false);
        errors.put("errors", getMessageArgumentNotValidMap(ex));
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        errors.put("data", data);
        return errors;
    }

    private static Map<String, String> getMessageArgumentNotValidMap(MethodArgumentNotValidException ex) {
        Map<String, String> details = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            if (details.put(fieldError.getField(), fieldError.getDefaultMessage()) != null) {
                throw new IllegalStateException("Duplicate key");
            }
        }
        ObjectError globalError = ex.getBindingResult().getGlobalError();
        if (globalError != null && (details.put(globalError.getObjectName(), globalError.getDefaultMessage()) != null))
            throw new IllegalStateException("Duplicate key");
        return details;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({SystemNotFoundException.class})
    public ApiResponse<Map<String, Object>> handleNotFoundException(HttpServletRequest req, Exception ex) {
        response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Map<String, Object>> handleException(HttpServletRequest req, Exception ex) {
        response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SystemValidatorException.class)
    public Map<String, Object> handleSystemValidatorException(HttpServletRequest req, SystemValidatorException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", System.currentTimeMillis());
        errors.put("status", false);
        errors.put("errors", getMessageSystemValidatorMap(ex));
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        errors.put("data", data);
        return errors;
    }

    private static Map<String, String> getMessageSystemValidatorMap(SystemValidatorException ex) {
        Map<String, String> details = new HashMap<>();
        JSONObject jsonObject = new JSONObject(ex.getMessage());
        for(String key : jsonObject.keySet()){
            details.put(key, jsonObject.getString(key));
        }
        return details;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SystemUnAuthorizationException.class)
    public ApiResponse<Map<String, Object>> handleUnAuthorizationException(HttpServletRequest req, SystemUnAuthorizationException ex) {
        response = new ApiResponse<>(false, ex.getMessage());
        Map<String, Object> data = new HashMap<>();
        data.put("path", req.getRequestURI());
        response.setData(data);
        return response;
    }
}
