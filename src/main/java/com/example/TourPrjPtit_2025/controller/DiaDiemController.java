package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.DiaDiem;
import com.example.TourPrjPtit_2025.service.DiaDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diadiem")
public class DiaDiemController {

    @Autowired
    private DiaDiemService service;

    @PostMapping("/create")
    public DiaDiem create(@RequestBody DiaDiem dd) {
        return service.create(dd);
    }

    @GetMapping("/all")
    public List<DiaDiem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DiaDiem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public DiaDiem update(@PathVariable Long id, @RequestBody DiaDiem dd) {
        return service.update(id, dd);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
