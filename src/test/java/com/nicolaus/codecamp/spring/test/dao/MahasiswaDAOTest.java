package com.nicolaus.codecamp.spring.test.dao;

import com.nicolaus.codecamp.spring.config.AppConfig;
import com.nicolaus.codecamp.spring.dao.MahasiswaDAO;
import com.nicolaus.codecamp.spring.entity.Mahasiswa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MahasiswaDAOTest {

    @Autowired
    private MahasiswaDAO mahasiswaDAO;

    @Test
    public void save() {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim("009");
        mahasiswa.setNama("Joko");
        mahasiswa.setAlamat("Purwokerto");
        mahasiswa = mahasiswaDAO.save(mahasiswa);
        System.out.println(mahasiswa);
    }

    @Test
    public void delete() {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(1);
        mahasiswaDAO.delete(mahasiswa);
    }

    @Test
    public void update() {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(8);
        mahasiswa.setNim("008");
        mahasiswa.setNama("Hermawan Supart");
        mahasiswa.setAlamat("Semarang");
        mahasiswa = mahasiswaDAO.update(mahasiswa);
        System.out.println(mahasiswa);
    }

    @Test
    public void findById() {
        Mahasiswa mahasiswa = mahasiswaDAO.findById(7);
        System.out.println(mahasiswa);
    }

    @Test
    public void find() {
        List<Mahasiswa> mahasiswas = mahasiswaDAO.find(null, 0, 0);
        for (Mahasiswa mahasiswa : mahasiswas) {
            System.out.println(mahasiswa);
        }
    }

    @Test
    public void count() {
        System.out.println(mahasiswaDAO.count(null));
    }

}
