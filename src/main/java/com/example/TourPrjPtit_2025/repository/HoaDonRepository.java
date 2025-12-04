package com.example.TourPrjPtit_2025.repository;

import com.example.TourPrjPtit_2025.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    /**
     * ⭐ MỚI: Lấy tất cả hóa đơn của 1 tour (không filter theo date)
     * Dùng cho API: /api/thong-ke/invoices-by-tour
     */
    @Query("SELECT h FROM HoaDon h WHERE h.tour.maTour = :maTour ORDER BY h.ngayLapHD DESC")
    List<HoaDon> findByTourMaTour(@Param("maTour") String maTour);

    /**
     * ⭐ Lấy hóa đơn theo ngày tạo tour (ngay_tao trong bảng tour)
     * Dùng cho API: /api/thong-ke/revenue-by-tour
     */
    @Query("SELECT h FROM HoaDon h WHERE h.tour.ngayTao BETWEEN :startDate AND :endDate ORDER BY h.tour.ngayTao DESC")
    List<HoaDon> findByTourNgayTaoBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    /**
     * Lấy tất cả hóa đơn trong khoảng thời gian (theo ngày lập hóa đơn)
     * Giữ lại nếu cần dùng cho mục đích khác
     */
    @Query("SELECT h FROM HoaDon h WHERE h.ngayLapHD BETWEEN :startDate AND :endDate ORDER BY h.ngayLapHD DESC")
    List<HoaDon> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    /**
     * Lấy hóa đơn theo mã tour và khoảng thời gian
     * Giữ lại nếu cần dùng cho mục đích khác
     */
    @Query("SELECT h FROM HoaDon h WHERE h.tour.maTour = :maTour " +
            "AND h.ngayLapHD BETWEEN :startDate AND :endDate " +
            "ORDER BY h.ngayLapHD DESC")
    List<HoaDon> findByTourAndDateRange(
            @Param("maTour") String maTour,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}