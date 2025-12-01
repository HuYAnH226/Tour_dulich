package com.example.TourPrjPtit_2025.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @Column(name = "ma_tour", length = 50)
    private String maTour;

    @Column(name = "ten_tour", length = 255)
    private String tenTour;

    @Column(name = "diem_khoi_hanh", length = 255)
    private String diemKhoiHanh;

    // Nhiều Tour —> 1 DiaDiem (MANAGED)
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_diadiem")
    @JsonManagedReference
    private DiaDiem diaDiem;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "so_ngay")
    private Integer soNgay;

    @Column(name = "so_cho_toi_da")
    private Integer soChoToiDa;

    @Column(name = "gia_tour", precision = 12, scale = 2)
    private BigDecimal giaTour;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "so_luong")
    private Integer soLuong;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<LichKhoiHanh> lichKhoiHanhs;

    // GETTER - SETTER
    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(Integer soNgay) {
        this.soNgay = soNgay;
    }

    public Integer getSoChoToiDa() {
        return soChoToiDa;
    }

    public void setSoChoToiDa(Integer soChoToiDa) {
        this.soChoToiDa = soChoToiDa;
    }

    public BigDecimal getGiaTour() {
        return giaTour;
    }

    public void setGiaTour(BigDecimal giaTour) {
        this.giaTour = giaTour;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public List<LichKhoiHanh> getLichKhoiHanhs() {
        return lichKhoiHanhs;
    }

    public void setLichKhoiHanhs(List<LichKhoiHanh> lichKhoiHanhs) {
        this.lichKhoiHanhs = lichKhoiHanhs;
    }
}
