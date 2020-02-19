package org.timespace.user;

import lombok.Data;
import org.timespace.comment.Comment;
import org.timespace.post.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User implements Serializable {
    @GeneratedValue
    @Id
    private Long id;

    private String email;

    private String name;

    private String github;

    private String avatarUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Column
    @Lob
    private String bio;

    User(){

    }
    public User(String email, String name, String githubLogin, String avatar) {
        this.email = email;
        this.name = name;
        this.github = githubLogin;
        this.avatarUrl = avatar;
    }

    //기타 getter setter hashcode toString
}