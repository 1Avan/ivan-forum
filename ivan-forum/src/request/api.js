import http from "./http";
 
export const ratedApi = (params) => http.get("/rated/list", params);//将对应的地址封装 params参数是传递的数据
// 分页获取用户信息
export const getUser = (params) => http.get("/user/page",params)
//获取所有用户信息
export function getALL(params){
    return http.get("/user",params)
}
//删除用户
export const deleteById = (id)=>http.delete("/user/delete/"+id)