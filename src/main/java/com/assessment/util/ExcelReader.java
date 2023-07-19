package com.assessment.util;

import com.assessment.entities.User;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static List<User> readUsersFromExcel(MultipartFile file) throws IOException {
        List<User> users = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip the header row

            User user = new User();
            user.setId((long)row.getCell(0).getNumericCellValue());
            user.setName( row.getCell(1).getStringCellValue());
            user.setAge((int) row.getCell(2).getNumericCellValue());
            user.setCity(row.getCell(3).getStringCellValue());
            user.setEmail(row.getCell(4).getStringCellValue());

            users.add(user);
        }

        workbook.close();
        return users;
    }
}

