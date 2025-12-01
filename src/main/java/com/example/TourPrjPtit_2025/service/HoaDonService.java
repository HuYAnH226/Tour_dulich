package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.HoaDon;
import com.example.TourPrjPtit_2025.repository.HoaDonRepository;
import com.example.TourPrjPtit_2025.repository.UserRepository;
import com.example.TourPrjPtit_2025.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TourPrjPtit_2025.entity.User;
import com.example.TourPrjPtit_2025.entity.Tour;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourRepository tourRepository;

    public HoaDon create(Long userId, String tourId, HoaDon hoaDon) {
        // Tìm User và Tour từ database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour không tồn tại"));

        // Gán vào HoaDon
        hoaDon.setUser(user);
        hoaDon.setTour(tour);

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
