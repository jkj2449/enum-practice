package com.practice.service;

import com.practice.dto.CardPayReq;
import com.practice.dto.CardPayRes;
import com.practice.enums.PyMnsCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardApprovalPayServiceImpl implements ApprovalPayService<CardPayRes, CardPayReq> {

    @Override
    public CardPayRes approvePay(CardPayReq requestPayModel) {
        log.info(PyMnsCd.CARD.getName() + " 결제 승인");
        return new CardPayRes();
    }
}
