package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.shared.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostFacade postFacade;

    // 목록 조회
    @GetMapping
    public List<PostDto> getItems() {
        return postFacade
                .findByOrderByIdDesc()
                .stream()
                .map(PostDto::new) // entity -> dto
                .toList();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public PostDto getItem(@PathVariable int id) {
        return postFacade
                .findById(id)
                .map(PostDto::new) // entity -> dto
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Post not found"
        ));
    }
}
