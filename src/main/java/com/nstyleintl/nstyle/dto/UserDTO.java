package com.nstyleintl.nstyle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long userId;
    private String name;
    private String email;
    private String profilePic;
    private String cv;
    private String dateOfBirth;
    private String verificationCode;
    private Long verified;
}
