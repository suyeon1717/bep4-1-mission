package com.back.global.initData;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
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
//    private final MemberService memberService;
    private final MemberFacade memberFacade;
//    private final PostService postService;
    private final PostFacade postFacade;
    private final MemberRepository memberRepository;

    public DataInit(@Lazy DataInit self, MemberFacade memberFacade, PostFacade postFacade, MemberRepository memberRepository) {
        this.self = self;
        this.memberFacade = memberFacade;
        this.postFacade = postFacade;
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
        if (memberFacade.count() > 0) return;

        Member systemMember = memberFacade.join("system", "1234", "시스템");
        Member holdingMember = memberFacade.join("holding", "1234", "홀딩");
        Member adminMember = memberFacade.join("admin", "1234", "관리자");
        Member user1Member = memberFacade.join("user1", "1234", "유저1");
        Member user2Member = memberFacade.join("user2", "1234", "유저2");
        Member user3Member = memberFacade.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePosts() {
        if (postFacade.count() > 0) return;

        Member user1 = memberRepository.getReferenceById(4);
        Member user2 = memberRepository.getReferenceById(5);
        Member user3 = memberRepository.getReferenceById(6);

        Post post1 = postFacade.createPost(user1, "제목1", "내용1");
        Post post2 = postFacade.createPost(user1, "제목2", "내용2");
        Post post3 = postFacade.createPost(user1, "제목3", "내용3");
        Post post4 = postFacade.createPost(user2, "제목4", "내용4");
        Post post5 = postFacade.createPost(user2, "제목5", "내용5");
        Post post6 = postFacade.createPost(user3, "제목6", "내용6");

    }

    @Transactional
    public void makeBaseComments() {
        Post post1 = postFacade.findById(1).get();
        Post post2 = postFacade.findById(2).get();
        Post post3 = postFacade.findById(3).get();
        Post post4 = postFacade.findById(4).get();
        Post post5 = postFacade.findById(5).get();
        Post post6 = postFacade.findById(6).get();

        Member user1 = memberFacade.findByUsername("user1").get();
        Member user2 = memberFacade.findByUsername("user2").get();
        Member user3 = memberFacade.findByUsername("user3").get();

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