package com.practice.service;

import com.practice.dto.CardPayReq;
import com.practice.dto.CardPayRes;
import com.practice.enums.PyMnsCd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardCancelPayServiceImpl implements CancelPayService<CardPayRes, CardPayReq> {
    @Override
    public CardPayRes cancelPay(CardPayReq requestPayModel) {
        log.info(PyMnsCd.CARD.getName() + " 결제 취소");
        return new CardPayRes();
    }
}
