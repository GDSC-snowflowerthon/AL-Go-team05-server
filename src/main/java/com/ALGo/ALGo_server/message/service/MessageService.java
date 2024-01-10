package com.ALGo.ALGo_server.message.service;

import com.ALGo.ALGo_server.entity.User;
import com.ALGo.ALGo_server.message.dto.MessageResponse;
import com.ALGo.ALGo_server.papago.Service.NaverTransService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("${message-secret}")
    private String secretKey;

    private final NaverTransService naverTransService;

    public MessageResponse message(User user) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("https://www.safetydata.go.kr/openApi");

        urlBuilder.append("/" + URLEncoder.encode("행정안전부_긴급재난문자","UTF-8"));
        urlBuilder.append("?serviceKey=" + secretKey);
        urlBuilder.append("&returnType=json");
        urlBuilder.append("&pageNum=1");
        urlBuilder.append("&numRowsPerPage=1");

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());


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
        String responseBody = sb.toString();
//        System.out.println(responseBody);

        //파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

        JSONObject responseData = (JSONObject) jsonObject.get("responseData");
        JSONArray data = (JSONArray) responseData.get("data");

        JSONObject dataObject = (JSONObject) data.get(0);

        String CREAT_DT = dataObject.get("CREAT_DT").toString();
        String DSSTR_SE_ID = dataObject.get("DSSTR_SE_ID").toString();
        String DSSTR_SE_NM = dataObject.get("DSSTR_SE_NM").toString();
        String EMRGNCY_STEP_ID = dataObject.get("EMRGNCY_STEP_ID").toString();
        String MSG_CN = dataObject.get("MSG_CN").toString();
        String RCV_AREA_ID = dataObject.get("RCV_AREA_ID").toString();
        String RCV_AREA_NM = dataObject.get("RCV_AREA_NM").toString();

        List<String> areaIdArr = Arrays.stream(RCV_AREA_ID.split(",")).toList();
        for(int i=0;i<areaIdArr.size();i++){
            String a = areaIdArr.get(i);
        }

        List<String> areaNmArr = Arrays.stream(RCV_AREA_NM.split(",")).toList();
        for(int i=0;i<areaNmArr.size();i++){
            String a = areaNmArr.get(i);
        }


        String translatedMSG = naverTransService.getTransSentence(MSG_CN, user);
        MessageResponse response = new MessageResponse(translatedMSG, CREAT_DT, areaIdArr, areaNmArr, EMRGNCY_STEP_ID, DSSTR_SE_ID, DSSTR_SE_NM);
        return response;
    }
}
