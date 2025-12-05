package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.dto.CreateTourRequest;
import com.example.TourPrjPtit_2025.entity.Tour;
import com.example.TourPrjPtit_2025.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.TourPrjPtit_2025.dto.TourDetailResponse;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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


    @GetMapping("/{id}/detail")
    public ResponseEntity<TourDetailResponse> getDetail(@PathVariable String id) {
        return ResponseEntity.ok(service.getTourDetail(id));
    }
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File trống");
            }

            String uploadDir = "uploads/tours/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


            String relativePath = "/uploads/tours/" + fileName;
            return ResponseEntity.ok(relativePath);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi upload: " + e.getMessage());
        }
    }

}