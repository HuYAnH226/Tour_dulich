package com.example.TourPrjPtit_2025.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TourDetailResponse {

    // thông tin tour
    private String maTour;
    private String tenTour;
    private String diemKhoiHanh;
    private String tenDiaDiemDen;
    private String moTa;
    private Integer soNgay;
    private Integer soChoToiDa;
    private BigDecimal giaTour;
    private Boolean trangThai;

    private List<LichTrinhDto> lichTrinh;
    private List<LichKhoiHanhDto> lichKhoiHanh;

    public void setNgayTao(LocalDate ngayTao) {
    }

    public void setAnhTour(String anhTour) {
    }

    public static class LichTrinhDto {
        private String loai;      // dd/dv
        private String tenDiaDiem;
        private String tenDichVu;
        private String moTa;

        // getters/setters
        public String getLoai() { return loai; }
        public void setLoai(String loai) { this.loai = loai; }
        public String getTenDiaDiem() { return tenDiaDiem; }
        public void setTenDiaDiem(String tenDiaDiem) { this.tenDiaDiem = tenDiaDiem; }
        public String getTenDichVu() { return tenDichVu; }
        public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }
        public String getMoTa() { return moTa; }
        public void setMoTa(String moTa) { this.moTa = moTa; }
    }

    public static class LichKhoiHanhDto {
        private Long idLich;
        private LocalDate ngayKhoiHanh;
        private LocalDate ngayKetThuc;
        private Integer soChoConLai;

        // getters/setters
        public Long getIdLich() { return idLich; }
        public void setIdLich(Long idLich) { this.idLich = idLich; }
        public LocalDate getNgayKhoiHanh() { return ngayKhoiHanh; }
        public void setNgayKhoiHanh(LocalDate ngayKhoiHanh) { this.ngayKhoiHanh = ngayKhoiHanh; }
        public LocalDate getNgayKetThuc() { return ngayKetThuc; }
        public void setNgayKetThuc(LocalDate ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
        public Integer getSoChoConLai() { return soChoConLai; }
        public void setSoChoConLai(Integer soChoConLai) { this.soChoConLai = soChoConLai; }
    }

    // getters/setters cho TourDetailResponse
    public String getMaTour() { return maTour; }
    public void setMaTour(String maTour) { this.maTour = maTour; }
    public String getTenTour() { return tenTour; }
    public void setTenTour(String tenTour) { this.tenTour = tenTour; }
    public String getDiemKhoiHanh() { return diemKhoiHanh; }
    public void setDiemKhoiHanh(String diemKhoiHanh) { this.diemKhoiHanh = diemKhoiHanh; }
    public String getTenDiaDiemDen() { return tenDiaDiemDen; }
    public void setTenDiaDiemDen(String tenDiaDiemDen) { this.tenDiaDiemDen = tenDiaDiemDen; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public Integer getSoNgay() { return soNgay; }
    public void setSoNgay(Integer soNgay) { this.soNgay = soNgay; }
    public Integer getSoChoToiDa() { return soChoToiDa; }
    public void setSoChoToiDa(Integer soChoToiDa) { this.soChoToiDa = soChoToiDa; }
    public BigDecimal getGiaTour() { return giaTour; }
    public void setGiaTour(BigDecimal giaTour) { this.giaTour = giaTour; }
    public Boolean getTrangThai() { return trangThai; }
    public void setTrangThai(Boolean trangThai) { this.trangThai = trangThai; }
    public List<LichTrinhDto> getLichTrinh() { return lichTrinh; }
    public void setLichTrinh(List<LichTrinhDto> lichTrinh) { this.lichTrinh = lichTrinh; }
    public List<LichKhoiHanhDto> getLichKhoiHanh() { return lichKhoiHanh; }
    public void setLichKhoiHanh(List<LichKhoiHanhDto> lichKhoiHanh) { this.lichKhoiHanh = lichKhoiHanh; }
}
