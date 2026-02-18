package com.study.profile_stack_api.domain.profile.dao;

import com.study.profile_stack_api.domain.profile.entity.Profile;

import java.util.List;

public interface ProfileDao {
    // Create
    Profile save(Profile profile);

    // Read
    List<Profile> findAll();
    List<Profile> fineById(Long id);
    List<Profile> findByPosition(String position);

    // Update
    Profile update(Profile profile);

    // Delete
    boolean deleteById(Long id);

    // Paging
    // TODO : Paging 구현
}
