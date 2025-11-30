package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.dto.CreateTourRequest;
import com.example.TourPrjPtit_2025.entity.Tour;
import com.example.TourPrjPtit_2025.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.TourPrjPtit_2025.dto.TourDetailResponse;

import java.util.List;

@RestController
@RequestMapping("/api/tour")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class TourController {

    @Autowired
    private TourService service;

    @PostMapping("/create")
    public Tour create(@RequestBody Tour tour) {
        return service.create(tour);
    }

    @PostMapping("/create-full")
    public ResponseEntity<Tour> createFull(@RequestBody CreateTourRequest request) {
        Tour created = service.createFullTour(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/all")
    public List<Tour> getAll() {
        return service.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TourDetailResponse> getById(@PathVariable String id) {
        try {
            System.out.println("🔍 Getting tour detail for: " + id);
            TourDetailResponse detail = service.getTourDetail(id);
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Tour update(@PathVariable String id, @RequestBody Tour tour) {
        return service.update(id, tour);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return service.delete(id);
    }

    // ✅ GIỮ NGUYÊN endpoint này cho detail
    @GetMapping("/{id}/detail")
    public ResponseEntity<TourDetailResponse> getDetail(@PathVariable String id) {
        return ResponseEntity.ok(service.getTourDetail(id));
    }
}