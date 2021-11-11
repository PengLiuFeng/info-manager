package com.rainng.coursesystem.model.export;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TeacherExportDao {

    @ExcelProperty(index = 2)
    private String sexName;

    @ExcelProperty(index = 4)
    private String titleName;

    @ExcelProperty(index = 5)
    private String teacherTypeName;

    @ExcelProperty(index = 6)
    private Integer scienceInTrue;

    @ExcelProperty(index = 7)
    private Integer scienceOutTrue;

    @ExcelProperty(index = 8)
    private Integer scienceSum;

    @ExcelProperty(index = 9)
    private Integer scienceSurplus;

    @ExcelProperty(index = 10)
    private Integer noScienceInTrue;

    @ExcelProperty(index = 11)
    private Integer noScienceOutTrue;

    @ExcelProperty(index = 12)
    private Integer noScienceSum;

    @ExcelProperty(index = 13)
    private Integer noScienceSurplus;


    @ExcelProperty(index = 2)
    private String number;

    @ExcelProperty(index = 1)
    private String name;

}
