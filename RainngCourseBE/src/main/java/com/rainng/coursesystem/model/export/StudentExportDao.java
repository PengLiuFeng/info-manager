package com.rainng.coursesystem.model.export;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentExportDao {

    @ExcelProperty(index = 2)
    private String number;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 3)
    private Integer political;

    @ExcelProperty(index = 4)
    private Integer english;

    @ExcelProperty(index = 5)
    private Integer businessOne;

    @ExcelProperty(index = 6)
    private Integer businessTwo;

    @ExcelProperty(index = 7)
    private Integer firstTry;

    @ExcelProperty(index = 8)
    private String graduateSchool;

    @ExcelProperty(index = 9)
    private String graduateProject ;

}
