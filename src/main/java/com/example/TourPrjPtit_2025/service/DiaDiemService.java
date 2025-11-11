package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.DiaDiem;
import com.example.TourPrjPtit_2025.repository.DiaDiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaDiemService {

    @Autowired
    private DiaDiemRepository repository;

    public DiaDiem create(DiaDiem dd) {
        return repository.save(dd);
    }

    public List<DiaDiem> getAll() {
        return repository.findAll();
    }

    public DiaDiem getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DiaDiem update(Long id, DiaDiem updated) {
        return repository.findById(id)
                .map(dd -> {
                    dd.setTenDd(updated.getTenDd());
                    dd.setHinhAnh(updated.getHinhAnh());
                    return repository.save(dd);
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
