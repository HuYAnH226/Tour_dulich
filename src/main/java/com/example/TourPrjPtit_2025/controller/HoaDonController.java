package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.HoaDon;
import com.example.TourPrjPtit_2025.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @PostMapping("/create")
    public HoaDon createHoaDon(@RequestBody HoaDon hoaDon) {
        return hoaDonService.create(hoaDon);
    }

    @GetMapping("/all")
    public List<HoaDon> getAll() {
        return hoaDonService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<HoaDon> getById(@PathVariable Long id) {
        return hoaDonService.getById(id);
    }

    @PutMapping("/{id}")
    public HoaDon update(@PathVariable Long id, @RequestBody HoaDon hoaDon) {
        return hoaDonService.update(id, hoaDon);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return hoaDonService.delete(id);
    }
}
