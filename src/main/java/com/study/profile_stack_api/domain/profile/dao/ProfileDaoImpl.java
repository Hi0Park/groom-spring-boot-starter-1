package com.study.profile_stack_api.domain.profile.dao;

import com.study.profile_stack_api.domain.profile.entity.Position;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfileDaoImpl implements ProfileDao {
    private final JdbcTemplate jdbcTemplate;

    public ProfileDaoImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }


    @Override
    public Profile save(Profile profile) {
        return null;
    }

    @Override
    public List<Profile> findAll() {
        String sql = "select * from profile order by id desc";

        return jdbcTemplate.query(sql, profileRowMapper);
    }

    @Override
    public Optional<Profile> fineById(Long id) {
        String sql = """
                select *
                from profile
                where id = ?
                """;
        try {
            Profile profile = jdbcTemplate.queryForObject(sql, profileRowMapper, id);
            return Optional.ofNullable(profile);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Profile> findByPosition(String position) {
        String sql = """
                select *
                from profile
                where position = ?
                """;

        return jdbcTemplate.query(sql, profileRowMapper, position);
    }

    @Override
    public Profile update(Profile profile) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private final RowMapper<Profile> profileRowMapper = (rs, rowNum) -> {
        Profile profile = new Profile();
        profile.setId(rs.getLong("id"));
        profile.setName(rs.getString("name"));
        profile.setEmail(rs.getString("email"));
        profile.setBio(rs.getString("bio"));
        profile.setPosition(Position.valueOf(rs.getString("position")));
        profile.setCareerYears(rs.getInt("career_years"));
        profile.setGithubUrl(rs.getString("github_url"));
        profile.setBlogUrl(rs.getString("blog_url"));
        profile.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        profile.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

        return profile;
    };
}
