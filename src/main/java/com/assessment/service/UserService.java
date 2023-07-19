package com.assessment.service;

import com.assessment.entities.User;
import com.assessment.payload.FileUploadInfoDto;
import com.assessment.payload.UserDto;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto getUserById(Long id);
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
    InputStreamResource getUserAsExcel();
    public List<FileUploadInfoDto> getAllFileUploads();
}

