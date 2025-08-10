package com.oussama.social_service.dto;

import com.oussama.social_service.enums.DemandeStatus;
import com.oussama.social_service.enums.PriorityLevel;
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
public class DemandeDto {
    private Long id;

    @NotNull(message = "L'ID de la prestation est obligatoire")
    private Long prestationId;

    private Long userId;
    private String userEmail;
    private String userName;
    private String employeeId;

    private DemandeStatus status;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant demandé doit être positif")
    private BigDecimal requestedAmount;

    private BigDecimal approvedAmount;

    @NotBlank(message = "La justification est obligatoire")
    @Size(max = 2000, message = "La justification ne doit pas dépasser 2000 caractères")
    private String justification;

    private String adminComment;
    private String rejectionReason;
    private String documentsUploaded;
    private PriorityLevel priorityLevel;

    private LocalDateTime submittedAt;
    private LocalDateTime processedAt;
    private Long processedBy;
    private String processedByName;
    private LocalDateTime expectedProcessingDate;
    private String paymentReference;
    private LocalDateTime paymentDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Information de la prestation associée (pour l'affichage)
    private String prestationTitle;
    private String prestationCategory;
}
