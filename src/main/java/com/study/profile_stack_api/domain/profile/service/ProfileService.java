package com.study.profile_stack_api.domain.profile.service;

import com.study.profile_stack_api.domain.profile.dao.ProfileDao;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileUpdateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileDeleteResponse;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.domain.profile.entity.Position;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import com.study.profile_stack_api.domain.profile.exception.ResourceNotFoundException;
import com.study.profile_stack_api.domain.profile.mapper.ProfileMapper;
import com.study.profile_stack_api.global.common.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;
    private final ProfileDao profileDao;
    private final ProfileMapper profileMapper;

    // Create
    public ProfileResponse createProfile(ProfileCreateRequest request) {
        validationCreateRequest(request);

        Profile profile = profileMapper.toEntity(request);

        Profile savedProfile = profileDao.save(profile);
        return profileMapper.toResponse(savedProfile);
    }

    // Read
    public ProfileResponse getProfilesById(Long id) {
        Profile profile = profileDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID : " + id));

        return profileMapper.toResponse(profile);
    }


    public List<ProfileResponse> getProfilesByPosition(String position) {
        List<Profile> profiles = profileDao.findByPosition(position);

        return profileMapper.toResponseList(profiles);
    }

    // Update
    public ProfileResponse updateProfile(Long id, ProfileUpdateRequest request) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(request);

        Profile profile = profileDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필을 찾을 수 없습니다. ID: " + id));

        if (request.hasNoUpdates()) {
            throw new IllegalArgumentException("수정할 내용이 존재하지 않습니다");
        }

        validationUpdateRequest(request);

        profileMapper.updateEntityFromRequest(request, profile);

        Profile updatedProfile = profileDao.update(profile);
        return profileMapper.toResponse(updatedProfile);
    }

    // Delete
    public ProfileDeleteResponse deleteProfile(Long id) {
        if (!profileDao.existById(id)) {
            throw new ResourceNotFoundException(id);
        }

        profileDao.deleteById(id);

        return ProfileDeleteResponse.of(id);
    }

    public Page<ProfileResponse> getProfilesWithPaging(int page, int size) {
        page = Math.max(0, page);
        size = Math.min(Math.max(1, size), MAX_PAGE_SIZE);

        Page<Profile> profilePage = profileDao.findAllWithPaging(page, size);

        List<ProfileResponse> content = profilePage.getContent().stream()
                .map(ProfileResponse::from)
                .collect(Collectors.toList());

        return new Page<>(content, page, size, profilePage.getTotalElements());
    }

    // validation
    public void validationCreateRequest(ProfileCreateRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 공백일 수 없습니다.");
        }
        if (request.getPosition() == null || request.getPosition().trim().isEmpty()) {
            throw new IllegalArgumentException("직무는 공백일 수 없습니다.");
        }
        if (request.getCareerYears() == null || request.getCareerYears() < 0 || request.getCareerYears() > 100) {
            throw new IllegalArgumentException("올바른 경력 연차를 입력해주세요.");
        }
    }

    private void validationUpdateRequest(ProfileUpdateRequest request) {
        if (request.getName() != null && request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다");
        }
        if (request.getEmail() != null && request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 공백일 수 없습니다.");
        }
        if (request.getPosition() != null && request.getPosition().trim().isEmpty()) {
            throw new IllegalArgumentException("직무는 공백일 수 없습니다.");
        }
        if (request.getCareerYears() != null && (request.getCareerYears() < 0 || request.getCareerYears() > 100)) {
            throw new IllegalArgumentException("올바른 경력 연차를 입력해주세요.");
        }
    }

}
