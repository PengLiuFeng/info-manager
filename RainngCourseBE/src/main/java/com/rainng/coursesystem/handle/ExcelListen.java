package com.rainng.coursesystem.handle;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

public class ExcelListen extends AnalysisEventListener<T> {

    //可以通过实例获取该值
    private List<T> datas = new ArrayList<T>();

    private void doSomething(T object) {
        //1、入库调用接口

    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
