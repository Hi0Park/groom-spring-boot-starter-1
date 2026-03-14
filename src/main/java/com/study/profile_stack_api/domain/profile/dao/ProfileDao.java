package com.study.profile_stack_api.domain.profile.dao;

import com.study.profile_stack_api.domain.profile.entity.Profile;
import com.study.profile_stack_api.global.common.Page;

import java.util.List;
import java.util.Optional;

// DAO 인터페이스

public interface ProfileDao {
    // Create
    Profile save(Profile profile);

    // Read
    Optional<Profile> findById(Long id);
    List<Profile> findByPosition(String position);

    // Update
    Profile update(Profile profile);

    // Delete
    boolean deleteById(Long id);

    boolean existById(Long id);

    // Paging
    // TODO : Paging 구현
    Page<Profile> findAllWithPaging(int page, int size);
}
