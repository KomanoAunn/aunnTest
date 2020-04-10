package com.example.demo;

import java.util.UUID;

public class Test6 {
    public static void main(String[] args) {
        UUID uuid=UUID.randomUUID();
        for (int i=0;i<100;i++){
            System.out.println(UUID.randomUUID().getLeastSignificantBits());
            System.out.println(UUID.randomUUID().getMostSignificantBits());
        }
    }
}
