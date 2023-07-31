package com.dsm.up.domain.post.presentation;

import com.dsm.up.domain.post.presentation.dto.request.PostCreateRequest;
import com.dsm.up.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public Long create(@RequestBody @Valid PostCreateRequest request) {
        return  postService.create(request);
    }
}
