package com.nicolaus.codecamp.spring.dao;

import com.nicolaus.codecamp.spring.entity.Jurusan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JurusanDAOImpl implements JurusanDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Jurusan save(Jurusan entity) {
        String sql = "INSERT INTO tbl_jurusan (kode, nama) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getKode());
            ps.setString(2, entity.getNama());
            return ps;
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Transactional
    @Override
    public Jurusan delete(Jurusan entity) {
        String sql = "DELETE FROM tbl_jurusan WHERE id=?";
        this.jdbcTemplate.update(sql, entity.getId());
        return null;
    }

    @Transactional
    @Override
    public Jurusan update(Jurusan entity) {
        String sql = "UPDATE tbl_jurusan SET kode=?, nama=? WHERE id=?";
        this.jdbcTemplate.update(entity.getKode(), entity.getNama(), entity.getId());
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Jurusan> find(Jurusan entity, int offset, int limit) {
        String sql = "SELECT * FROM tbl_jurusan";
        List<Jurusan> jurusans = this.jdbcTemplate.query(sql, new JurusanMapper());
        return jurusans;
    }

    @Transactional(readOnly = true)
    @Override
    public Jurusan findById(int id) {
        String sql = "SELECT * FROM tbl_jurusan WHERE id=?";
        Jurusan jurusan = this.jdbcTemplate.queryForObject(sql, new JurusanMapper(), id);
        return jurusan;
    }

    @Transactional(readOnly = true)
    @Override
    public int count(Jurusan entity) {
        String sql = "SELECT COUNT(id) AS count FROM tbl_jurusan";
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("count");
            }
        });
    }

    class JurusanMapper implements RowMapper<Jurusan> {

        @Override
        public Jurusan mapRow(ResultSet resultSet, int i) throws SQLException {
            Jurusan jurusan = new Jurusan();
            jurusan.setId(resultSet.getInt("id"));
            jurusan.setKode(resultSet.getString("kode"));
            jurusan.setNama(resultSet.getString("nama"));
            return jurusan;
        }

    }

}
