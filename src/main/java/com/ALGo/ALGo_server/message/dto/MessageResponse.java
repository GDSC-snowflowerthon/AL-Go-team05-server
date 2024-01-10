package com.ALGo.ALGo_server.message.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageResponse {
    private String MSG_CN;  //메세지 내용
    private String CREAT_DT;  //생성일
    private List<String> RCV_AREA_ID;  //수신지역 id
    private String EMRGNCY_STEP_ID;  //긴급단계 id
    private String DSSTR_SE_ID;  //재해구분 id

    public MessageResponse(String MSG_CN, String CREAT_DT, List<String> RCV_AREA_ID, String EMRGNCY_STEP_ID, String DSSTR_SE_ID){
        this.MSG_CN = MSG_CN;
        this.CREAT_DT = CREAT_DT;
        this.RCV_AREA_ID = RCV_AREA_ID;
        this.EMRGNCY_STEP_ID = EMRGNCY_STEP_ID;
        this.DSSTR_SE_ID = DSSTR_SE_ID;
    }

}
