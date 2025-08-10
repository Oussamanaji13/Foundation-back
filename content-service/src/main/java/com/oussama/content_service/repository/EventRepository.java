package com.oussama.content_service.repository;


import com.oussama.content_service.entity.Event;
import com.oussama.content_service.enums.ContentStatus;
import com.oussama.content_service.enums.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Méthodes existantes (si déjà présentes)
    Page<Event> findByStatusOrderByStartDateAsc(ContentStatus status, Pageable pageable);
    Page<Event> findByStatusAndTypeOrderByStartDateAsc(ContentStatus status, EventType type, Pageable pageable);

    // Nouvelles méthodes à ajouter
    @Query("SELECT e FROM Event e WHERE e.status = :status AND e.startDate >= :now ORDER BY e.startDate ASC")
    Page<Event> findUpcomingEvents(@Param("status") ContentStatus status, @Param("now") LocalDateTime now, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.status = :status AND e.startDate < :now ORDER BY e.startDate DESC")
    Page<Event> findPastEvents(@Param("status") ContentStatus status, @Param("now") LocalDateTime now, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.status = 'PUBLISHED' AND e.startDate >= :now AND " +
            "(LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Event> searchUpcomingEvents(@Param("keyword") String keyword, @Param("now") LocalDateTime now, Pageable pageable);

    List<Event> findTop5ByStatusAndStartDateGreaterThanEqualOrderByStartDateAsc(ContentStatus status, LocalDateTime now);
}
