package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTenDangNhap(String tenDangNhap);
}
