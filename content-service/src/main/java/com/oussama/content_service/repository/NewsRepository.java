package com.oussama.content_service.repository;


import com.oussama.content_service.entity.News;
import com.oussama.content_service.enums.ContentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    // Méthodes de base pour les actualités
    Page<News> findByStatusOrderByPublishedAtDesc(ContentStatus status, Pageable pageable);

    Page<News> findByStatusAndFeaturedOrderByPublishedAtDesc(ContentStatus status, Boolean featured, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.status = :status AND " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(n.shortDescription) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<News> searchByKeyword(@Param("status") ContentStatus status, @Param("keyword") String keyword, Pageable pageable);

    List<News> findTop5ByStatusOrderByViewCountDesc(ContentStatus status);

    @Modifying
    @Query("UPDATE News n SET n.viewCount = n.viewCount + 1 WHERE n.id = :newsId")
    void incrementViewCount(@Param("newsId") Long newsId);

    @Query("SELECT n FROM News n WHERE n.status = 'PUBLISHED' AND n.publishedAt <= :now ORDER BY n.publishedAt DESC")
    Page<News> findPublishedNews(@Param("now") LocalDateTime now, Pageable pageable);
}