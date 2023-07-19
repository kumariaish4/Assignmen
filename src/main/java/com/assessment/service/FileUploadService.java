package com.assessment.service;

import com.assessment.payload.FileUploadInfoDto;

import java.util.List;

public interface FileUploadService {
    List<FileUploadInfoDto> getAllFileUploads();
}
