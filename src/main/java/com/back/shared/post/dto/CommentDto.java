package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
    private int postId;
    private String content;
    private int authorId;

    public CommentDto(PostComment postComment) {
        this.postId = postComment.getPost().getId();
        this.content = postComment.getContent();
        this.authorId = postComment.getAuthor().getId();
    }
}