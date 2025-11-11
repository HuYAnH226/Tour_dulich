package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lich_trinh_tour")
public class LichTrinhTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lich_trinh")
    private Long idLichTrinh;

    // ========== Quan hệ với TOUR ==========
    @ManyToOne
    @JoinColumn(name = "ma_tour", referencedColumnName = "ma_tour", nullable = false)
    private Tour tour;

    // ========== Quan hệ với DICH_VU ==========
    @ManyToOne
    @JoinColumn(name = "ma_dv", referencedColumnName = "ma_dv", nullable = true)
    private DichVu dichVu;

    // ========== Quan hệ với DIA_DIEM ==========
    @ManyToOne
    @JoinColumn(name = "ma_dd", referencedColumnName = "ma_dd", nullable = true)
    private DiaDiem diaDiem;

    @Column(name = "loai", length = 50)
    private String loai; // "dd" hoặc "dv"

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    // ===== Getter - Setter =====
    public Long getIdLichTrinh() {
        return idLichTrinh;
    }

    public void setIdLichTrinh(Long idLichTrinh) {
        this.idLichTrinh = idLichTrinh;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
