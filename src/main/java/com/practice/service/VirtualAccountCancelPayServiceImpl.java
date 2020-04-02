package com.practice.service;

import com.practice.dto.VirtualAccountPayReq;
import com.practice.dto.VirtualAccountPayRes;
import com.practice.enums.PyMnsCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VirtualAccountCancelPayServiceImpl implements CancelPayService<VirtualAccountPayRes, VirtualAccountPayReq> {
    @Override
    public VirtualAccountPayRes cancelPay(VirtualAccountPayReq requestPayModel) {
        log.info(PyMnsCd.VIRTUAL_ACCOUNT.getName() + " 결제 취소");
        return new VirtualAccountPayRes();
    }
}
