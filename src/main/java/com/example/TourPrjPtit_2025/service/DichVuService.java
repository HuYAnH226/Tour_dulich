package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.DichVu;
import com.example.TourPrjPtit_2025.repository.DichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuService {

    @Autowired
    private DichVuRepository dichVuRepository;

    public List<DichVu> getAll() {
        return dichVuRepository.findAll();
    }

    public Optional<DichVu> getById(Integer id) {
        return dichVuRepository.findById(id);
    }

    public List<DichVu> getByNhaCungCap(Integer maNcc) {
        return dichVuRepository.findByNhaCungCap_MaNcc(maNcc);
    }

    public DichVu save(DichVu dichVu) {
        return dichVuRepository.save(dichVu);
    }

    public void delete(Integer id) {
        dichVuRepository.deleteById(id);
    }
}
