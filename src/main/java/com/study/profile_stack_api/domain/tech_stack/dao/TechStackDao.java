package com.study.profile_stack_api.domain.tech_stack.dao;

import com.study.profile_stack_api.domain.tech_stack.entity.TechStack;

import java.util.List;

public interface TechStackDao {
    TechStack save(TechStack techStack);

    List<TechStack> findAll();
}
