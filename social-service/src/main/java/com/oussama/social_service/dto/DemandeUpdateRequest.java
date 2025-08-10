package com.oussama.social_service.dto;

import com.oussama.social_service.enums.DemandeStatus;
import com.oussama.social_service.enums.PriorityLevel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandeUpdateRequest {
    private DemandeStatus status;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant approuvé doit être positif")
    private BigDecimal approvedAmount;

    @Size(max = 1000, message = "Le commentaire administrateur ne doit pas dépasser 1000 caractères")
    private String adminComment;

    @Size(max = 1000, message = "La raison de rejet ne doit pas dépasser 1000 caractères")
    private String rejectionReason;

    private PriorityLevel priorityLevel;

    private String paymentReference;
}