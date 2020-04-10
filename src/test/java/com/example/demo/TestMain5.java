package com.example.demo;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestMain5 {
    public static void main(String[] args) {
        StringWriter buffer = new StringWriter();
        try (PrintWriter pw = new PrintWriter(buffer)) {
            pw.println("Hello");
            pw.println(12345);
            pw.println(true);
        }
        //System.out.println(buffer.toString());
    }
}
