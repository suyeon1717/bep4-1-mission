package com.back.boundedContext.member.domain;

import com.back.shared.member.domain.SourceMember;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.member.event.MemberModifiedEvent;
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

    public MemberDto toDto() {
        return new MemberDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                getUsername(),
                getNickname(),
                getActivityScore()
        );
    }

    public int increaseActivityScore(int amount) {
        // 활동점수 변화가 없으면 이벤트 발행 X
        if(amount == 0) return getActivityScore();

        // BaseMember의 ActivityScore 값 접근
        setActivityScore(getActivityScore() + amount);

        // 활동점수 변동 이벤트 호출 --> PostMember 테이블에 동기화
        publishEvent(new MemberModifiedEvent(toDto()));

        return getActivityScore();
    }
}
