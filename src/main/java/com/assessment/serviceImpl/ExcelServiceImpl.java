package com.assessment.serviceImpl;

import com.assessment.entities.User;
import com.assessment.repositories.UserRepository;
import com.assessment.service.ExcelService;
import com.assessment.util.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private UserRepository userRepository;

    public void readAndStoreData(MultipartFile file) throws IOException {
        List<User> users = ExcelReader.readUsersFromExcel(file);
        userRepository.saveAll(users);
    }
}

