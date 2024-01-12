package com.ALGo.ALGo_server.papago.Service;

import com.ALGo.ALGo_server.entity.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class NaverTransService {
    @Value("${papago-client-id}")
    private String clientId; //papago 애플리케이션 클라이언트 아이디값;

    @Value("${papago-client-secret}")
    private String clientSecret; //papago 애플리케이션 클라이언트 시크릿값;

    public String getTransSentence(String s, User user) throws ParseException {
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;

        try {
            text = URLEncoder.encode(s, "UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new RuntimeException("인코딩 실패", e);
        }

        //요청 헤더 구성하기
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text, user);
//        System.out.println("responseBody = " + responseBody);

        //응답 파싱하기
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

        JSONObject messageObj = (JSONObject) jsonObject.get("message");
        System.out.println("messageObj = " + messageObj);
        JSONObject resultObj = (JSONObject) messageObj.get("result");
        System.out.println("resultObj = " + resultObj);
        String transText = resultObj.get("translatedText").toString();

        return transText;

    }

    private String post(String apiUrl, Map<String, String> requestHeaders, String text, User user){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target="+user.getLanguage()+"&text=" + text; //나중에 목적 언어는 사용자 데이터로 바꾸기!! ko(원본언어) -> 목적언어

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header : requestHeaders.entrySet()){
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())){
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){ //정상 응답인 경우
                return readBody(con.getInputStream());
            }else{ //에러 응답인 경우
                return readBody(con.getErrorStream());
            }
        }catch (IOException e){
            throw new RuntimeException("API 요청과 응답 실패", e);
        }finally {
            con.disconnect();
        }

    }

    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        }catch (MalformedURLException e){
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        }catch (IOException e){
            throw new RuntimeException("연결에 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try(BufferedReader lineReader = new BufferedReader(streamReader)){
            StringBuilder responseBody = new StringBuilder();
            String line;

            while ((line = lineReader.readLine()) != null){ //읽어온 line이 null이 아니면
                responseBody.append(line);  //responseBody에 추가
            }

            return responseBody.toString();
        }catch (IOException e){
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
