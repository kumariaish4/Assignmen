package com.assessment.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    void readAndStoreData(MultipartFile file) throws IOException;
}

