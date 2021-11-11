package com.rainng.coursesystem.service;

import com.rainng.coursesystem.model.vo.response.ResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileExportService {

    boolean studentExport(MultipartFile file);

    boolean teacherExport(MultipartFile file);

    boolean adminExport(MultipartFile file);

    Boolean export(MultipartFile multipartFile, Integer type);
}
