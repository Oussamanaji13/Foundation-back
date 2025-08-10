package com.oussama.content_service.Dto;


import com.oussama.content_service.enums.ContentStatus;
import com.oussama.content_service.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    private String description;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDateTime startDate;

    private LocalDateTime endDate;
    private String location;
    private String imageUrl;
    private Long organizerId;
    private String organizerName;
    private EventType type;
    private ContentStatus status;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private Boolean registrationRequired;
    private LocalDateTime registrationDeadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
