import axios from "axios";
 
//1.允许创建axios实例
const instance = axios.create({
  baseURL: "http://localhost:9090",
  // 表示超时事件 如果超过这个时间 就不再请求 进行报错
  // Uncaught (in promise) TypeError: Cannot read properties of undefined (reading 'data')
  timeout: 1000,
  //请求头
  headers: { "Content-Type": "application/json " },
  //会在原先传的参数的情况下 前面添加一项 可以用于传token
  // get请求使用
  params: {
    token: localStorage.getItem("token"),
  },
  // post请求使用
  data: {
    token: localStorage.getItem("token"),
  },
});
 
// 2.拦截器
// 请求拦截器
// 在发送请求之前执行这个函数
instance.interceptors.request.use(
  function (config) {
    // 参数config是要发送给后端的所有内容
    // 在发送请求之前统一做一些事情 比如发送token 更换请求头
    // console.log(config);
 
    // 如果是上传文件的请求 更改请求头
    // if (config.url === "/file/upload") {
    //   config.headers = ...
    // }
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);
 
// 响应拦截器
// 在刚好接收数据的时候执行
instance.interceptors.response.use(
  function (response) {
    // console.log(response); //数据对象
    // 对响应数据做点什么
    //?  可以在这里过滤数据 要哪个数据返回哪个数据
    //?  可以在这处理状态码
    if (response.status === 200) {
      return response;//这里return出去的东西是return导get或者post方法的.then方法里
    } else if (response.status === 500) {
      // 抛错导catch
      // 这里404错误无法处理
      throw new Error("505 服务器端错误...");
    }
  },
  function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  }
);
 
const http = {
  // 参数可以直接传递 params直接是对象
  get(url, params) {
    //使用实例请求 可以直接自动拼接baseURL
    return instance
      .get(url, {
        params: params,
      })
      .then((res) => {
        if (res.data.code === 200) {
          return res;
        }
      })
      .catch((err) => {
        //   捕获错误信息 timeout of 1000ms exceeded  捕获之后可以将这个换成轻提示
        // console.log(err.message);
        if (err.message === "timeout of 1000ms exceeded") {
          alert("请求超时");
        }
        alert("服务器端错误");
      });
  },
  post(url, data) {
    return instance
      .post(url, data)
      .then((response) => {
        return response;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  delete(url) {
    return instance
      .delete(url)
      .then((response) => {
        return response;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  
};
 
export default http;