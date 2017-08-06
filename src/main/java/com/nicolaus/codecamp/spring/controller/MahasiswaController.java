package com.nicolaus.codecamp.spring.controller;


import com.nicolaus.codecamp.spring.dao.MahasiswaDAO;
import com.nicolaus.codecamp.spring.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MahasiswaController {
    @Autowired
    private MahasiswaDAO mahasiswaDAO;

    @PostMapping("/mahasiswa")
    public Map<String, Object> save(@RequestBody Mahasiswa param){
        Mahasiswa mahasiswa = mahasiswaDAO.save(param);

        Map<String, Object> response = new HashMap<>();
        response.put("data", mahasiswa);
        response.put("status", HttpStatus.OK);
        return response;
    }

    @GetMapping("/mahasiswas")
    public Map<String, Object> find(@RequestParam(value = "nim", required = false)String nim,
                                    @RequestParam(value = "nama", required = false)String nama){
        Mahasiswa param = new Mahasiswa();
        param.setNim(nim);
        param.setNama(nama);

        Map<String, Object> response = new HashMap<>();
        response.put("nim", mahasiswaDAO.find(param, 0,10));
        response.put("status", HttpStatus.OK);
        return response;
    }
}
