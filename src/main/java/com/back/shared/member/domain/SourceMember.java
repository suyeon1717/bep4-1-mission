package com.back.shared.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class SourceMember extends BaseMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    public SourceMember(String username, String password, String nickname) {
        super(username, password, nickname, 0); // BaseMember 필드 초기화
    }
}
