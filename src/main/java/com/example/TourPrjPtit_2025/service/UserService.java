package com.example.TourPrjPtit_2025.service;

import com.example.TourPrjPtit_2025.entity.User;
import com.example.TourPrjPtit_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Đăng ký user
    public User register(String username, String password) {

        // Kiểm tra username đã tồn tại chưa
        if (userRepository.findByTenDangNhap(username).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        User user = new User();
        user.setTenDangNhap(username);
        user.setMatKhau(password);  // mật khẩu không mã hóa (theo yêu cầu hiện tại)

        return userRepository.save(user);
    }

    // Đăng nhập
    public boolean login(String username, String password) {

        Optional<User> optionalUser = userRepository.findByTenDangNhap(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getMatKhau().equals(password);
        }

        return false;
    }
}
