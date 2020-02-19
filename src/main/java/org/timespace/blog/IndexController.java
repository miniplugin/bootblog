package org.timespace.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.timespace.config.Navigation;
import org.timespace.config.Section;
import org.timespace.post.PostRepository;
import org.timespace.user.User;

@Controller
@RequiredArgsConstructor
@Navigation(Section.HOME)
public class IndexController {

    private final PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model, Pageable pageable){
        model.addAttribute("posts", postRepository.findAll(pageable));
        return "index";
    }
}