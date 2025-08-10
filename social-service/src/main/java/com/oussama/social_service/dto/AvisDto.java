package com.oussama.social_service.dto;

import com.oussama.social_service.enums.AvisType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvisDto {
    private Long id;

    private Long userId;
    private String userName;
    private Long prestationId;
    private Long demandeId;

    @NotNull(message = "La note est obligatoire")
    @Min(value = 1, message = "La note doit être entre 1 et 5")
    @Max(value = 5, message = "La note doit être entre 1 et 5")
    private Integer rating;

    @Size(max = 1000, message = "Le commentaire ne doit pas dépasser 1000 caractères")
    private String comment;

    @NotNull(message = "Le type d'avis est obligatoire")
    private AvisType avisType;

    private Boolean isAnonymous;
    private Boolean isApproved;
    private Boolean isFeatured;

    private Long approvedBy;
    private LocalDateTime approvedAt;
    private String adminResponse;
    private LocalDateTime responseDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Informations supplémentaires pour l'affichage
    private String prestationTitle;
}
