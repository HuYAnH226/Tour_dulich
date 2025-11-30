package com.example.TourPrjPtit_2025.dto;

import java.math.BigDecimal;

public class ThongKeTourDTO {

    private String maTour;
    private String tenTour;
    private String diemKhoiHanh;
    private String diemDen;
    private Double soKhachTrungBinh;
    private BigDecimal tongDoanhThu;

    // ===== CONSTRUCTOR =====
    public ThongKeTourDTO(String maTour, String tenTour, String diemKhoiHanh,
                          String diemDen, Double soKhachTrungBinh, BigDecimal tongDoanhThu) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemDen = diemDen;
        this.soKhachTrungBinh = soKhachTrungBinh;
        this.tongDoanhThu = tongDoanhThu;
    }

    // ===== GETTER =====
    public String getMaTour() {
        return maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public Double getSoKhachTrungBinh() {
        return soKhachTrungBinh;
    }

    public BigDecimal getTongDoanhThu() {
        return tongDoanhThu;
    }

    // ===== SETTER =====
    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public void setSoKhachTrungBinh(Double soKhachTrungBinh) {
        this.soKhachTrungBinh = soKhachTrungBinh;
    }

    public void setTongDoanhThu(BigDecimal tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }
}