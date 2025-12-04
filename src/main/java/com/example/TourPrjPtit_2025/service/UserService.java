package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.User;
import com.example.TourPrjPtit_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Đăng ký đầy đủ thông tin
    public User registerFull(String tenDangNhap, String matKhau, String hoTen,
                             String email, String soDienThoai, String soCmnd, String diaChi) {

        // Kiểm tra username đã tồn tại chưa
        if (userRepository.findByTenDangNhap(tenDangNhap).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        User user = new User();
        user.setTenDangNhap(tenDangNhap);
        user.setMatKhau(matKhau);
        user.setHoTen(hoTen);
        user.setEmail(email);
        user.setSoDienThoai(soDienThoai);
        user.setSoCmnd(soCmnd);
        user.setDiaChi(diaChi);

        return userRepository.save(user);
    }

    // Đăng nhập trả về thông tin user nếu đúng
    public User login(String tenDangNhap, String matKhau) {
        Optional<User> optionalUser = userRepository.findByTenDangNhap(tenDangNhap);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getMatKhau().equals(matKhau)) {
                return user; // đăng nhập thành công
            }
        }

        return null; // sai thông tin
    }
    // lay tt nguoi dung
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}