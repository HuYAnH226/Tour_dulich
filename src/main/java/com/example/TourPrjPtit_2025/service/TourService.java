package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.dto.CreateTourRequest;
import com.example.TourPrjPtit_2025.entity.*;
import com.example.TourPrjPtit_2025.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TourPrjPtit_2025.dto.TourDetailResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.time.LocalDate;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private LichTrinhTourRepository lichTrinhRepo;

    @Autowired
    private LichKhoiHanhRepository lichKhoiHanhRepo;

    @Autowired
    private DiaDiemRepository diaDiemRepo;

    @Autowired
    private DichVuRepository dichVuRepo;

    private String generateRandomTourCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder("TOUR");

        for (int i = 0; i < 6; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }

        String generatedCode = code.toString();

        if (tourRepository.existsById(generatedCode)) {
            return generateRandomTourCode();
        }

        return generatedCode;
    }

    @Transactional
    public Tour createFullTour(CreateTourRequest req) {
        try {
            System.out.println("=== BẮT ĐẦU TẠO TOUR ===");

            String maTour;
            if (req.getMaTour() == null || req.getMaTour().trim().isEmpty()) {
                maTour = generateRandomTourCode();
                System.out.println("Tự động tạo mã tour: " + maTour);
            } else {
                maTour = req.getMaTour().trim();
                if (tourRepository.existsById(maTour)) {
                    throw new RuntimeException("Mã tour đã tồn tại: " + maTour);
                }
            }

            if (req.getTenTour() == null || req.getTenTour().trim().isEmpty()) {
                throw new RuntimeException("Tên tour không được để trống");
            }

            Tour tour = new Tour();
            tour.setMaTour(maTour);
            tour.setTenTour(req.getTenTour().trim());
            tour.setDiemKhoiHanh(req.getDiemKhoiHanh() != null ? req.getDiemKhoiHanh().trim() : "");
            tour.setMoTa(req.getMoTa() != null ? req.getMoTa().trim() : "");
            tour.setSoNgay(req.getSoNgay() != null ? req.getSoNgay() : 1);
            tour.setSoChoToiDa(req.getSoChoToiDa() != null ? req.getSoChoToiDa() : 10);
            tour.setGiaTour(req.getGiaTour() != null ? req.getGiaTour() : BigDecimal.ZERO);
            tour.setTrangThai(req.getTrangThai() != null ? req.getTrangThai() : true);
            tour.setNgayTao(LocalDate.now());
            tour.setSoLuong(req.getSoLuong() != null ? req.getSoLuong() : 0);

            // ✅ SỬA LỖI: req.getAnhTour() thay vì request.getAnhTour()
            tour.setAnhTour(req.getAnhTour());

            System.out.println("📸 Ảnh tour: " + req.getAnhTour());

            if (req.getDiaDiemId() != null) {
                try {
                    DiaDiem dd = diaDiemRepo.findById(req.getDiaDiemId()).orElse(null);
                    if (dd != null) {
                        tour.setDiaDiem(dd);
                        System.out.println("Đã set DiaDiem: " + dd.getTenDd());
                    } else {
                        tour.setDiaDiem(null);
                    }
                } catch (Exception e) {
                    tour.setDiaDiem(null);
                }
            } else {
                tour.setDiaDiem(null);
            }

            Tour savedTour = tourRepository.save(tour);
            System.out.println("Tour saved successfully: " + savedTour.getMaTour());
            System.out.println("Ngày tạo tour: " + savedTour.getNgayTao());
            System.out.println("Số lượng tour: " + savedTour.getSoLuong());
            System.out.println("Ảnh tour đã lưu: " + savedTour.getAnhTour());

            if (req.getLichTrinh() != null && !req.getLichTrinh().isEmpty()) {
                List<LichTrinhTour> lichTrinhEntities = new ArrayList<>();

                for (CreateTourRequest.LichTrinhItem item : req.getLichTrinh()) {
                    if (item.getDiaDiemId() != null || item.getDichVuId() != null) {
                        LichTrinhTour lt = new LichTrinhTour();
                        lt.setTour(savedTour);

                        if (item.getDiaDiemId() != null) {
                            try {
                                DiaDiem dd = diaDiemRepo.findById(item.getDiaDiemId()).orElse(null);
                                if (dd != null) {
                                    lt.setDiaDiem(dd);
                                }
                            } catch (Exception e) {
                            }
                        }

                        if (item.getDichVuId() != null) {
                            try {
                                DichVu dv = dichVuRepo.findById(item.getDichVuId()).orElse(null);
                                if (dv != null) {
                                    lt.setDichVu(dv);
                                }
                            } catch (Exception e) {
                            }
                        }

                        lt.setLoai(item.getLoai() != null ? item.getLoai() : "dd");
                        lt.setMoTa(item.getMoTa() != null ? item.getMoTa() : "");

                        lichTrinhEntities.add(lt);
                    }
                }

                if (!lichTrinhEntities.isEmpty()) {
                    lichTrinhRepo.saveAll(lichTrinhEntities);
                }
            }

            if (req.getLichKhoiHanh() != null && !req.getLichKhoiHanh().isEmpty()) {
                List<LichKhoiHanh> lichKhoiHanhEntities = new ArrayList<>();

                for (CreateTourRequest.LichKhoiHanhItem item : req.getLichKhoiHanh()) {
                    if (item.getNgayKhoiHanh() != null) {
                        LichKhoiHanh lkh = new LichKhoiHanh();
                        lkh.setTour(savedTour);
                        lkh.setNgayKhoiHanh(item.getNgayKhoiHanh());
                        lkh.setNgayKetThuc(item.getNgayKetThuc());
                        lkh.setSoChoConLai(item.getSoCho() != null ? item.getSoCho() : 0);

                        lichKhoiHanhEntities.add(lkh);
                    }
                }

                if (!lichKhoiHanhEntities.isEmpty()) {
                    lichKhoiHanhRepo.saveAll(lichKhoiHanhEntities);
                }
            }

            System.out.println("=== TẠO TOUR THÀNH CÔNG ===");
            return savedTour;

        } catch (Exception e) {
            System.err.println("=== LỖI KHI TẠO TOUR ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Không thể tạo tour: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Tour createSimpleTour(CreateTourRequest req) {
        try {
            System.out.println("=== TẠO TOUR ĐƠN GIẢN ===");

            String maTour;
            if (req.getMaTour() == null || req.getMaTour().trim().isEmpty()) {
                maTour = generateRandomTourCode();
                System.out.println("Tự động tạo mã tour: " + maTour);
            } else {
                maTour = req.getMaTour().trim();
                if (tourRepository.existsById(maTour)) {
                    throw new RuntimeException("Mã tour đã tồn tại: " + maTour);
                }
            }

            Tour tour = new Tour();
            tour.setMaTour(maTour);
            tour.setTenTour(req.getTenTour() != null ? req.getTenTour().trim() : " ");
            tour.setDiemKhoiHanh(req.getDiemKhoiHanh() != null ? req.getDiemKhoiHanh().trim() : "");
            tour.setMoTa(req.getMoTa() != null ? req.getMoTa().trim() : "");
            tour.setSoNgay(req.getSoNgay() != null ? req.getSoNgay() : 10);
            tour.setSoChoToiDa(req.getSoChoToiDa() != null ? req.getSoChoToiDa() : 10);
            tour.setGiaTour(req.getGiaTour() != null ? req.getGiaTour() : BigDecimal.valueOf(100000));
            tour.setTrangThai(req.getTrangThai() != null ? req.getTrangThai() : true);
            tour.setNgayTao(LocalDate.now());
            tour.setSoLuong(req.getSoLuong() != null ? req.getSoLuong() : 0);

            // ✅ THÊM: Set ảnh tour cho simple tour
            tour.setAnhTour(req.getAnhTour());

            tour.setDiaDiem(null);

            Tour savedTour = tourRepository.save(tour);
            System.out.println("TOUR ĐƠN GIẢN TẠO THÀNH CÔNG: " + savedTour.getMaTour());
            System.out.println("Ngày tạo: " + savedTour.getNgayTao());
            System.out.println("Số lượng: " + savedTour.getSoLuong());

            return savedTour;

        } catch (Exception e) {
            System.err.println("LỖI TẠO TOUR ĐƠN GIẢN: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi tạo tour đơn giản: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Tour createAutoTour(String tenTour) {
        try {
            String maTour = generateRandomTourCode();

            Tour tour = new Tour();
            tour.setMaTour(maTour);
            tour.setTenTour(tenTour != null ? tenTour : "");
            tour.setDiemKhoiHanh("");
            tour.setMoTa("");
            tour.setSoNgay(2);
            tour.setSoChoToiDa(15);
            tour.setGiaTour(BigDecimal.valueOf(500000));
            tour.setTrangThai(true);
            tour.setDiaDiem(null);
            tour.setNgayTao(LocalDate.now());
            tour.setSoLuong(10);

            Tour saved = tourRepository.save(tour);
            System.out.println("TOUR TỰ ĐỘNG TẠO THÀNH CÔNG: " + saved.getMaTour());
            System.out.println("Ngày tạo: " + saved.getNgayTao());
            System.out.println("Số lượng: " + saved.getSoLuong());
            return saved;

        } catch (Exception e) {
            System.err.println("LỖI TẠO TOUR TỰ ĐỘNG: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public TourDetailResponse getTourDetail(String maTour) {
        try {
            Tour tour = tourRepository.findById(maTour)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tour: " + maTour));

            TourDetailResponse res = new TourDetailResponse();
            res.setMaTour(tour.getMaTour());
            res.setTenTour(tour.getTenTour());
            res.setDiemKhoiHanh(tour.getDiemKhoiHanh());
            res.setTenDiaDiemDen(tour.getDiaDiem() != null ? tour.getDiaDiem().getTenDd() : null);
            res.setMoTa(tour.getMoTa());
            res.setSoNgay(tour.getSoNgay());
            res.setSoChoToiDa(tour.getSoChoToiDa());
            res.setGiaTour(tour.getGiaTour());
            res.setTrangThai(tour.getTrangThai());
            res.setNgayTao(tour.getNgayTao());

            // ✅ THÊM: Trả về ảnh tour
            res.setAnhTour(tour.getAnhTour());

            List<LichTrinhTour> ltList = lichTrinhRepo.findByTour_MaTour(maTour);
            List<TourDetailResponse.LichTrinhDto> ltDtoList = new ArrayList<>();
            for (LichTrinhTour lt : ltList) {
                TourDetailResponse.LichTrinhDto dto = new TourDetailResponse.LichTrinhDto();
                dto.setLoai(lt.getLoai());
                dto.setMoTa(lt.getMoTa());
                dto.setTenDiaDiem(lt.getDiaDiem() != null ? lt.getDiaDiem().getTenDd() : null);
                dto.setTenDichVu(lt.getDichVu() != null ? lt.getDichVu().getTenDv() : null);
                ltDtoList.add(dto);
            }
            res.setLichTrinh(ltDtoList);

            List<LichKhoiHanh> lkhList = tour.getLichKhoiHanhs();
            List<TourDetailResponse.LichKhoiHanhDto> lkhDtoList = new ArrayList<>();
            if (lkhList != null) {
                for (LichKhoiHanh lkh : lkhList) {
                    TourDetailResponse.LichKhoiHanhDto dto = new TourDetailResponse.LichKhoiHanhDto();
                    dto.setIdLich(lkh.getIdLich());
                    dto.setNgayKhoiHanh(lkh.getNgayKhoiHanh());
                    dto.setNgayKetThuc(lkh.getNgayKetThuc());
                    dto.setSoChoConLai(lkh.getSoChoConLai());
                    lkhDtoList.add(dto);
                }
            }
            res.setLichKhoiHanh(lkhDtoList);

            return res;
        } catch (Exception e) {
            System.err.println("Lỗi getTourDetail: " + e.getMessage());
            throw e;
        }
    }

    public Tour create(Tour tour) {
        return tourRepository.save(tour);
    }

    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    public Tour getById(String id) {
        return tourRepository.findById(id).orElse(null);
    }

    public Tour update(String id, Tour updated) {
        return tourRepository.findById(id)
                .map(t -> {
                    t.setTenTour(updated.getTenTour());
                    t.setDiemKhoiHanh(updated.getDiemKhoiHanh());
                    t.setDiaDiem(updated.getDiaDiem());
                    t.setMoTa(updated.getMoTa());
                    t.setSoNgay(updated.getSoNgay());
                    t.setSoChoToiDa(updated.getSoChoToiDa());
                    t.setGiaTour(updated.getGiaTour());
                    t.setTrangThai(updated.getTrangThai());
                    t.setNgayTao(LocalDate.now());

                    if (updated.getSoLuong() != null) {
                        t.setSoLuong(updated.getSoLuong());
                    }

                    // ✅ THÊM: Update ảnh tour
                    if (updated.getAnhTour() != null) {
                        t.setAnhTour(updated.getAnhTour());
                    }

                    return tourRepository.save(t);
                })
                .orElse(null);
    }

    public boolean delete(String id) {
        if (tourRepository.existsById(id)) {
            tourRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Map<String, Object>> getTourRevenueStatistics(LocalDate startDate, LocalDate endDate) {
        try {
            System.out.println("🔍 Querying tours from " + startDate + " to " + endDate);

            List<Tour> tours = tourRepository.findByNgayTaoBetween(startDate, endDate);

            System.out.println("📦 Found " + tours.size() + " tours");

            List<Map<String, Object>> result = new ArrayList<>();

            for (Tour tour : tours) {
                Map<String, Object> tourData = new HashMap<>();
                tourData.put("maTour", tour.getMaTour());
                tourData.put("tenTour", tour.getTenTour());
                tourData.put("diemKhoiHanh", tour.getDiemKhoiHanh());
                tourData.put("diemDen", tour.getDiaDiem() != null ? tour.getDiaDiem().getTenDd() : "Chưa có");
                tourData.put("ngayTao", tour.getNgayTao());
                tourData.put("soNgay", tour.getSoNgay());
                tourData.put("soChoToiDa", tour.getSoChoToiDa());
                tourData.put("soKhachTrungBinh", 0.0);
                tourData.put("tongDoanhThu", 0);

                result.add(tourData);
            }

            System.out.println("✅ Returning " + result.size() + " tours");
            return result;

        } catch (Exception e) {
            System.err.println("❌ Error in getTourRevenueStatistics: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}