package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
    List<DichVu> findByNhaCungCap_MaNcc(Integer maNcc);
}
