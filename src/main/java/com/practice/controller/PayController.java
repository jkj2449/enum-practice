package com.practice.controller;

import com.practice.dto.CommonPayRes;
import com.practice.enums.PayType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PayController {
    @PostMapping("/approvalPay")
    public List<CommonPayRes> approvalPay(@RequestBody List<Map<String, Object>> payReqList) {

        List<CommonPayRes> commonPayResList = new ArrayList<>();
        for(Map<String, Object> payReq : payReqList) {
            PayType payType = PayType.findByPyMnsCd((String) payReq.get("pyMnsCd"));
            CommonPayRes commonPayRes = payType.getApprovalPayServiceInstance().approvePay(PayType.convertToCommonPayReq(payReq));
            commonPayResList.add(commonPayRes);
        }

        return commonPayResList;
    }

    @PostMapping("/cancelPay")
    public List<CommonPayRes> cancelPay(@RequestBody List<Map<String, Object>> payReqList) {

        List<CommonPayRes> commonPayResList = new ArrayList<>();
        for(Map<String, Object> payReq : payReqList) {
            PayType payType = PayType.findByPyMnsCd((String) payReq.get("pyMnsCd"));
            CommonPayRes commonPayRes = payType.getCancelPayServiceInstance().cancelPay(PayType.convertToCommonPayReq(payReq));
            commonPayResList.add(commonPayRes);
        }

        return commonPayResList;
    }
}
