package com.andy.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Logged {
}