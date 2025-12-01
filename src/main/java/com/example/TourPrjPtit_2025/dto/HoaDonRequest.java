package com.example.TourPrjPtit_2025.dto;

import java.time.LocalDateTime;

public class HoaDonRequest {
    private LocalDateTime ngayLapHD;
    private int soKhach;
    private String diemDi;

    // Constructor không tham số
    public HoaDonRequest() {
    }

    // Constructor đầy đủ tham số
    public HoaDonRequest(LocalDateTime ngayLapHD, int soKhach, String diemDi) {
        this.ngayLapHD = ngayLapHD;
        this.soKhach = soKhach;
        this.diemDi = diemDi;
    }

    // Getter & Setter
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
