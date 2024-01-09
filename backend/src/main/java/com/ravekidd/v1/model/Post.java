package com.ravekidd.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="POSTS")
public class Post {

    @Id
    @Column(name="POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user = null;

    @Column(name="DESCRIPTION")
    @NotBlank(message = "Description cannot be blank.")
    private String description = null;

    @Column(name="LINK")
    private String link = null;

    @Column(name="DATE")
    private LocalDateTime date = null;

    @ManyToMany
    @JoinTable(
            name = "POST_LIKES",
            joinColumns = @JoinColumn(name = "POST_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> likes = null;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> comments = null;

    public Post() {
    }

    public Post(User user, String description, String link, LocalDateTime date) {
        this.user = user;
        this.description = description;
        this.link = link;
        this.date = date;
    }

    public void addLike(User user) {
        likes.add(user);
    }

    public void removeLike(User user) {
        likes.remove(user);
    }

    public void addComment(PostComment comment) {
        comments.add(comment);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
    }
}
