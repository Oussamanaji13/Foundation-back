package com.oussama.social_service.entity;

import com.oussama.social_service.enums.PrestationCategory;
import com.oussama.social_service.enums.PrestationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "prestation_type", nullable = false)
    private PrestationType prestationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private PrestationCategory category;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "min_amount")
    private BigDecimal minAmount;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "requires_documents", nullable = false)
    private Boolean requiresDocuments = false;

    @Column(name = "required_documents", columnDefinition = "TEXT")
    private String requiredDocuments; // Liste des documents requis (JSON ou texte)

    @Column(name = "eligibility_criteria", columnDefinition = "TEXT")
    private String eligibilityCriteria;

    @Column(name = "processing_time_days")
    private Integer processingTimeDays;

    @Column(name = "max_requests_per_year")
    private Integer maxRequestsPerYear;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "display_order")
    private Integer displayOrder;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

