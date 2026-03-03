package com.study.profile_stack_api.domain.tech_stack.controller;

import com.study.profile_stack_api.domain.tech_stack.dto.request.TechStackCreateRequest;
import com.study.profile_stack_api.domain.tech_stack.dto.response.TechStackResponse;
import com.study.profile_stack_api.domain.tech_stack.service.TechStackService;
import com.study.profile_stack_api.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tech_stacks")
@RequiredArgsConstructor
@Validated
public class TechStackController {
    private final TechStackService techStackService;

    @PostMapping
    public ResponseEntity<ApiResponse<TechStackResponse>> createTechStack(
            @RequestBody TechStackCreateRequest request) {
        TechStackResponse response = techStackService.createTechStack(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }
}
