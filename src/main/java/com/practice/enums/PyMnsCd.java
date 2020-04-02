package com.practice.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PyMnsCd {
	CARD("10", "신용카드", "신용카드"),
	VIRTUAL_ACCOUNT("20", "	무통장입금", "무통장입금"),
	ACCOUNT("30", "실시간 계좌이체", "실시간 계좌이체"),
	MOBILE("40", "휴대폰", "휴대폰");

	private String code;
	private String name;
	private String description;

	PyMnsCd(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public static PyMnsCd findByPyMnsCd(String pyMnsCd) {
		if(pyMnsCd == null) {
			throw new IllegalArgumentException("pyMnsCd cannot be null");
		}

		return Arrays.stream(PyMnsCd.values())
				.filter(PyMnsCd -> PyMnsCd.getCode().equals(pyMnsCd))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Not found PyMnsCd"));
	}

}
