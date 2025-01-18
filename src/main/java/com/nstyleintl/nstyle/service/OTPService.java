package com.nstyleintl.nstyle.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.model.User;
import com.nstyleintl.nstyle.repo.UserRepository;

@Service
public class OTPService {

    @Autowired
    private UserRepository userRepository;
    
    private String generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(9000) + 1000;
        return String.valueOf(otp);
    }

    // currently returning the otp, but it should be void and sending the otp to mail
    public String sendOTP(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        String otp = generateOTP();
        user.setVerificationCode(otp);

        userRepository.save(user);

        // abstracting the mail code
        // TODO: send the OTP via mail to the entered email by user
        return otp;
    }
    
    public boolean verifyOTP(String email, String otp) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getVerificationCode().equals(otp)) {
            user.setVerified(1L);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}

