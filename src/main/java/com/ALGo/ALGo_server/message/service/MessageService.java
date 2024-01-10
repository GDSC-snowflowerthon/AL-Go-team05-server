package com.ALGo.ALGo_server.message.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class MessageService {

    @Value("${message-secret}")
    private String secretKey;
    public void message() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/openApi");

        urlBuilder.append("/" + URLEncoder.encode("행정안전부_긴급재난문자","UTF-8"));
        urlBuilder.append("?serviceKey=" + secretKey);
        urlBuilder.append("&returnType=json");
        urlBuilder.append("&pageNum=1");
        urlBuilder.append("&numRowsPerPage=2");

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());


        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        // HTTP 연결 닫기
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
