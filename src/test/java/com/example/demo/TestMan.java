package com.example.demo;

import com.example.demo.model.User;
import net.minidev.json.JSONValue;

import java.io.*;

public class TestMan {
    private static final String tableName = "user";
    private static final String dbName = "user";
    private static final String allDbBasePackage = "db";
    private static final String schemaBasePackage = allDbBasePackage + "." + dbName;
    private static final String schemaBasePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + (schemaBasePackage.replace(".", File.separator) + File.separator);

    public static void main(String[] args) {
//        try {
//        createFile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        User user = new User();
        user.setCellphone("1800000000");
        user.setPassword("123123");

        User user2 = new User();
        user2.setCellphone("1800000001");
        user2.setPassword("333333");
        try {
            writeCover(user);
            //writeCover(user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeCover(Object obj) throws IOException, ClassNotFoundException {
        File file = new File("test.text");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(obj);
        out.writeObject(obj);
        FileInputStream fio = new FileInputStream(file);
        ObjectInputStream input = new ObjectInputStream(fio);
        Object o = null;
        while (fio.available() > 0 && (o = input.readObject()) != null) {
            if (o instanceof User) {
                System.out.println(JSONValue.toJSONString(o));
            }
        }
    }

    private static void writeAppend(Object obj) throws IOException, ClassNotFoundException {
        File file = new File("test.text");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(obj);
        FileInputStream fio = new FileInputStream(file);
        ObjectInputStream input = new ObjectInputStream(fio);
        Object o = null;
        while (input.available() > 0 && (o = input.readObject()) != null) {
            if (o instanceof User) {
                System.out.println(JSONValue.toJSONString(o));
            }
        }
    }

    private static void createFile() throws IOException {
        File file = new File(schemaBasePath + tableName);
        if (!new File(schemaBasePath).exists()) {
            new File(schemaBasePath).mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

}
