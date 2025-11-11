package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.ThanhToan;
import com.example.TourPrjPtit_2025.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThanhToanService {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public List<ThanhToan> getAll() {
        return thanhToanRepository.findAll();
    }

    public Optional<ThanhToan> getById(Long id) {
        return thanhToanRepository.findById(id);
    }

    public List<ThanhToan> getByUser(Long maUser) {
        return thanhToanRepository.findByUser_MaUser(maUser);
    }

    public List<ThanhToan> getByTour(String maTour) {
        return thanhToanRepository.findByTour_MaTour(maTour);
    }

    public ThanhToan save(ThanhToan thanhToan) {
        return thanhToanRepository.save(thanhToan);
    }

    public void delete(Long id) {
        thanhToanRepository.deleteById(id);
    }
}
