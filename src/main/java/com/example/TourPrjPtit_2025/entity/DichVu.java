package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dich_vu")
public class DichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dv")
    private Integer maDv;

    @Column(name = "ten_dv", nullable = false, length = 100)
    private String tenDv;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    // ========== Quan hệ với NHA_CUNG_CAP ==========
    @ManyToOne
    @JoinColumn(name = "ma_ncc", referencedColumnName = "ma_ncc", nullable = false)
    private NhaCungCap nhaCungCap;

    // ===== Getter - Setter =====
    public Integer getMaDv() {
        return maDv;
    }

    public void setMaDv(Integer maDv) {
        this.maDv = maDv;
    }

    public String getTenDv() {
        return tenDv;
    }

    public void setTenDv(String tenDv) {
        this.tenDv = tenDv;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
}
