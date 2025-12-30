package com.back.standard.resultType;

public interface ResultType {
    String getResultCode();

    String getMsg();

    default <T> T getDate() {
        return null;
    }
}
