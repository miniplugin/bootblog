package org.timespace.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post, Long> {
    Post findByIdAndStatus(Long id, PostStatus status);
    default Post findOne(Long id) {
        return (Post) findById(id).orElse(null);
    }
}