package com.example.TourPrjPtit_2025.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "nha_cung_cap")
public class NhaCungCap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ncc")
    private Integer maNcc;

    @Column(name = "ten_ncc", nullable = false, length = 100)
    private String tenNcc;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "dia_chi", columnDefinition = "TEXT")
    private String diaChi;

    // ===== Quan hệ 1-n với DICH_VU =====
    @OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DichVu> danhSachDichVu;

    // ===== Getter - Setter =====
    public Integer getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(Integer maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public List<DichVu> getDanhSachDichVu() {
        return danhSachDichVu;
    }

    public void setDanhSachDichVu(List<DichVu> danhSachDichVu) {
        this.danhSachDichVu = danhSachDichVu;
    }
}
