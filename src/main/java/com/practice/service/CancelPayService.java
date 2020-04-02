package com.practice.service;

import com.practice.dto.CommonPayReq;
import com.practice.dto.CommonPayRes;

public interface CancelPayService<R extends CommonPayRes, T extends CommonPayReq>  {
	R cancelPay(T requestPayModel);
}
