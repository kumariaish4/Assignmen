package com.assessment.serviceImpl;

import com.assessment.entities.User;
import com.assessment.payload.FileUploadInfoDto;
import com.assessment.payload.UserDto;
import com.assessment.repositories.UserRepository;
import com.assessment.service.FileUploadService;
import com.assessment.service.UserService;
import com.assessment.util.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, FileUploadService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapDtoToUser(userDto);
        user = userRepository.save(user);
        return mapUserToDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        updateUserFromDto(user, updatedUserDto);
        user = userRepository.save(user);
        return mapUserToDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return mapUserToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapUserToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InputStreamResource getUserAsExcel() {
        List<User> users=userRepository.findAll();
        try{
            return ExcelExporter.exportUsersToExcel(users);
        }catch(IOException e) {
            throw new RuntimeException("Failed to generate Excel file", e);
        }
    }

    // Helper methods for manual mapping
    private User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setCity(userDto.getCity());
        user.setEmail(userDto.getEmail());
        return user;
    }

    private UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private void updateUserFromDto(User user, UserDto updatedUserDto) {
        user.setName(updatedUserDto.getName());
        user.setAge(updatedUserDto.getAge());
        user.setCity(updatedUserDto.getCity());
        user.setEmail(updatedUserDto.getEmail());
    }

    @Override
    public List<FileUploadInfoDto> getAllFileUploads() {
        List<FileUploadInfoDto> fileUploads = new ArrayList<>();

        // Fetch all users from the database
        List<User> users = userRepository.findAll();

        // Convert user data to FileUploadInfoDto and add to the list
        for (User user : users) {
            FileUploadInfoDto fileInfo = new FileUploadInfoDto();
            fileInfo.setFileName(user.getName());
            fileInfo.setUploadDateTime(user.getUploadDateTime()); // Assuming you have a 'uploadDateTime' field in the User entity to store the upload date and time
            fileUploads.add(fileInfo);
        }

        return fileUploads;
    }
}
