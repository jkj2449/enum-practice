package com.practice.service;

import com.practice.dto.MobilePayReq;
import com.practice.dto.MobilePayRes;
import com.practice.enums.PyMnsCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MobileCancelPayServiceImpl implements CancelPayService<MobilePayRes, MobilePayReq> {
    @Override
    public MobilePayRes cancelPay(MobilePayReq requestPayModel) {
        log.info(PyMnsCd.MOBILE.getName() + " 결제 취소");
        return new MobilePayRes();
    }
}
