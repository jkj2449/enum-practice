package com.practice.service;

import com.practice.dto.CommonPayReq;
import com.practice.dto.CommonPayRes;

public interface ApprovalPayService<R extends CommonPayRes, T extends CommonPayReq> {
	R approvePay(T requestPayModel);
}
