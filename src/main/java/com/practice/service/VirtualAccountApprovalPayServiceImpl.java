package com.practice.service;

import com.practice.dto.VirtualAccountPayReq;
import com.practice.dto.VirtualAccountPayRes;
import com.practice.enums.PyMnsCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VirtualAccountApprovalPayServiceImpl implements ApprovalPayService<VirtualAccountPayRes, VirtualAccountPayReq> {
    @Override
    public VirtualAccountPayRes approvePay(VirtualAccountPayReq requestPayModel) {
        log.info(PyMnsCd.VIRTUAL_ACCOUNT.getName() + " 결제 승인");
        return new VirtualAccountPayRes();
    }
}
