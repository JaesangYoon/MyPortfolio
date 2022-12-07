package com.myportfolio.web.aop;

import org.springframework.stereotype.Component;

@Component // Bean으로 등록
public class MyMath { // Target으로 사용할 클래스
    public int add(int a, int b) {
        int result = a + b;
        return result;
    }
    public int add(int a, int b, int c) {
        int result = a + b + c;
        return result;
    }
    public int multiply(int a, int b) {
        int result = a * b;
        return result;
    }
    public int divide(int a, int b) {
        int result = a / b;
        return result;
    }
}
