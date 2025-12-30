package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.global.rsData.RsData;
import com.back.shared.member.out.MemberApiClient;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;
    private final MemberApiClient memberApiClient;

    public RsData<Post> createPost(PostMember author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));
        eventPublisher.publish(new PostCreatedEvent(post.toDto()));

        // API를 호출을 통해 memberFacade 메서드 호출 (타 모듈간 메서드 호출 X)
        String randomSecureTip = memberApiClient.getRandomSecureTip();

        return new RsData<>(
                "201-1",
                "%d번 글이 생성되었습니다. 보안 팁: %s"
                        .formatted(post.getId(), randomSecureTip)
                , post
        );
    }
}
