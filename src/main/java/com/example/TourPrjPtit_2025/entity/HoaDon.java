package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hd")
    private Long maHd;

    // USER (n-1)
    @ManyToOne
    @JoinColumn(name = "ma_user", nullable = false)
    private User user;

    // LICH_KHOI_HANH (n-1)
    @ManyToOne
    @JoinColumn(name = "id_lich", nullable = false)
    private LichKhoiHanh lichKhoiHanh;


    @Column(name = "ngay_lap_hd")
    private LocalDateTime ngayLapHD;

    @Column(name = "so_khach")
    private int soKhach;

    @Column(name = "tong_tien", precision = 10, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "tien_dat_coc", precision = 10, scale = 2)
    private BigDecimal tienDatCoc;

    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    // GETTER - SETTER

    public Long getMaHd() {
        return maHd;
    }

    public void setMaHd(Long maHd) {
        this.maHd = maHd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LichKhoiHanh getLichKhoiHanh() {
        return lichKhoiHanh;
    }

    public void setLichKhoiHanh(LichKhoiHanh lichKhoiHanh) {
        this.lichKhoiHanh = lichKhoiHanh;
    }

    public LocalDateTime getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDateTime ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public int getSoKhach() {
        return soKhach;
    }

    public void setSoKhach(int soKhach) {
        this.soKhach = soKhach;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(BigDecimal tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
