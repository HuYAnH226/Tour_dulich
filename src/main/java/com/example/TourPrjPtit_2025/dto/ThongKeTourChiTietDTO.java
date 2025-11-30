package com.example.TourPrjPtit_2025.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ThongKeTourChiTietDTO {

    private Long idHoaDon;
    private String tenKhachHang;
    private Integer soLuongKhach;
    private BigDecimal tongTien;

    // ===== CONSTRUCTOR =====
    public ThongKeTourChiTietDTO(Long idHoaDon, String tenKhachHang,
                                 Integer soLuongKhach, BigDecimal tongTien) {
        this.idHoaDon = idHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.soLuongKhach = soLuongKhach;
        this.tongTien = tongTien;
    }

    // ===== GETTER =====
    public Long getIdHoaDon() {
        return idHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }


    public Integer getSoLuongKhach() {
        return soLuongKhach;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    // ===== SETTER =====
    public void setIdHoaDon(Long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }


    public void setSoLuongKhach(Integer soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
}