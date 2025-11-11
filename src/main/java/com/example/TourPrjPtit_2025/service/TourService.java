package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.Tour;
import com.example.TourPrjPtit_2025.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository repository;

    public Tour create(Tour tour) {
        return repository.save(tour);
    }

    public List<Tour> getAll() {
        return repository.findAll();
    }

    public Tour getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Tour update(String id, Tour updated) {

        return repository.findById(id)
                .map(t -> {
                    t.setTenTour(updated.getTenTour());
                    t.setDiemKhoiHanh(updated.getDiemKhoiHanh());
                    t.setDiaDiem(updated.getDiaDiem());
                    t.setMoTa(updated.getMoTa());
                    t.setSoNgay(updated.getSoNgay());
                    t.setSoChoToiDa(updated.getSoChoToiDa());
                    t.setGiaTour(updated.getGiaTour());
                    t.setTrangThai(updated.getTrangThai());
                    return repository.save(t);
                })
                .orElse(null);
    }

    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
