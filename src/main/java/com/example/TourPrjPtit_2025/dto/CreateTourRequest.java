package com.example.TourPrjPtit_2025.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreateTourRequest {

    private String maTour;
    private String tenTour;
    private String diemKhoiHanh;
    private Long diaDiemId;
    private String moTa;
    private Integer soNgay;
    private Integer soChoToiDa;
    private BigDecimal giaTour;
    private Boolean trangThai;
    private Integer soLuong;

    private List<LichTrinhItem> lichTrinh;
    private List<LichKhoiHanhItem> lichKhoiHanh;
    private String anhTour;

    // Getters and Setters

    public String getAnhTour() {
        return anhTour;
    }

    public void setAnhTour(String anhTour) {
        this.anhTour = anhTour;
    }

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

    public Long getDiaDiemId() { // ✅ LONG CHỮ HOA
        return diaDiemId;
    }

    public void setDiaDiemId(Long diaDiemId) { // ✅ LONG CHỮ HOA
        this.diaDiemId = diaDiemId;
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

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public List<LichTrinhItem> getLichTrinh() {
        return lichTrinh;
    }

    public void setLichTrinh(List<LichTrinhItem> lichTrinh) {
        this.lichTrinh = lichTrinh;
    }

    public List<LichKhoiHanhItem> getLichKhoiHanh() {
        return lichKhoiHanh;
    }

    public void setLichKhoiHanh(List<LichKhoiHanhItem> lichKhoiHanh) {
        this.lichKhoiHanh = lichKhoiHanh;
    }

    // ===== INNER CLASSES =====

    public static class LichTrinhItem {
        private Long diaDiemId; // ✅ LONG CHỮ HOA
        private Integer dichVuId;
        private String loai;
        private String moTa;

        public Long getDiaDiemId() {
            return diaDiemId;
        }

        public void setDiaDiemId(Long diaDiemId) {
            this.diaDiemId = diaDiemId;
        }

        public Integer getDichVuId() {
            return dichVuId;
        }

        public void setDichVuId(Integer dichVuId) {
            this.dichVuId = dichVuId;
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

    public static class LichKhoiHanhItem {
        private LocalDate ngayKhoiHanh;
        private LocalDate ngayKetThuc;
        private Integer soCho;

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

        public Integer getSoCho() {
            return soCho;
        }

        public void setSoCho(Integer soCho) {
            this.soCho = soCho;
        }
    }
}