package com.example.demo.dao.base;

import com.example.demo.model.base.BaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class BaseJsonDao<T> extends BaseFileDao {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final ObjectMapper mapper = new ObjectMapper();
    private List<T> datas = null;
    private Class classType;

    {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            //参数化类型
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            this.classType = (Class<T>) actualTypeArguments[0];
        } else {
            this.classType = (Class<T>) genericSuperclass;
        }
    }

    protected List<T> readObject() {


        File file = getFile();
        datas = new ArrayList<>();
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            br.readLine();
            String lineString = null;
            while ((lineString = br.readLine()) != null) {
                Object o = mapper.readValue(lineString, classType);
                datas.add((T) o);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return datas;
    }

    protected T save(T t) {
        File file = super.getFile();
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(mapper.writeValueAsString(t));
            fw.write(LINE_SEPARATOR);
            fw.flush();
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
