package org.timespace.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryData {
    private Long id;
    @NotBlank
    private String name;
}
