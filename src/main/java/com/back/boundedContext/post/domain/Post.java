package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.post.dto.PostDto;
import com.back.shared.post.event.CommentCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "POST_POST")
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private PostMember author;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    public Post(PostMember author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public PostDto toDto() {
        return new PostDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                author.getId(),
                author.getNickname(),
                title,
                content
        );
    }

    public PostComment addComment(PostMember author, String content) {
        PostComment postComment = new PostComment(this, author, content);

        postComments.add(postComment);

        // 댓글 작성시 작성자의 활동점수 1점 증가
        publishEvent(new CommentCreatedEvent(postComment.toDto()));

        return postComment;
    }

    public boolean hasComments(){
        return !postComments.isEmpty();
    }
}
