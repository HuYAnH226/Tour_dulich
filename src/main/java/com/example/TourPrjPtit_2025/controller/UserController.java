package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.User;
import com.example.TourPrjPtit_2025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") // Cho phép frontend gọi API từ domain khác
public class UserController {

    @Autowired
    private UserService userService;

    // ===== ĐĂNG KÝ NGƯỜI DÙNG =====
    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        String tenDangNhap = body.get("tenDangNhap");
        String matKhau = body.get("matKhau");
        String hoTen = body.get("hoTen");
        String email = body.get("email");
        String soDienThoai = body.get("soDienThoai");
        String soCmnd = body.get("soCmnd");
        String diaChi = body.get("diaChi");

        return userService.registerFull(
                tenDangNhap, matKhau, hoTen, email, soDienThoai, soCmnd, diaChi
        );
    }

    // ===== ĐĂNG NHẬP =====
    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> body) {
        String tenDangNhap = body.get("tenDangNhap");
        String matKhau = body.get("matKhau");

        User user = userService.login(tenDangNhap, matKhau);
        if (user != null) {
            return user; // Trả về toàn bộ thông tin user nếu đăng nhập thành công
        } else {
            return Map.of("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }
}