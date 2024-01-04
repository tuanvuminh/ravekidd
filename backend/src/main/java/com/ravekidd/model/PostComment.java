package com.ravekidd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
    private String content = null;

    @Column(name="DATE")
    private LocalDateTime date = null;

    public PostComment() {
    }

    public PostComment(Post post, User user, String content, LocalDateTime date) {
        this.post = post;
        this.user = user;
        this.content = content;
        this.date = date;
    }
}

