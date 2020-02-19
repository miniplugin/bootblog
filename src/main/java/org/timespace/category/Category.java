package org.timespace.category;

import lombok.Data;
import org.timespace.post.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime regDate;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> post = new ArrayList<>();

    Category() {
    }

    public Category(Long id, String name, LocalDateTime regDate) {
        this.name = name;
        this.id = id;
        this.regDate = regDate;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id) {
        this.id = id;
    }
    //기타 생성자 getter setter
}