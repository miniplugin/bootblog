package org.timespace.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.timespace.config.NotFoundException;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Post oldPost = postRepository.findByIdAndStatus(id, PostStatus.Y);
        if(oldPost == null){
            throw new NotFoundException(id + " not found");
        }

        oldPost.setContent(post.getContent());
        oldPost.setCode(post.getCode());
        oldPost.setTitle(post.getTitle());
        oldPost.setCategory(post.getCategory());
        return oldPost;
    }

    public void deletePost(Long id) {
        Post oldPost = postRepository.findByIdAndStatus(id, PostStatus.Y);
        if(oldPost == null){
            throw new NotFoundException(id + " not found");
        }
        //oldPost.setStatus(PostStatus.N);//살제하지 않고 상태만 변경
        postRepository.deleteById(id);//실제 삭제
    }

    public Post findByIdAndStatus(Long id, PostStatus status) {
        Post post = postRepository.findByIdAndStatus(id, status);
        if(post == null){
            throw new NotFoundException(id + " not found");
        }
        return post;
    }
}