package org.timespace.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default void delete(Long id) {
        deleteById(id);
    }
}
