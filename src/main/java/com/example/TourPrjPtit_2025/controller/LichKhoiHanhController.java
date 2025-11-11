package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.LichKhoiHanh;
import com.example.TourPrjPtit_2025.service.LichKhoiHanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lichkhoihanh")
public class LichKhoiHanhController {

    @Autowired
    private LichKhoiHanhService service;

    @PostMapping("/create")
    public LichKhoiHanh create(@RequestBody LichKhoiHanh lich) {
        return service.create(lich);
    }

    @GetMapping("/all")
    public List<LichKhoiHanh> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<LichKhoiHanh> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public LichKhoiHanh update(
            @PathVariable Long id,
            @RequestBody LichKhoiHanh lich
    ) {
        return service.update(id, lich);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
