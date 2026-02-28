package com.study.profile_stack_api.domain.profile.controller;

import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileUpdateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.domain.profile.service.ProfileService;
import com.study.profile_stack_api.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Validated
public class ProfileController {

    // 의존성 주입
    private final ProfileService profileService;

    // Create
    @PostMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> createProfile(@RequestBody ProfileCreateRequest request) {
        ProfileResponse profileResponse = profileService.createProfile(request); // service의 create 구현
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(profileResponse));
    }

    // Read
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getAllProfiles() {
        List<ProfileResponse> profileResponses = profileService.getAllProfiles();

        return ResponseEntity.ok()
                .body(ApiResponse.success(profileResponses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfileById(
            @PathVariable Long id ) {
        ProfileResponse profileResponse = profileService.getProfilesById(id);
        return ResponseEntity.ok()
                .body(ApiResponse.success(profileResponse));
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getProfilesByPosition(
            @PathVariable String position) {
        List<ProfileResponse> profileResponses = profileService.getProfilesByPosition(position);
        return ResponseEntity.ok()
                .body(ApiResponse.success(profileResponses));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @PathVariable Long id,
            @RequestBody ProfileUpdateRequest request) {


    }

    // Delete
    //@DeleteMapping

}
