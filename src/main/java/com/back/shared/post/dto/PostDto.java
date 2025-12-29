package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(
        // final 필드라도 생성자 기반으로 주입 가능
        onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
)
public class PostDto {
    private final int id;
    private final String title;
    private final String content;
    private final int authorId;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.authorId = post.getAuthor().getId();
    }
}
