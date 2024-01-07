package com.ravekidd.service.repositories;

import com.ravekidd.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for accessing and managing Post entities in the database.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findById(long id);
    List<Post> findByUserIdIn(List<Long> userIds);
    List<Post> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
