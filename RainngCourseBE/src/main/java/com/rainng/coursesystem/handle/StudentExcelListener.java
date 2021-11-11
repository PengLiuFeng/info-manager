package com.rainng.coursesystem.handle;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.rainng.coursesystem.model.export.StudentExportDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentExcelListener<T> extends AnalysisEventListener<T> {
    List<T> data = new ArrayList<>();

    @Override
    public void invoke(T studentExportDao, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(studentExportDao));
        data.add(studentExportDao);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
    }

    public List<T> getData(){
        return this.data;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
