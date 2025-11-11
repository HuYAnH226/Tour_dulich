package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.LichTrinhTour;
import com.example.TourPrjPtit_2025.repository.LichTrinhTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LichTrinhTourService {

    @Autowired
    private LichTrinhTourRepository lichTrinhTourRepository;

    public List<LichTrinhTour> getAll() {
        return lichTrinhTourRepository.findAll();
    }

    public Optional<LichTrinhTour> getById(Long id) {
        return lichTrinhTourRepository.findById(id);
    }

    public List<LichTrinhTour> getByMaTour(String maTour) {
        return lichTrinhTourRepository.findByTour_MaTour(maTour);
    }

    public LichTrinhTour save(LichTrinhTour lichTrinhTour) {
        return lichTrinhTourRepository.save(lichTrinhTour);
    }

    public void delete(Long id) {
        lichTrinhTourRepository.deleteById(id);
    }
}
