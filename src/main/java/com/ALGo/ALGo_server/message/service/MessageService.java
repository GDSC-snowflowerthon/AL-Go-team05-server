package com.ALGo.ALGo_server.message.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@Service
public class MessageService {

    @Value("${message-key}")
    private String serviceKey;

    public void message() throws IOException{
        StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/openApi");

        urlBuilder.append("/" + URLEncoder.encode("행정안전부_긴급재난문자", "UTF-8"));
        urlBuilder.append("?serviceKey=" + serviceKey);
        urlBuilder.append("&returnType=json");
        urlBuilder.append("&pageNum=1");
        urlBuilder.append("&numRowsPerPage=2");

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        InputStream inputStream;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            inputStream = conn.getInputStream();
        } else {
            inputStream = conn.getErrorStream();
        }

        // API 응답을 문자열로 변환하여 출력
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
        }

        conn.disconnect();

    }

}
