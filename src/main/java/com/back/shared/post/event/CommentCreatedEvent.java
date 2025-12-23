package com.back.shared.post.event;

import com.back.shared.post.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreatedEvent {
    private CommentDto commentDto;
}
