package com.example.demo.dao.base;

import com.example.demo.model.base.BaseModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class BaseJsonDao<T> extends BaseFileDao {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final ObjectMapper mapper = new ObjectMapper();
    protected volatile List<T> datas = null;
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

    protected List<T> query(Map<String, Object> queryMap) {
        if (datas == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        ArrayNode arrayNode = mapper.valueToTree(datas);
        Map<String, Object> newMap = queryMap.entrySet().stream().filter((e) -> e.getValue() != null).collect(Collectors.toMap(
                (e) -> (String) e.getKey(),
                (e) -> e.getValue()));
        for (int i = 0; i < arrayNode.size(); i++) {
            boolean isTarget = true;
            JsonNode jsonNode = arrayNode.get(i);
            for (String key : newMap.keySet()) {
                if (!jsonNode.get(key).asText().equals(newMap.get(key))) {
                    isTarget = false;
                    break;
                }
            }
            if (isTarget) {
                try {
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    String objJsonStr = jsonNode.toString();
                    Object o = mapper.readValue(objJsonStr, classType);
                    list.add((T) o);
                } catch (IOException e) {
                    logger.log(Level.INFO, e.getMessage());
                }

            }
        }
        return list;
    }
}
