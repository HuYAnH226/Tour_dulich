package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.ThanhToan;
import com.example.TourPrjPtit_2025.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/thanhtoan")
public class ThanhToanController {

    @Autowired
    private ThanhToanService thanhToanService;

    @GetMapping
    public List<ThanhToan> getAll() {
        return thanhToanService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ThanhToan> getById(@PathVariable Long id) {
        return thanhToanService.getById(id);
    }

    @GetMapping("/user/{maUser}")
    public List<ThanhToan> getByUser(@PathVariable Long maUser) {
        return thanhToanService.getByUser(maUser);
    }

    @GetMapping("/tour/{maTour}")
    public List<ThanhToan> getByTour(@PathVariable String maTour) {
        return thanhToanService.getByTour(maTour);
    }

    @PostMapping
    public ThanhToan create(@RequestBody ThanhToan thanhToan) {
        return thanhToanService.save(thanhToan);
    }

    @PutMapping("/{id}")
    public ThanhToan update(@PathVariable Long id, @RequestBody ThanhToan thanhToan) {
        thanhToan.setMaTt(id);
        return thanhToanService.save(thanhToan);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        thanhToanService.delete(id);
    }
}
