import * as ajax from "../../common/ajax";

export const select = id => ajax.post("/student/course/select/" + id, {});

export const getPageCount = (courseName, teacherName) =>
  ajax.get("/student/course/select/page/count", {
    courseName: courseName,
    teacherName: teacherName
  });

export const getPage = (index, courseName, teacherName) =>
  ajax.get("/student/course/select/page/" + index, {
    courseName: courseName,
    teacherName: teacherName
  });

  export const choose = (teacherUuid,scienceType) =>
  ajax.get("/student/course/select/selectTeacher", {
    teacherUuid: teacherUuid,
    scienceType: scienceType
  });

export const pageSize = 20;
