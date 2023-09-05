package com.dsm.up.domain.post.presentation;

import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.domain.post.presentation.dto.response.PostListResponse;
import com.dsm.up.domain.post.presentation.dto.response.ReturnPostIdResponse;
import com.dsm.up.domain.post.service.PostDetailsService;
import com.dsm.up.domain.post.service.PostListService;
import com.dsm.up.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final PostDetailsService postDetailsService;
    private final PostListService postListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnPostIdResponse create(@RequestBody @Valid PostRequest request) {
        return postService.create(request);
    }

    @PatchMapping(value = "/{id}")
    public Long update(@PathVariable @NotNull Long id,@RequestBody @Valid PostRequest request){
        return postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        postService.delete(id);
    }

    @GetMapping("/{id}")
    public PostResponse getPostDetails(@PathVariable @NotNull Long id) {
        return postDetailsService.getPostDetails(id);
    }

    @GetMapping("/search")
    public PostListResponse findPost(@RequestParam(value = "title") String title, @RequestParam(value = "state") String state, @RequestParam(value = "major") String major, Pageable page) {
        return postListService.findPost(title, state, major, page);
    }

    @GetMapping("/user")
    public PostListResponse getUserPostsPaged(Pageable pageable) {
        return postListService.getUserPostsPaged(pageable);
    }

    @PostMapping(value = "/postImage/{id}", consumes = "multipart/form-data")
    public void postImage(@PathVariable @NotNull Long id, @RequestPart(value = "image") MultipartFile file) {
        postService.postImage(id, file);
    }
}
