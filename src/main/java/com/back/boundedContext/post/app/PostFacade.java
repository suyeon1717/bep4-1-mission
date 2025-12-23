package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final CreatePostUseCase createPostUseCase;

    public long count() {
        return postRepository.count();
    }

    @Transactional
    public Post createPost(Member author, String title, String content) {
        return createPostUseCase.createPost(author, title, content);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}
