package org.timespace.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.timespace.category.Category;
import org.timespace.category.CategoryService;
import org.timespace.comment.CommentData;
import org.timespace.config.Navigation;
import org.timespace.config.NotFoundException;
import org.timespace.config.Section;
import org.timespace.user.User;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
@Navigation(Section.POST)
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public String findByPost(@PathVariable Long id, Model model, @ModelAttribute CommentData commentData) {
        Post post = postService.findByIdAndStatus(id, PostStatus.Y);
        if(post == null){
            throw new NotFoundException(id + " not found");
        }
        model.addAttribute("post", post);
        return "post/post";
    }

    @GetMapping("/new")
    public String newPost(PostData postData) {
        return "post/new";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postService.findByIdAndStatus(id, PostStatus.Y);
        if(post == null){
            throw new NotFoundException(id + " not found");
        }
        PostData createPost = new PostData();
        createPost.setCategoryId(post.getCategory().getId());
        createPost.setCategoryName(post.getCategory().getName());
        createPost.setTitle(post.getTitle());
        createPost.setCode(post.getCode());
        createPost.setContent(post.getContent());
        createPost.setId(id);
        model.addAttribute("editPost", createPost);
        return "post/edit";
    }

    @PostMapping
    public String createPost(@ModelAttribute @Valid PostData createPost, BindingResult bindingResult, Model model, @AuthenticationPrincipal User user) {
        if(bindingResult.hasErrors()){
            return "post/new";
        }
        Post post = new Post(createPost.getTitle(),
                createPost.getContent(),
                createPost.getCode(),
                PostStatus.Y,
                new Category(createPost.getCategoryId()),
                user
        );
        Post newPost = postService.createPost(post);
        model.addAttribute("post", newPost);
        return "redirect:/posts/" +  newPost.getId();
    }

    @PostMapping("/{id}/edit")
    public String modifyPost(@PathVariable Long id, @ModelAttribute("editPost") @Valid PostData createPost, BindingResult bindingResult, @AuthenticationPrincipal User user) {
        if(bindingResult.hasErrors()){
            return "post/edit";
        }
        postService.updatePost(id, new Post(
                createPost.getTitle(),
                createPost.getContent(),
                createPost.getCode(),
                PostStatus.Y,
                new Category(createPost.getCategoryId()),
                user
        ));
        return "redirect:/posts/" +  id;
    }

    @PostMapping("{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/#/";
    }
}