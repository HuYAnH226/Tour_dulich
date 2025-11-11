package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dia_diem")
public class DiaDiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dd")
    private Long maDd;

    @Column(name = "ten_dd", length = 255)
    private String tenDd;

    @Column(name = "hinh_anh", length = 500)
    private String hinhAnh;

    // 1 địa điểm có thể nằm trong nhiều tour
    @OneToMany(mappedBy = "diaDiem")
    private List<Tour> tours;

    // ===== GETTER - SETTER =====

    public Long getMaDd() {
        return maDd;
    }

    public void setMaDd(Long maDd) {
        this.maDd = maDd;
    }

    public String getTenDd() {
        return tenDd;
    }

    public void setTenDd(String tenDd) {
        this.tenDd = tenDd;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
