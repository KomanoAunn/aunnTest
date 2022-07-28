package com.example.demo.cjedb;

import com.alibaba.fastjson2.annotation.JSONType;
import lombok.Data;

import java.util.List;

/**
 * @author pangxiong
 * @title: Event
 * @projectName aunnTest
 * @description: TODO
 * @date 2022/7/2617:36
 */
@Data
@JSONType(orders = {"storyId","choices"})
public class Event {
    private Long storyId;
    private List<Choice> choices;
}
