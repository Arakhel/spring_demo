package com.nicolaus.codecamp.spring.test.dao;

import com.nicolaus.codecamp.spring.config.AppConfig;
import com.nicolaus.codecamp.spring.dao.JurusanDAO;
import com.nicolaus.codecamp.spring.entity.Jurusan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class JurusanDAOTest {

    @Autowired
    private JurusanDAO jurusanDAO;

    @Test
    public void save() {
        Jurusan jurusan = new Jurusan();
        jurusan.setKode("004");
        jurusan.setNama("SK");
        jurusan = jurusanDAO.save(jurusan);
        System.out.println(jurusan);
    }


}
