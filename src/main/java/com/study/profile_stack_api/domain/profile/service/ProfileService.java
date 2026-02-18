package com.study.profile_stack_api.domain.profile.service;

import com.study.profile_stack_api.domain.profile.dao.ProfileDao;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.domain.profile.entity.Position;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private ProfileDao profileDao;

    public ProfileService(ProfileDao profileDao) {this.profileDao = profileDao;}

    // Create
    public ProfileResponse createProfile(ProfileCreateRequest request) {
        // validation


        Profile profile = new Profile(
                null,
                request.getName(),
                request.getEmail(),
                request.getBio(),
                Position.valueOf(request.getPosition()),
                request.getCareerYears(),
                request.getGithubUrl(),
                request.getBlogUrl());

        Profile savedProfile = profileDao.save(profile);
        return ProfileResponse.from(savedProfile);
    }

    // Read

    // getAll
    public List<ProfileResponse> getAllProfiles() {
        List<Profile> profiles = profileDao.findAll();

        return profiles.stream()
                .map(ProfileResponse::from)
                .collect(Collectors.toList());
    }

    // getById
    public List<ProfileResponse> getProfilesById(Long id) {
        List<Profile> profiles = profileDao.fineById(id);

        return profiles.stream()
                .map(ProfileResponse::from)
                .collect(Collectors.toList());
    }

    // getByPosition
    public List<ProfileResponse> getProfilesByPosition(String position) {
        List<Profile> profiles = profileDao.findByPosition(position);

        return profiles.stream()
                .map(ProfileResponse::from)
                .collect(Collectors.toList());
    }
}
