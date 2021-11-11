package com.rainng.coursesystem.controller.admin;

import com.rainng.coursesystem.service.FileExportService;
import com.rainng.coursesystem.service.impl.FileExportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RequestMapping("/admin/file/export")
@RestController
@Slf4j
public class FileExportController {


    @Autowired
    private FileExportService fileExportService;

    @PostMapping("exportFile")
    public Boolean exportFile(@RequestPart("file") MultipartFile multipartFile,@RequestParam("type") Integer type){
       return fileExportService.export(multipartFile,type);
    }
}
