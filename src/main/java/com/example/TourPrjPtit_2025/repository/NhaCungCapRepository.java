package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Integer> {
    NhaCungCap findByEmail(String email);
}
