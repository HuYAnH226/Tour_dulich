package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, String> {

    // ✅ FILTER TOUR THEO NGÀY TẠO (KHOẢNG NGÀY)
    @Query("SELECT t FROM Tour t WHERE t.ngayTao BETWEEN :startDate AND :endDate ORDER BY t.ngayTao DESC")
    List<Tour> findByNgayTaoBetween(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);

    // ✅ FILTER TOUR TỪ NGÀY CỤ THỂ
    @Query("SELECT t FROM Tour t WHERE t.ngayTao >= :startDate ORDER BY t.ngayTao DESC")
    List<Tour> findByNgayTaoAfter(@Param("startDate") LocalDate startDate);

    // ✅ FILTER TOUR ĐẾN NGÀY CỤ THỂ
    @Query("SELECT t FROM Tour t WHERE t.ngayTao <= :endDate ORDER BY t.ngayTao DESC")
    List<Tour> findByNgayTaoBefore(@Param("endDate") LocalDate endDate);

    // ✅ LẤY TOUR THEO NGÀY TẠO CỤ THỂ
    @Query("SELECT t FROM Tour t WHERE t.ngayTao = :ngayTao ORDER BY t.maTour")
    List<Tour> findByNgayTao(@Param("ngayTao") LocalDate ngayTao);

    // ✅ LẤY TOUR MỚI NHẤT (SẮP XẾP THEO NGÀY TẠO GIẢM DẦN)
    List<Tour> findByOrderByNgayTaoDesc();

    // ✅ LẤY TOUR CŨ NHẤT (SẮP XẾP THEO NGÀY TẠO TĂNG DẦN)
    List<Tour> findByOrderByNgayTaoAsc();
}