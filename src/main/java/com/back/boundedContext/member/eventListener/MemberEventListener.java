package com.back.boundedContext.member.eventListener;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.member.repository.MemberRepository;
import com.back.shared.post.event.CommentCreatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberRepository memberRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberRepository.findById(
                event.getPostDto().getAuthorId()
        ).orElseThrow();
        member.increaseActivityScore(3);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CommentCreatedEvent event) {
        Member member = memberRepository.findById(
                event.getCommentDto().getAuthorId()
        ).orElseThrow();
        member.increaseActivityScore(1);
    }
}
