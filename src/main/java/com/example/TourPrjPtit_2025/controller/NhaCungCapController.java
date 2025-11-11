package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.NhaCungCap;
import com.example.TourPrjPtit_2025.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhacungcap")
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nhaCungCapService;

    @GetMapping
    public List<NhaCungCap> getAll() {
        return nhaCungCapService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<NhaCungCap> getById(@PathVariable Integer id) {
        return nhaCungCapService.getById(id);
    }

    @GetMapping("/email/{email}")
    public NhaCungCap getByEmail(@PathVariable String email) {
        return nhaCungCapService.getByEmail(email);
    }

    @PostMapping
    public NhaCungCap create(@RequestBody NhaCungCap nhaCungCap) {
        return nhaCungCapService.save(nhaCungCap);
    }

    @PutMapping("/{id}")
    public NhaCungCap update(@PathVariable Integer id, @RequestBody NhaCungCap nhaCungCap) {
        nhaCungCap.setMaNcc(id);
        return nhaCungCapService.save(nhaCungCap);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        nhaCungCapService.delete(id);
    }
}
