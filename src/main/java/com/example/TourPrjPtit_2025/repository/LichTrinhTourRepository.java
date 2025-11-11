package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.LichTrinhTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichTrinhTourRepository extends JpaRepository<LichTrinhTour, Long> {
    List<LichTrinhTour> findByTour_MaTour(String maTour);
}
