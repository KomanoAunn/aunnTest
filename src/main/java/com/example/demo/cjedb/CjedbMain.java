package com.example.demo.cjedb;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pangxiong
 * @title: CjedbMain
 * @projectName aunnTest
 * @description: TODO
 * @date 2022/7/2610:16
 */
public class CjedbMain {
    public static void main(String[] args) throws IOException {
        Events events = parse();
        events.getEvents().addAll(getNewEventList());
        System.out.println(JSON.toJSONString(events, JSONWriter.Feature.PrettyFormat,JSONWriter.Feature.MapSortField));
    }

    private static Events parse() throws IOException {
        InputStreamReader read = new InputStreamReader(
                new FileInputStream("D:\\workspace12\\aunnTest\\src\\main\\java\\com\\example\\demo\\cjedb\\cjedb" +
                        ".json"));
        BufferedReader bufferedReader = new BufferedReader(read);
        StringBuilder sb = new StringBuilder();
        String lineTxt;
        while ((lineTxt = bufferedReader.readLine()) != null && !StringUtils.isEmpty(lineTxt)) {
            sb.append(lineTxt);
        }
        Events events = JSON.parseObject(sb.toString(), Events.class);
        //System.out.println(JSON.toJSONString(events, JSONWriter.Feature.PrettyFormat,JSONWriter.Feature.MapSortField));
        return events;
    }

    private static List<Event> getNewEventList() {
        //1
        List<Event> list = new ArrayList<>();
        Event event = new Event();
        Choice choice = new Choice();
        choice.setTitle("");
        choice.setText("");
        event.setStoryId(1L);
        event.setChoices(Arrays.asList(choice));
        list.add(event);
        //2
        return list;
    }

    private void downJs() throws IOException {
        String filePath = "https://gamewith-tool.s3-ap-northeast-1.amazonaws.com/uma-musume/male_event_datas.js";
        URL url = new URL(filePath);
        URLConnection urlConnection = url.openConnection();
        InputStream inStream = urlConnection.getInputStream();
        FileOutputStream fs = new FileOutputStream("cjedb.txt");

        byte[] buffer = new byte[1204];
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = inStream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
        }
        fs.flush();
    }
}
