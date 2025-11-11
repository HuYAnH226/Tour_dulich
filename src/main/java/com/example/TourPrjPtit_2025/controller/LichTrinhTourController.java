package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.LichTrinhTour;
import com.example.TourPrjPtit_2025.service.LichTrinhTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lichtrinh")
public class LichTrinhTourController {

    @Autowired
    private LichTrinhTourService lichTrinhTourService;

    @GetMapping
    public List<LichTrinhTour> getAll() {
        return lichTrinhTourService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<LichTrinhTour> getById(@PathVariable Long id) {
        return lichTrinhTourService.getById(id);
    }

    @GetMapping("/tour/{maTour}")
    public List<LichTrinhTour> getByMaTour(@PathVariable String maTour) {
        return lichTrinhTourService.getByMaTour(maTour);
    }

    @PostMapping
    public LichTrinhTour create(@RequestBody LichTrinhTour lichTrinhTour) {
        return lichTrinhTourService.save(lichTrinhTour);
    }

    @PutMapping("/{id}")
    public LichTrinhTour update(@PathVariable Long id, @RequestBody LichTrinhTour lichTrinhTour) {
        lichTrinhTour.setIdLichTrinh(id);
        return lichTrinhTourService.save(lichTrinhTour);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        lichTrinhTourService.delete(id);
    }
}
