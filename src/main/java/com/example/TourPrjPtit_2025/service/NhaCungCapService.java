package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.NhaCungCap;
import com.example.TourPrjPtit_2025.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    public List<NhaCungCap> getAll() {
        return nhaCungCapRepository.findAll();
    }

    public Optional<NhaCungCap> getById(Integer id) {
        return nhaCungCapRepository.findById(id);
    }

    public NhaCungCap getByEmail(String email) {
        return nhaCungCapRepository.findByEmail(email);
    }

    public NhaCungCap save(NhaCungCap nhaCungCap) {
        return nhaCungCapRepository.save(nhaCungCap);
    }

    public void delete(Integer id) {
        nhaCungCapRepository.deleteById(id);
    }
}
