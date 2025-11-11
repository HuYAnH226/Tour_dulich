package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lich_khoi_hanh")
public class LichKhoiHanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lich")
    private Long idLich;

    // N-1: mỗi lịch thuộc 1 tour
    @ManyToOne
    @JoinColumn(name = "ma_tour", nullable = false)
    private Tour tour;

    @Column(name = "ngay_khoi_hanh")
    private LocalDate ngayKhoiHanh;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "so_cho_con_lai")
    private Integer soChoConLai;

    // GETTER & SETTER
    public Long getIdLich() {
        return idLich;
    }

    public void setIdLich(Long idLich) {
        this.idLich = idLich;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public LocalDate getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(LocalDate ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getSoChoConLai() {
        return soChoConLai;
    }

    public void setSoChoConLai(Integer soChoConLai) {
        this.soChoConLai = soChoConLai;
    }
}
