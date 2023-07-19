package com.assessment.payload;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class FileUploadInfoDto {
    private String fileName;
    private LocalDateTime uploadDateTime;
}
