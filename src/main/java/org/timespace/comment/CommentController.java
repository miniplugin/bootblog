package org.timespace.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.timespace.post.Post;
import org.timespace.post.PostRepository;
import org.timespace.user.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostRepository postRepository;

    @ModelAttribute
    public Post post(@ModelAttribute CommentData commentData){
        return postRepository.findOne(commentData.getPostId());
    }

    @PostMapping
    public String createComment(Post post, @ModelAttribute @Valid CommentData commentData, BindingResult bindingResult, Model model, @AuthenticationPrincipal User user){
        if(bindingResult.hasErrors()){
            return "post/post";
        }
        model.addAttribute("comment",commentService.createComment(
                new Comment(commentData.getContent(),
                        //new Post(commentData.getPostId())
                        post, user)
        ));
        return "redirect:/posts/"+commentData.getPostId();
    }

    @PostMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/posts/"+postId;
    }
}
