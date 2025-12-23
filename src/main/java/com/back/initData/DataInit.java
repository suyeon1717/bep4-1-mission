package com.back.initData;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.MemberRepository;
import com.back.service.MemberService;
import com.back.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class DataInit {
    private final DataInit self;
    private final MemberService memberService;
    private final PostService postService;
    private final MemberRepository memberRepository;

    public DataInit(@Lazy DataInit self, MemberService memberService, PostService postService, MemberRepository memberRepository) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;
        this.memberRepository = memberRepository;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePosts();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberService.count() > 0) return;

        Member systemMember = memberService.join("system", "1234", "시스템");
        Member holdingMember = memberService.join("holding", "1234", "홀딩");
        Member adminMember = memberService.join("admin", "1234", "관리자");
        Member user1Member = memberService.join("user1", "1234", "유저1");
        Member user2Member = memberService.join("user2", "1234", "유저2");
        Member user3Member = memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePosts() {
        if (postService.count() > 0) return;

        /**
         * user1 회원(4번 회원)이 글 3개 작성
         * user2 회원(5번 회원)이 글 2개 작성
         * user3 회원(6번 회원)이 글 1개 작성
         */
        Member user1 = memberRepository.getReferenceById(4L);
        Member user2 = memberRepository.getReferenceById(5L);
        Member user3 = memberRepository.getReferenceById(6L);

        Post post1 = postService.createPost(user1, "제목1", "내용1");
        Post post2 = postService.createPost(user1, "제목2", "내용2");
        Post post3 = postService.createPost(user1, "제목3", "내용3");
        Post post4 = postService.createPost(user2, "제목4", "내용4");
        Post post5 = postService.createPost(user2, "제목5", "내용5");
        Post post6 = postService.createPost(user3, "제목6", "내용6");

    }
}