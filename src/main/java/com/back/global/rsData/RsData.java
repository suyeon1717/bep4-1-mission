package com.back.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RsData<T> { // 공통 API 응답 객체

    private final String resultCode;
    private final String msg;
    private final T data;

    public RsData(String resultCode, String msg) {
        this(resultCode, msg, null);
    }

}
