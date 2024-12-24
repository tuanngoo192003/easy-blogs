package com.project.easyblogs.controller;

import com.project.core.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viewmodel.ApiResponse;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {

    @GetMapping("test")
    public ApiResponse<String> testApi(){
        return new ApiResponse<>(true, "testing");
    }
}
