package com.dsm.up.domain.comment.presentation;

import com.dsm.up.domain.comment.presentation.dto.request.CommentRequest;
import com.dsm.up.domain.comment.service.CommentService;
import com.dsm.up.domain.post.presentation.dto.response.ReturnIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnIdResponse create(@RequestBody @Valid CommentRequest request) {
        return commentService.creat(request);
    }

    @PatchMapping("/{id}")
    public ReturnIdResponse update(@PathVariable @NotNull Long id, @RequestBody @Valid CommentRequest request) {
        return commentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        commentService.delete(id);
    }

}
