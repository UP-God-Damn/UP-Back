package com.dsm.up.domain.post.presentation;

import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody @Valid PostRequest request) {
        return postService.create(request);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable @NotNull Long id, @RequestBody @Valid PostRequest request) {
        return postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        postService.delete(id);
    }

    @GetMapping("{id}")
    public PostResponse getPostById(@PathVariable @NotNull Long id) {
        return postService.getPostDetails(id);
    }

}
