package com.study.profile_stack_api.domain.profile.controller;

import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import com.study.profile_stack_api.domain.profile.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private ProfileService profileService;
    public ProfileController(ProfileService profileService) { this.profileService = profileService; }

    @PostMapping
    public ProfileResponse createProfile(@RequestBody ProfileCreateRequest request) {
        ProfileResponse profileResponse = profileService.createProfile(request); // service의 create 구현
        return profileResponse;
    }

    @GetMapping
    public List<ProfileResponse> getAllProfiles() {
        return profileService.getAllProfiles();
    }

}
