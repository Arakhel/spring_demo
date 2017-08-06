package com.nicolaus.codecamp.spring.dao;

import com.nicolaus.codecamp.spring.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class MahasiswaDAOImpl implements MahasiswaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Mahasiswa save(Mahasiswa entity) {
        String sql = "INSERT INTO tbl_mahasiswa (nim, nama, alamat) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getNim());
            ps.setString(2, entity.getNama());
            ps.setString(3, entity.getAlamat());
            return ps;
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Transactional
    @Override
    public Mahasiswa delete(Mahasiswa entity) {
        String sql = "DELETE FROM tbl_mahasiswa WHERE id=?";
        this.jdbcTemplate.update(sql, entity.getId());
        return null;
    }

    @Transactional
    @Override
    public Mahasiswa update(Mahasiswa entity) {
        String sql = "UPDATE tbl_mahasiswa SET nim=?, nama=?, alamat=? WHERE id=?";
        this.jdbcTemplate.update(sql, entity.getNim(), entity.getNama(),entity.getAlamat(), entity.getId());
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Mahasiswa> find(Mahasiswa entity, int offset, int limit) {
        String sql = "SELECT * FROM tbl_mahasiswa";
        List<Mahasiswa> mahasiswas = this.jdbcTemplate.query(sql, new MahasiswaMapper());
        return mahasiswas;
    }

    @Transactional(readOnly = true)
    @Override
    public Mahasiswa findById(int id) {
        String sql = "SELECT * FROM tbl_mahasiswa WHERE id=?";
        Mahasiswa mahasiswa = (Mahasiswa) this.jdbcTemplate.queryForObject(sql, new MahasiswaMapper(), id);
        return mahasiswa;
    }

    @Transactional(readOnly = true)
    @Override
    public int count(Mahasiswa entity) {
        String sql = "SELECT COUNT(id) AS count FROM tbl_mahasiswa";
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("count");
            }
        });
    }

    class MahasiswaMapper implements RowMapper<Mahasiswa> {

        @Override
        public Mahasiswa mapRow(ResultSet resultSet, int i) throws SQLException {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setId(resultSet.getInt("id"));
            mahasiswa.setNim(resultSet.getString("nim"));
            mahasiswa.setNama(resultSet.getString("nama"));
            mahasiswa.setAlamat(resultSet.getString("alamat"));
            return mahasiswa;
        }

    }
}
