package com.example.demo.cjedb;

import com.alibaba.fastjson2.annotation.JSONType;
import lombok.Data;

/**
 * @author pangxiong
 * @title: ChoicesItem
 * @projectName aunnTest
 * @description: TODO
 * @date 2022/7/2617:34
 */
@Data
@JSONType(orders = {"title","text"})
public class Choice {
    private String title;
    private String text;
}
