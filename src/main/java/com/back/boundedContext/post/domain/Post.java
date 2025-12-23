package com.back.boundedContext.post.domain;

import com.back.boundedContext.member.domain.Member;
import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.post.dto.CommentDto;
import com.back.shared.post.event.CommentCreatedEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Comment addComment(Member author, String content) {
        Comment comment = new Comment(this, author, content);

        comments.add(comment);

        // 댓글 작성시 작성자의 활동점수 1점 증가
        publishEvent(new CommentCreatedEvent(new CommentDto(comment)));

        return comment;
    }

    public boolean hasComments(){
        return !comments.isEmpty();
    }
}
