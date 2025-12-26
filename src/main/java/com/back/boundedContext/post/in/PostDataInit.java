package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class PostDataInit {

    private final PostDataInit self;
    private final PostFacade postFacade;

    public PostDataInit(@Lazy PostDataInit self, PostFacade postFacade) {
        this.self = self;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postDataInitApplicationRunner() {
        return args -> {
            self.makeBasePosts();
            self.makeBaseComments();
        };
    }

    @Transactional
    public void makeBasePosts() {
        if (postFacade.count() > 0) return;

        PostMember user1 = postFacade.findMemberByUsername("user1").get();
        PostMember user2 = postFacade.findMemberByUsername("user2").get();
        PostMember user3 = postFacade.findMemberByUsername("user3").get();

        RsData<Post> post1RsData = postFacade.createPost(user1, "제목1", "내용1");
        log.debug(post1RsData.getMsg());

        RsData<Post> post2RsData = postFacade.createPost(user1, "제목2", "내용2");
        log.debug(post2RsData.getMsg());

        RsData<Post> post3RsData = postFacade.createPost(user1, "제목3", "내용3");
        log.debug(post3RsData.getMsg());

        RsData<Post> post4RsData = postFacade.createPost(user2, "제목4", "내용4");
        log.debug(post4RsData.getMsg());

        RsData<Post> post5RsData = postFacade.createPost(user2, "제목5", "내용5");
        log.debug(post5RsData.getMsg());

        RsData<Post> post6RsData = postFacade.createPost(user3, "제목6", "내용6");
        log.debug(post6RsData.getMsg());
    }

    @Transactional
    public void makeBaseComments() {
        Post post1 = postFacade.findById(1).get();
        Post post2 = postFacade.findById(2).get();
        Post post3 = postFacade.findById(3).get();
        Post post4 = postFacade.findById(4).get();
        Post post5 = postFacade.findById(5).get();
        Post post6 = postFacade.findById(6).get();

        PostMember user1 = postFacade.findMemberByUsername("user1").get();
        PostMember user2 = postFacade.findMemberByUsername("user2").get();
        PostMember user3 = postFacade.findMemberByUsername("user3").get();

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
