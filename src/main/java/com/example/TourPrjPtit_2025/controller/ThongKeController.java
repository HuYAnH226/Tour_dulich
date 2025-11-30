package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.LichKhoiHanh;
import com.example.TourPrjPtit_2025.entity.Tour;
import com.example.TourPrjPtit_2025.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thong-ke")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ThongKeController {

    @Autowired
    private TourService tourService;

    /**
     * API thống kê doanh thu theo tour
     * URL: GET /api/thong-ke/revenue-by-tour?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/revenue-by-tour")
    public ResponseEntity<List<Map<String, Object>>> getRevenueByTour(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            System.out.println("📊 API Called: /revenue-by-tour");
            System.out.println("📅 Date range: " + startDate + " to " + endDate);

            List<Map<String, Object>> result = tourService.getTourRevenueStatistics(startDate, endDate);

            System.out.println("✅ Returned " + result.size() + " tours");
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            System.err.println("❌ Error in /revenue-by-tour: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * API lấy tất cả tours với trạng thái
     * URL: GET /api/thong-ke/all-tours
     */
    @GetMapping("/all-tours")
    public ResponseEntity<List<Map<String, Object>>> getAllTours() {
        try {
            System.out.println("📋 API Called: /all-tours");

            List<Tour> tours = tourService.getAll();
            List<Map<String, Object>> result = new ArrayList<>();

            for (Tour tour : tours) {
                Map<String, Object> tourData = new HashMap<>();
                tourData.put("maTour", tour.getMaTour());
                tourData.put("tenTour", tour.getTenTour());
                tourData.put("diemKhoiHanh", tour.getDiemKhoiHanh());
                tourData.put("diemDen", tour.getDiaDiem() != null ? tour.getDiaDiem().getTenDd() : "");
                tourData.put("soNgay", tour.getSoNgay());
                tourData.put("soChoToiDa", tour.getSoChoToiDa());
                tourData.put("giaTour", tour.getGiaTour());
                tourData.put("trangThai", tour.getTrangThai());
                tourData.put("ngayTao", tour.getNgayTao());

                // ✅ THÊM SỐ LƯỢNG TOUR
                int soLuong = tour.getSoLuong() != null ? tour.getSoLuong() : 0;
                tourData.put("soLuong", soLuong);

                // Tính tổng chỗ còn lại từ lịch khởi hành (giữ lại cho thông tin)
                int tongChoConLai = 0;
                if (tour.getLichKhoiHanhs() != null) {
                    for (LichKhoiHanh lkh : tour.getLichKhoiHanhs()) {
                        tongChoConLai += lkh.getSoChoConLai();
                    }
                }
                tourData.put("tongChoConLai", tongChoConLai);

                // ✅ LOGIC MỚI: Dựa vào soLuong
                tourData.put("tinhTrang", soLuong > 0 ? "Còn chỗ" : "Hết chỗ");

                result.add(tourData);
            }

            System.out.println("✅ Returned " + result.size() + " tours");
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            System.err.println("❌ Error in /all-tours: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * API cập nhật trạng thái tour (ẩn/hiện)
     * URL: PUT /api/thong-ke/toggle-tour-status/{maTour}
     */
    @PutMapping("/toggle-tour-status/{maTour}")
    public ResponseEntity<Map<String, Object>> toggleTourStatus(@PathVariable String maTour) {
        try {
            System.out.println("🔄 Toggling status for tour: " + maTour);

            Tour tour = tourService.getById(maTour);
            if (tour == null) {
                return ResponseEntity.notFound().build();
            }

            // Đảo ngược trạng thái
            tour.setTrangThai(!tour.getTrangThai());
            tourService.update(maTour, tour);

            Map<String, Object> response = new HashMap<>();
            response.put("maTour", maTour);
            response.put("trangThai", tour.getTrangThai());
            response.put("message", tour.getTrangThai() ? "Tour đã được hiển thị" : "Tour đã được ẩn");

            System.out.println("✅ Tour status updated: " + tour.getTrangThai());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("❌ Error toggling tour status: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * API lấy hóa đơn chi tiết theo tour
     * URL: GET /api/thong-ke/invoices-by-tour?maTour=TOUR123&startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/invoices-by-tour")
    public ResponseEntity<List<Map<String, Object>>> getInvoicesByTour(
            @RequestParam String maTour,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            System.out.println("🧾 API Called: /invoices-by-tour");
            System.out.println("🎯 Tour: " + maTour);
            System.out.println("📅 Date range: " + startDate + " to " + endDate);

            // ✅ TODO: Implement query từ database khi có bảng HoaDon
            // List<HoaDon> hoaDons = hoaDonRepository.findByTourAndDateRange(maTour, startDate, endDate);

            // ✅ TẠM THỜI: Trả về empty array
            List<Map<String, Object>> invoices = new ArrayList<>();

            System.out.println("✅ Returned " + invoices.size() + " invoices (chưa có data hóa đơn)");
            return ResponseEntity.ok(invoices);

        } catch (Exception e) {
            System.err.println("❌ Error in /invoices-by-tour: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}