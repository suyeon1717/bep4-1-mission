package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
    private int postId;
    private String content;
    private int authorId;

    public CommentDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
        this.authorId = comment.getAuthor().getId();
    }
}