package com.oussama.social_service.dto;

import com.oussama.social_service.enums.PrestationCategory;
import com.oussama.social_service.enums.PrestationType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestationDto {
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 255, message = "Le titre ne doit pas dépasser 255 caractères")
    private String title;

    private String description;

    @Size(max = 500, message = "La description courte ne doit pas dépasser 500 caractères")
    private String shortDescription;

    @NotNull(message = "Le type de prestation est obligatoire")
    private PrestationType prestationType;

    @NotNull(message = "La catégorie est obligatoire")
    private PrestationCategory category;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant maximum doit être positif")
    private BigDecimal maxAmount;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant minimum doit être positif")
    private BigDecimal minAmount;

    private Boolean isActive;

    private Boolean requiresDocuments;

    private String requiredDocuments;

    private String eligibilityCriteria;

    @Min(value = 1, message = "Le délai de traitement doit être au moins de 1 jour")
    private Integer processingTimeDays;

    @Min(value = 1, message = "Le nombre maximum de demandes par an doit être au moins de 1")
    private Integer maxRequestsPerYear;

    private String imageUrl;

    private Integer displayOrder;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
