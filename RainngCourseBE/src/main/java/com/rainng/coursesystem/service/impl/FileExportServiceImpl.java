package com.rainng.coursesystem.service.impl;

import com.alibaba.excel.EasyExcel;
import com.rainng.coursesystem.dao.mapper.StudentMapper;
import com.rainng.coursesystem.dao.mapper.TeacherMapper;
import com.rainng.coursesystem.handle.StudentExcelListener;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.enums.SexEnum;
import com.rainng.coursesystem.model.enums.TeacherTypeEnum;
import com.rainng.coursesystem.model.enums.TitleTypeEnum;
import com.rainng.coursesystem.model.export.StudentExportDao;
import com.rainng.coursesystem.model.export.TeacherExportDao;
import com.rainng.coursesystem.service.FileExportService;
import com.rainng.coursesystem.util.Md5Encrypt;
import com.rainng.coursesystem.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FileExportServiceImpl implements FileExportService {
    private static final String PASSWORD = "123456";

    private static final String PASSWORD_SALT = "_Rain_Ng-_Azure_99";

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    @Transactional
    public boolean studentExport(MultipartFile file ) {
        List<StudentExportDao> data = new DataGet<StudentExportDao>().getData(file , StudentExportDao.class);
        List<StudentEntity> studentEntities = data.stream().map(value -> {
            StudentEntity studentEntity = new StudentEntity();
            BeanUtils.copyProperties(value,studentEntity);
            studentEntity.setStudentUuid(UuidUtil.createUUID());
            studentEntity.setPassword(computePasswordHash(PASSWORD));
            return studentEntity;
        }).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(studentEntities)){
            studentMapper.insertBatchSomeColumn(studentEntities);
        }
        return true;
    }

    public String computePasswordHash(String password) {
        String md5 = Md5Encrypt.computeHexString(password);
        return Md5Encrypt.computeHexString(md5 + PASSWORD_SALT);
    }

    @Override
    public boolean teacherExport(MultipartFile file) {
        List<TeacherExportDao> data = new DataGet<TeacherExportDao>().getData(file , TeacherExportDao.class);
        List<TeacherEntity> teacherEntities = data.stream().map(value -> {
            TeacherEntity teacherEntity = new TeacherEntity();
            BeanUtils.copyProperties(value,teacherEntity);
            teacherEntity.setTeacherUuid(UuidUtil.createUUID());
            teacherEntity.setPassword(computePasswordHash(PASSWORD));
            teacherEntity.setSex(SexEnum.getCodeByName(value.getSexName()));
            teacherEntity.setTitle(TitleTypeEnum.getCodeByName(value.getTitleName()));
            teacherEntity.setTeacherType(TeacherTypeEnum.getCodeByName(value.getTeacherTypeName()));
            return teacherEntity;
        }).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(teacherEntities)){
            teacherMapper.insertBatchSomeColumn(teacherEntities);
        }
        return true;
    }

    @Override
    public boolean adminExport(MultipartFile file) {
        return false;
    }

    @Override
    public Boolean export(MultipartFile multipartFile, Integer type) {
       return type == 1 ? studentExport(multipartFile) : teacherExport(multipartFile);
    }

    class DataGet<T> {
        List<T> getData(MultipartFile file , Class<T> tcalss){
            try {
                // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
                StudentExcelListener<T> studentExcelListener = new StudentExcelListener();
                EasyExcel.read(file.getInputStream(), tcalss, studentExcelListener).sheet().doRead();
                return studentExcelListener.getData();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return Collections.EMPTY_LIST;
        }
    }

}
