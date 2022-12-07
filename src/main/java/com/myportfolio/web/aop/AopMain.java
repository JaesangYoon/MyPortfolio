package com.myportfolio.web.aop;

import java.lang.reflect.Method;

public class AopMain { // Ch03-18
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.myportfolio.web.aop.MyClass"); // MyClass의 Class 객체를 가져온다
        Object obj = myClass.newInstance(); // Class 객체로부터 MyClass 객체를 생성한다

        for (Method m : myClass.getDeclaredMethods()) { // MyClass에 정의된 메서드를 배열로 가져온 다음 하나씩 꺼내서 반복문 돈다
            myAdvice.invoke(m, obj, null);;// 매개변수에 myclass에 정의된 메서드의 정보를 넘겨준다
        }

    }
}

class MyAdvice {
    void invoke(Method m, Object obj, Object... args) throws Exception {
        System.out.println("[before]{");
        m.invoke(obj, args); // aaa(), aaa2(), bbb() 호출 가능
        System.out.println("}[after]");

    }
}

class MyClass {
    void aaa() {
        System.out.println("aaa() is called");
    }
    void aaa2() {
        System.out.println("aaa2() is called");
    }
    void bbb() {
        System.out.println("bbb() is called");
    }
}