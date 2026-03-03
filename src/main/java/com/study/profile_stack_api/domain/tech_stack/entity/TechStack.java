package com.study.profile_stack_api.domain.tech_stack.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechStack {
    private Long id;
    private Long profileId;
    private String name;
    private Category category;
    private Proficiency proficiency;
    private Integer yearsOfExp;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
