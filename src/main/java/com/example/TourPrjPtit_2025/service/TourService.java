package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.dto.CreateTourRequest;
import com.example.TourPrjPtit_2025.entity.*;
import com.example.TourPrjPtit_2025.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TourPrjPtit_2025.dto.TourDetailResponse;


import java.util.ArrayList;
import java.util.List;

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

    // =================================================
    // 1. TẠO TOUR ĐẦY ĐỦ: TOUR + LỊCH TRÌNH + KHỞI HÀNH
    // =================================================
    @Transactional
    public Tour createFullTour(CreateTourRequest req) {

        // ----- Tạo đối tượng Tour -----
        Tour tour = new Tour();
        tour.setMaTour(req.getMaTour());
        tour.setTenTour(req.getTenTour());
        tour.setDiemKhoiHanh(req.getDiemKhoiHanh());
        tour.setMoTa(req.getMoTa());
        tour.setSoNgay(req.getSoNgay());
        tour.setSoChoToiDa(req.getSoChoToiDa());
        tour.setGiaTour(req.getGiaTour());
        tour.setTrangThai(req.getTrangThai());

        // map DiaDiem (ManyToOne)
        if (req.getDiaDiemId() != null) {
            DiaDiem dd = diaDiemRepo.findById(req.getDiaDiemId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy địa điểm id=" + req.getDiaDiemId()));
            tour.setDiaDiem(dd);
        }

        Tour savedTour = tourRepository.save(tour);

        // ----- Lịch trình tour -----
        if (req.getLichTrinh() != null && !req.getLichTrinh().isEmpty()) {
            List<LichTrinhTour> lichTrinhEntities = new ArrayList<>();

            for (CreateTourRequest.LichTrinhItem item : req.getLichTrinh()) {
                LichTrinhTour lt = new LichTrinhTour();
                lt.setTour(savedTour);   // ManyToOne Tour

                // nếu là địa điểm
                if (item.getDiaDiemId() != null) {
                    DiaDiem dd = diaDiemRepo.findById(item.getDiaDiemId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy địa điểm id=" + item.getDiaDiemId()));
                    lt.setDiaDiem(dd);
                }

                // nếu là dịch vụ
                if (item.getDichVuId() != null) {
                    DichVu dv = dichVuRepo.findById(item.getDichVuId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy dịch vụ id=" + item.getDichVuId()));
                    lt.setDichVu(dv);
                }

                lt.setLoai(item.getLoai());
                lt.setMoTa(item.getMoTa());

                lichTrinhEntities.add(lt);
            }

            lichTrinhRepo.saveAll(lichTrinhEntities);
        }


        // ----- Lịch khởi hành -----
        if (req.getLichKhoiHanh() != null && !req.getLichKhoiHanh().isEmpty()) {
            List<LichKhoiHanh> lichKhoiHanhEntities = new ArrayList<>();

            for (CreateTourRequest.LichKhoiHanhItem item : req.getLichKhoiHanh()) {
                LichKhoiHanh lkh = new LichKhoiHanh();
                lkh.setTour(savedTour);
                lkh.setNgayKhoiHanh(item.getNgayKhoiHanh());
                lkh.setNgayKetThuc(item.getNgayKetThuc());
                lkh.setSoChoConLai(item.getSoCho());

                lichKhoiHanhEntities.add(lkh);
            }

            lichKhoiHanhRepo.saveAll(lichKhoiHanhEntities);
        }

        return savedTour;
    }


    public TourDetailResponse getTourDetail(String maTour) {
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

        // Lịch trình
        List<LichTrinhTour> ltList = lichTrinhRepo.findByTour_MaTour(maTour);
        List<TourDetailResponse.LichTrinhDto> ltDtoList = new java.util.ArrayList<>();
        for (LichTrinhTour lt : ltList) {
            TourDetailResponse.LichTrinhDto dto = new TourDetailResponse.LichTrinhDto();
            dto.setLoai(lt.getLoai());
            dto.setMoTa(lt.getMoTa());
            dto.setTenDiaDiem(lt.getDiaDiem() != null ? lt.getDiaDiem().getTenDd() : null);
            dto.setTenDichVu(lt.getDichVu() != null ? lt.getDichVu().getTenDv() : null);
            ltDtoList.add(dto);
        }
        res.setLichTrinh(ltDtoList);

        // Lịch khởi hành (dùng quan hệ OneToMany trong Tour)
        List<LichKhoiHanh> lkhList = tour.getLichKhoiHanhs();
        List<TourDetailResponse.LichKhoiHanhDto> lkhDtoList = new java.util.ArrayList<>();
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
}
