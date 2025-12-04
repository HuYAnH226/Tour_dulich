package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.HoaDon;
import com.example.TourPrjPtit_2025.entity.LichKhoiHanh;
import com.example.TourPrjPtit_2025.entity.Tour;
import com.example.TourPrjPtit_2025.repository.HoaDonRepository;
import com.example.TourPrjPtit_2025.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/thong-ke")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ThongKeController {

    @Autowired
    private TourService tourService;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    /**
     * API thống kê doanh thu theo tour (FILTER THEO NGÀY TẠO TOUR)
     * URL: GET /api/thong-ke/revenue-by-tour?startDate=2024-01-01&endDate=2024-12-31
     */
    @GetMapping("/revenue-by-tour")
    public ResponseEntity<List<Map<String, Object>>> getRevenueByTour(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            System.out.println("📊 API Called: /revenue-by-tour");
            System.out.println("📅 Filter by Tour ngay_tao: " + startDate + " to " + endDate);

            // ⭐ THAY ĐỔI: Dùng query mới - filter theo ngay_tao của Tour
            List<HoaDon> hoaDons = hoaDonRepository.findByTourNgayTaoBetween(startDate, endDate);

            System.out.println("📋 Found " + hoaDons.size() + " invoices");

            if (hoaDons.isEmpty()) {
                System.out.println("⚠️ No invoices found for tours created in this date range");
                return ResponseEntity.ok(Collections.emptyList());
            }

            // Nhóm theo tour và tính tổng doanh thu
            Map<String, Map<String, Object>> tourRevenueMap = new HashMap<>();

            for (HoaDon hd : hoaDons) {
                String maTour = hd.getTour().getMaTour();

                // Tính doanh thu = soKhach * giaTour
                BigDecimal doanhThu = hd.getTour().getGiaTour()
                        .multiply(BigDecimal.valueOf(hd.getSoKhach()));

                if (tourRevenueMap.containsKey(maTour)) {
                    Map<String, Object> tourData = tourRevenueMap.get(maTour);
                    BigDecimal currentRevenue = (BigDecimal) tourData.get("tongDoanhThu");
                    tourData.put("tongDoanhThu", currentRevenue.add(doanhThu));

                    // Cập nhật tổng số khách
                    int currentSoKhach = (int) tourData.get("tongSoKhach");
                    tourData.put("tongSoKhach", currentSoKhach + hd.getSoKhach());
                } else {
                    Map<String, Object> tourData = new HashMap<>();
                    tourData.put("maTour", maTour);
                    tourData.put("tenTour", hd.getTour().getTenTour());
                    tourData.put("ngayTao", hd.getTour().getNgayTao());
                    tourData.put("tongDoanhThu", doanhThu);
                    tourData.put("tongSoKhach", hd.getSoKhach());
                    tourRevenueMap.put(maTour, tourData);
                }
            }

            // Chuyển sang List và sắp xếp theo doanh thu giảm dần
            List<Map<String, Object>> result = new ArrayList<>(tourRevenueMap.values());
            result.sort((a, b) -> {
                BigDecimal revenueA = (BigDecimal) a.get("tongDoanhThu");
                BigDecimal revenueB = (BigDecimal) b.get("tongDoanhThu");
                return revenueB.compareTo(revenueA);
            });

            System.out.println("✅ Returned " + result.size() + " tours");
            System.out.println("📊 Sample data: " + (result.isEmpty() ? "empty" : result.get(0)));

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            System.err.println("❌ Error in /revenue-by-tour: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.emptyList());
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
     * ⭐ API lấy hóa đơn chi tiết theo tour (KHÔNG FILTER THEO DATE)
     * URL: GET /api/thong-ke/invoices-by-tour?maTour=TOUR123
     */
    @GetMapping("/invoices-by-tour")
    public ResponseEntity<List<Map<String, Object>>> getInvoicesByTour(
            @RequestParam String maTour) {

        try {
            System.out.println("🧾 API Called: /invoices-by-tour");
            System.out.println("🎯 Tour: " + maTour);

            // ⭐ Lấy TẤT CẢ hóa đơn của tour (không filter theo date)
            List<HoaDon> hoaDons = hoaDonRepository.findByTourMaTour(maTour);

            System.out.println("📋 Found " + hoaDons.size() + " invoices");

            if (hoaDons.isEmpty()) {
                System.out.println("⚠️ No invoices found for this tour");
                return ResponseEntity.ok(Collections.emptyList());
            }

            List<Map<String, Object>> invoices = hoaDons.stream().map(hd -> {
                Map<String, Object> map = new HashMap<>();
                map.put("idHoaDon", hd.getMaHd());
                map.put("tenKhachHang", hd.getUser().getHoTen());
                map.put("ngayGioKhoiHanh", hd.getNgayLapHD());
                map.put("soLuongKhach", hd.getSoKhach());

                // Tính tổng tiền = soKhach * giaTour
                BigDecimal tongTien = hd.getTour().getGiaTour()
                        .multiply(BigDecimal.valueOf(hd.getSoKhach()));
                map.put("tongTien", tongTien);

                return map;
            }).collect(Collectors.toList());

            System.out.println("✅ Returned " + invoices.size() + " invoices");
            return ResponseEntity.ok(invoices);

        } catch (Exception e) {
            System.err.println("❌ Error in /invoices-by-tour: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }
}