package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("testWord")
    private String testWord1;
    private String testWord2 = "testWord2";

    {
        testWord1 = testWord1 + "_OK";
        testWord2 = testWord2 + "_OK";
    }

    @PostConstruct
    public void init2() {
        testWord1 = testWord1 + "_PostConstruct";
        testWord2 = testWord2 + "_PostConstruct";
    }


    @GetMapping("/url")
    public String test1(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return "test1,requestURI=" + requestURI;
    }

    @GetMapping("/testWord")
    public String testWord() {
        return testWord1 + System.getProperty("line.separator") + testWord2;
    }


    @GetMapping("/date")
    public String dateTest(@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") Date receiveDate) {
        return String.valueOf(receiveDate.getTime());
    }

    @GetMapping("/error")
    public String testError() {
        System.out.println(1 / 0);
        return "";
    }
}
