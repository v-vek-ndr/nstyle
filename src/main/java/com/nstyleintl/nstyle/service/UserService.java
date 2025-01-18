package com.nstyleintl.nstyle.service;

import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.UserDTO;
import com.nstyleintl.nstyle.exception.DuplicateResourceException;
import com.nstyleintl.nstyle.model.User;
import com.nstyleintl.nstyle.repo.UserRepository;
import com.nstyleintl.nstyle.utils.Utility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OTPService otpService;

    public String registerUser(UserDTO userDTO) {
    	if (userRepository.isEmailTaken(userDTO.getEmail())) {
    		throw new DuplicateResourceException(String.format("Email-Id - %s is already taken!", userDTO.getEmail()));
    	}
    	
        User user = mapToEntity(userDTO);
        user.setVerified(0L);
        userRepository.save(user);
        
        String otp = otpService.sendOTP(userDTO.getEmail());

        return otp;
    }

    public boolean verifyUserOTP(String email, String otp) {
        return otpService.verifyOTP(email, otp);
    }
    
    public User mapToEntity(UserDTO userDTO) {
		return User.builder().name(userDTO.getName())
				.email(userDTO.getEmail()).profilePic(userDTO.getProfilePic())
				.cv(userDTO.getCv()).dateOfBirth(Utility.casteToLocalDate(userDTO.getDateOfBirth()))
				.verified(userDTO.getVerified()).build();
	}
}

