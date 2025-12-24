package com.back.global.jpa.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseIdAndTimeManual extends BaseEntity {
    // id, createDate, modifyDate 칼럼에 자동 값 등록 옵션 제거

    @Id
    private int id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
