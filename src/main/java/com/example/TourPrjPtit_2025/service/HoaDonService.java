package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.HoaDon;
import com.example.TourPrjPtit_2025.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    public HoaDon create(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    public Optional<HoaDon> getById(Long id) {
        return hoaDonRepository.findById(id);
    }

    public HoaDon update(Long id, HoaDon updated) {
        return hoaDonRepository.findById(id)
                .map(existing -> {
                    existing.setNgayLapHD(updated.getNgayLapHD());
                    existing.setSoKhach(updated.getSoKhach());
                    existing.setTrangThai(updated.getTrangThai());
                    existing.setUser(updated.getUser());
                    return hoaDonRepository.save(existing);
                }).orElse(null);
    }

    public boolean delete(Long id) {
        if (hoaDonRepository.existsById(id)) {
            hoaDonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
