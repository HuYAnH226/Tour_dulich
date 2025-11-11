package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.Tour;
import com.example.TourPrjPtit_2025.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour")
public class TourController {

    @Autowired
    private TourService service;

    @PostMapping("/create")
    public Tour create(@RequestBody Tour tour) {
        return service.create(tour);
    }

    @GetMapping("/all")
    public List<Tour> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tour getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Tour update(@PathVariable String id, @RequestBody Tour tour) {
        return service.update(id, tour);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return service.delete(id);
    }
}
