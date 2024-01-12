package com.ALGo.ALGo_server.message.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageResponse {
    private String MSG_CN;  //메세지 내용
    private String CREAT_DT;  //생성일
    private List<String> RCV_AREA_ID;  //수신지역 id
    private List<String> RCV_AREA_NM; //수신지역명
    private String EMRGNCY_STEP_ID;  //긴급단계 id
    private String DSSTR_SE_ID;  //재해구분 id
    private String DSSTR_SE_NM; //재해구분 명

    private String trans_MSG_CN; //문자 번역
    private String trans_AREA;   //지역명 번역
    private String trnas_DSSTR;  //재해구분명 번역



    public MessageResponse(String MSG_CN, String CREAT_DT, List<String> RCV_AREA_ID, List<String> RCV_AREA_NM, String EMRGNCY_STEP_ID, String DSSTR_SE_ID, String DSSTR_SE_NM){
        this.MSG_CN = MSG_CN;
        //this.trans_MSG_CN = trans_MSG_CN;
        this.CREAT_DT = CREAT_DT;
        this.RCV_AREA_ID = RCV_AREA_ID;
        this.RCV_AREA_NM = RCV_AREA_NM;
        this.EMRGNCY_STEP_ID = EMRGNCY_STEP_ID;
        this.DSSTR_SE_ID = DSSTR_SE_ID;
        this.DSSTR_SE_NM = DSSTR_SE_NM;
    }

}
