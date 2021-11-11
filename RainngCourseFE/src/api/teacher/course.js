import * as ajax from "../../common/ajax";

export const list = () => ajax.pureGet("/teacher/course/list");

export const apple = (id ,status) => 
ajax.get("/teacher/course/deploy/apple",{
    id : id , 
    status : status
});
