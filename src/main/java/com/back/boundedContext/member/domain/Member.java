package com.back.boundedContext.member.domain;

import com.back.shared.member.domain.SourceMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends SourceMember {

    public Member(String username, String password, String nickname) {
        super(username, password, nickname); // Member -> SourceMember -> BaseMember 필드 초기화
    }

    public int increaseActivityScore(int amount) {
        // BaseMember의 ActivityScore 값 접근
        setActivityScore(getActivityScore() + amount);

        return getActivityScore();
    }
}
