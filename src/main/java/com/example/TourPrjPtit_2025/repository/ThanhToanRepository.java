package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Long> {
    List<ThanhToan> findByUser_MaUser(Long maUser);
    List<ThanhToan> findByTour_MaTour(String maTour);
}
