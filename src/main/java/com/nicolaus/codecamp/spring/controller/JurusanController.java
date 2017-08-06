package com.nicolaus.codecamp.spring.controller;

import com.nicolaus.codecamp.spring.dao.JurusanDAO;
import com.nicolaus.codecamp.spring.entity.Jurusan;
import com.nicolaus.codecamp.spring.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JurusanController {
    @Autowired
    private JurusanDAO jurusanDAO;

    @PostMapping("/jurusan")
    public Map<String, Object> save(@RequestBody Jurusan param){
        Jurusan jurusan = jurusanDAO.save(param);

        Map<String, Object> response = new HashMap<>();
        response.put("data", jurusan);
        response.put("status", HttpStatus.OK);
        return response;
    }

    @GetMapping("/jurusans")
    public Map<String, Object> find(@RequestParam(value = "kode", required = false)String kode,
                                    @RequestParam(value = "nama", required = false)String nama){
        Jurusan param = new Jurusan();
        param.setKode(kode);
        param.setNama(nama);

        Map<String, Object> response = new HashMap<>();
        response.put("kode", jurusanDAO.find(param, 0,10));
        response.put("status", HttpStatus.OK);
        return response;
    }
}
