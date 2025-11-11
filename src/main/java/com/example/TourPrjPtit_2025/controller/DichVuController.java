package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.DichVu;
import com.example.TourPrjPtit_2025.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dichvu")
public class DichVuController {

    @Autowired
    private DichVuService dichVuService;

    @GetMapping
    public List<DichVu> getAll() {
        return dichVuService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<DichVu> getById(@PathVariable Integer id) {
        return dichVuService.getById(id);
    }

    @GetMapping("/ncc/{maNcc}")
    public List<DichVu> getByNhaCungCap(@PathVariable Integer maNcc) {
        return dichVuService.getByNhaCungCap(maNcc);
    }

    @PostMapping
    public DichVu create(@RequestBody DichVu dichVu) {
        return dichVuService.save(dichVu);
    }

    @PutMapping("/{id}")
    public DichVu update(@PathVariable Integer id, @RequestBody DichVu dichVu) {
        dichVu.setMaDv(id);
        return dichVuService.save(dichVu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        dichVuService.delete(id);
    }
}
