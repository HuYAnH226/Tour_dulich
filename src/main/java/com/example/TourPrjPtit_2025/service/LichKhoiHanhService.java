package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.LichKhoiHanh;
import com.example.TourPrjPtit_2025.repository.LichKhoiHanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LichKhoiHanhService {

    @Autowired
    private LichKhoiHanhRepository repository;

    public LichKhoiHanh create(LichKhoiHanh lich) {
        return repository.save(lich);
    }

    public List<LichKhoiHanh> getAll() {
        return repository.findAll();
    }

    public Optional<LichKhoiHanh> getById(Long id) {
        return repository.findById(id);
    }

    public LichKhoiHanh update(Long id, LichKhoiHanh updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNgayKhoiHanh(updated.getNgayKhoiHanh());
                    existing.setNgayKetThuc(updated.getNgayKetThuc());
                    existing.setSoChoConLai(updated.getSoChoConLai());
                    existing.setTour(updated.getTour());
                    return repository.save(existing);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
