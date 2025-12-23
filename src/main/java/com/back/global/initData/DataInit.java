package com.back.global.initData;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.boundedContext.member.app.MemberService;
import com.back.boundedContext.post.app.PostService;
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
            self.makeBaseComments();
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

        Member user1 = memberRepository.getReferenceById(4);
        Member user2 = memberRepository.getReferenceById(5);
        Member user3 = memberRepository.getReferenceById(6);

        Post post1 = postService.createPost(user1, "제목1", "내용1");
        Post post2 = postService.createPost(user1, "제목2", "내용2");
        Post post3 = postService.createPost(user1, "제목3", "내용3");
        Post post4 = postService.createPost(user2, "제목4", "내용4");
        Post post5 = postService.createPost(user2, "제목5", "내용5");
        Post post6 = postService.createPost(user3, "제목6", "내용6");

    }

    @Transactional
    public void makeBaseComments() {
        Post post1 = postService.findById(1).get();
        Post post2 = postService.findById(2).get();
        Post post3 = postService.findById(3).get();
        Post post4 = postService.findById(4).get();
        Post post5 = postService.findById(5).get();
        Post post6 = postService.findById(6).get();

        Member user1 = memberService.findByUsername("user1").get();
        Member user2 = memberService.findByUsername("user2").get();
        Member user3 = memberService.findByUsername("user3").get();

        if (post1.hasComments()) return;

        post1.addComment(user1, "댓글1");
        post1.addComment(user2, "댓글2");
        post1.addComment(user3, "댓글3");

        post2.addComment(user2, "댓글4");
        post2.addComment(user2, "댓글5");

        post3.addComment(user3, "댓글6");
        post3.addComment(user3, "댓글7");

        post4.addComment(user1, "댓글8");
    }
}