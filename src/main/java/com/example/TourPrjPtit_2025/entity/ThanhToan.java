package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "thanh_toan")
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tt")
    private Long maTt;

    // ===== Quan hệ với USER =====
    @ManyToOne
    @JoinColumn(name = "ma_user", referencedColumnName = "ma_user", nullable = false)
    private User user;

    // ===== Quan hệ với TOUR =====
    @ManyToOne
    @JoinColumn(name = "ma_tour", referencedColumnName = "ma_tour", nullable = false)
    private Tour tour;

    @Column(name = "so_tien", nullable = false)
    private Double soTien;

    @Column(name = "phuong_thuc", length = 50)
    private String phuongThuc; // ví dụ: "Chuyển khoản", "Tiền mặt", "Thẻ"

    @Column(name = "ngay_thanh_toan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "trang_thai", length = 50)
    private String trangThai; // ví dụ: "Đã thanh toán", "Chờ xác nhận", "Hủy"

    // ===== Getter - Setter =====
    public Long getMaTt() {
        return maTt;
    }

    public void setMaTt(Long maTt) {
        this.maTt = maTt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
