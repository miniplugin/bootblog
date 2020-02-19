package org.timespace.post;

import lombok.Data;
import org.timespace.category.Category;
import org.timespace.comment.Comment;
import org.timespace.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @Lob
    @NotNull
    private String content;

    @Lob
    private String code;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime regDate;

    //기타 프로퍼티
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    Post(){
    }

    public Post(String title, String content, String code, PostStatus status) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
    }

    public Post(long l, String 제목1, String 컨텐츠1, String 마크다운1, PostStatus y) {
    }

    public Post(String title, String content, String code, PostStatus status, Category category) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
        this.category = category;
    }

    public Post(String title, String content, String code, PostStatus status, Category category, User user) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
        this.category = category;
        this.user = user;
    }

    public Post(Long postId) {
        this.id = postId;
    }

}