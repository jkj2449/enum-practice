/**
 *
 * @Project LOTTEON FREE Next-Generation
 * @Company lotteon.com
 * @since 2019. 9. 10.
 * @author "kjjeong3@lotte.net"@lotte.net
 */
package com.practice.enums;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.utils.ApplicationContextWrapper;
import com.practice.dto.*;
import com.practice.service.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public enum PayType {
	CARD("신용카드", PyMnsCd.CARD.getCode(), CardApprovalPayServiceImpl.class, CardCancelPayServiceImpl.class, CardPayReq.class, CardPayRes.class)
	,VIRTUAL_ACCOUNT("가상계좌", PyMnsCd.VIRTUAL_ACCOUNT.getCode(), VirtualAccountApprovalPayServiceImpl.class, VirtualAccountCancelPayServiceImpl.class, VirtualAccountPayReq.class, VirtualAccountPayRes.class)
	,MOBILE("휴대폰", PyMnsCd.MOBILE.getCode(), MobileApprovalPayServiceImpl.class, MobileCancelPayServiceImpl.class, MobilePayReq.class, MobilePayRes.class);

	private String description;
	private String pyMnsCd;
	private Class<? extends ApprovalPayService<? extends CommonPayRes, ? extends CommonPayReq>> approvalPayServiceType;
	private Class<? extends CancelPayService<? extends CommonPayRes, ? extends CommonPayReq>> cancelPayServiceType;
	private Class<? extends CommonPayReq> requestPayModelType;
	private Class<? extends CommonPayRes> reponsePayModelType;

	PayType(String description
			, String pyMnsCd
			, Class<? extends ApprovalPayService<? extends CommonPayRes, ? extends CommonPayReq>> approvalPayServiceType
			, Class<? extends CancelPayService<? extends CommonPayRes, ? extends CommonPayReq>> cancelPayServiceType
			, Class<? extends CommonPayReq> requestPayModelType
			, Class<? extends CommonPayRes> reponsePayModelType) {

		this.description = description;
		this.pyMnsCd = pyMnsCd;
		this.approvalPayServiceType = approvalPayServiceType;
		this.cancelPayServiceType = cancelPayServiceType;
		this.requestPayModelType = requestPayModelType;
		this.reponsePayModelType = reponsePayModelType;
	}

	@SuppressWarnings("unchecked")
	public ApprovalPayService<CommonPayRes, CommonPayReq> getApprovalPayServiceInstance() {
		return (ApprovalPayService<CommonPayRes, CommonPayReq>) ApplicationContextWrapper.getBean(getApprovalPayServiceType());
	}

	@SuppressWarnings("unchecked")
	public CancelPayService<CommonPayRes, CommonPayReq> getCancelPayServiceInstance() {
		return (CancelPayService<CommonPayRes, CommonPayReq>) ApplicationContextWrapper.getBean(getCancelPayServiceType());
	}

	public CommonPayReq getRequestPayModel(Map<String, Object> payReq) {
		ObjectMapper mapper = ApplicationContextWrapper.getBean(ObjectMapper.class);
		return mapper.convertValue(payReq, getRequestPayModelType());
	}

	public static PayType findByPyMnsCd(String pyMnsCd) {
		if(pyMnsCd == null) {
			throw new IllegalArgumentException("pyMnsCd cannot be null");
		}

		return Arrays.stream(PayType.values())
				.filter(payType -> payType.getPyMnsCd().equals(pyMnsCd))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Not found payType"));
	}

	public static CommonPayReq convertToCommonPayReq(Map<String, Object> payReq) {
		PayType payType = PayType.findByPyMnsCd((String) payReq.get("pyMnsCd"));

		ObjectMapper mapper = ApplicationContextWrapper.getBean(ObjectMapper.class);

		return mapper.convertValue(payReq, payType.getRequestPayModelType());
	}

	public static List<CommonPayReq> convertToCommonPayReqList(List<Map<String, Object>> payReqList) {
		List<CommonPayReq> commonPayReqList = new ArrayList<>();
		for(Map<String, Object> payReq : payReqList) {
			commonPayReqList.add(convertToCommonPayReq(payReq));
		}

		return commonPayReqList;
	}

	public static List<CommonPayRes> convertToCommonPayResList(List<Map<String, Object>> payReqList) {
		ObjectMapper mapper = ApplicationContextWrapper.getBean(ObjectMapper.class);

		List<CommonPayRes> commonPayResList = new ArrayList<>();
		for(Map<String, Object> payReq : payReqList) {
			String pyMnsCd = (String) payReq.get("pyMnsCd");
			PayType payType = PayType.findByPyMnsCd(pyMnsCd);

			CommonPayRes commonPayRes = mapper.convertValue(payReq, payType.getReponsePayModelType());

			commonPayResList.add(commonPayRes);
		}

		return commonPayResList;
	}
}
