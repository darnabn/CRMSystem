package com.example.crmSystem.repository;

import com.example.crmSystem.entity.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationRequestRepositoryImpl implements ApplicationRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationRequestRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ApplicationRequest> rowMapper = new RowMapper<>() {
        @Override
        public ApplicationRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            ApplicationRequest req = new ApplicationRequest();
            req.setId(rs.getLong("id"));
            req.setUserName(rs.getString("user_name"));
            req.setCourseName(rs.getString("course_name"));
            req.setCommentary(rs.getString("commentary"));
            req.setPhone(rs.getString("phone"));
            req.setHandled(rs.getBoolean("handled"));
            return req;
        }
    };

    @Override
    public ApplicationRequest save(ApplicationRequest request) {
        String sql = "INSERT INTO application_requests(user_name, course_name, commentary, phone, handled) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,
                request.getUserName(),
                request.getCourseName(),
                request.getCommentary(),
                request.getPhone(),
                request.isHandled());
        return request;
    }

    @Override
    public List<ApplicationRequest> findAll() {
        String sql = "SELECT * FROM application_requests";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ApplicationRequest> findById(Long id) {
        String sql = "SELECT * FROM application_requests WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    @Override
    public void markAsHandled(Long id) {
        String sql = "UPDATE application_requests SET handled = true WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(ApplicationRequest request) {
        String sql = "UPDATE application_requests SET user_name=?, course_name=?, commentary=?, phone=?, handled=? WHERE id=?";
        jdbcTemplate.update(sql,
                request.getUserName(),
                request.getCourseName(),
                request.getCommentary(),
                request.getPhone(),
                request.isHandled(),
                request.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM application_requests WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
