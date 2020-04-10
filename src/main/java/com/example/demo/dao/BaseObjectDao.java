package com.example.demo.dao;

import com.example.demo.common.stream.MyObjectOutputStream;
import com.example.demo.model.base.BaseModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class BaseObjectDao<T> extends BaseFileDao {
    private List<T> datas = null;

    protected List<T> readObject() {
        File file = getFile();
        datas = new ArrayList<>();
        try (FileInputStream fio = new FileInputStream(file); ObjectInputStream input = new ObjectInputStream(fio)) {
            Object o = null;
            while (fio.available() > 0 && (o = input.readObject()) != null) {
                datas.add((T) o);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return datas;
    }

    protected T save(T t) {
        File file = super.getFile();
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            if (file.length() > 0) {
                try (ObjectOutputStream out = new MyObjectOutputStream(fos)) {
                    out.writeObject(t);
                    out.flush();
                }
            } else {
                try (ObjectOutputStream out = new ObjectOutputStream(fos)) {
                    out.writeObject(t);
                    out.flush();
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        if (datas != null) {
            datas.add(t);
        }
        return t;
    }

    protected T getById_(String id) {
        if (datas == null) {
            return null;
        }
        Optional<T> optionalT = datas.stream().filter(p -> {
            //TODO
            if (p instanceof BaseModel) {
                return id.equals(((BaseModel) p).getId());
            } else {
                return false;
            }
        }).findFirst();
        if (optionalT.isPresent()) {
            return optionalT.get();
        }
        return null;
    }
}
