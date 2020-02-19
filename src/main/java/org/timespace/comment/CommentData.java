package org.timespace.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentData {
    @NotNull
    private Long postId;

    @NotBlank
    private String content;
}
