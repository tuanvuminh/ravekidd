package com.ravekidd.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "POST_COMMENTS")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post = null;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user = null;

    @Column(name = "CONTENT")
    @NotBlank(message = "Content cannot be blank.")
    private String content = null;

    @Column(name="DATE")
    private LocalDateTime date = null;

    @ManyToMany
    @JoinTable(
            name = "POST_COMMENTS_LIKES",
            joinColumns = @JoinColumn(name = "COMMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> likes = null;

    public PostComment() {
    }

    public PostComment(Post post, User user, String content, LocalDateTime date) {
        this.post = post;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public void addLike(User user) {
        likes.add(user);
    }

    public void removeLike(User user) {
        likes.remove(user);
    }
}

