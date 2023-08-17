package com.dsm.up.domain.post.presentation;

import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.domain.post.presentation.dto.response.PostListResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping(consumes = {"application/json", "multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestPart(value = "request") @Valid PostRequest request, @RequestPart(value = "image", required = false) MultipartFile file) {
        return postService.create(request, file);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json", "multipart/form-dat"})
    public Long update(@PathVariable @NotNull Long id, @RequestPart(value = "request") @Valid PostRequest request, @RequestPart(value = "image", required = false) MultipartFile file) {
        return postService.update(id, request, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        postService.delete(id);
    }

    @GetMapping("/{id}")
    public PostResponse getPostDetails(@PathVariable @NotNull Long id) {
        return postService.getPostDetails(id);
    }

    @GetMapping("/search")
    public PostListResponse findPost(@RequestParam(value = "title") String title, @RequestParam(value = "state") String state, @RequestParam(value = "major") String major) {
        return postService.findPost(title, state, major);
    }

}
