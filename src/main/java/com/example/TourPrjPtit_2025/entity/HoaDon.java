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

    // TOUR (n-1)
    @ManyToOne
    @JoinColumn(name = "ma_tour", nullable = false)
    private Tour tour;

    @Column(name = "ngay_lap_hd")
    private LocalDateTime ngayLapHD;

    @Column(name = "so_khach")
    private int soKhach;


    @Column(name = "diem_di", length = 50)
    private String diemDi;

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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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



    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }
}
