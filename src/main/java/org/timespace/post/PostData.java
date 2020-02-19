package org.timespace.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostData {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private String code;

    @NotNull
    private Long categoryId;

    private String categoryName;
}