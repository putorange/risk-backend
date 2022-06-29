package com.ietunnel.servicebase.exceptionhandler;

import com.ietunnel.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();//打印异常
        return R.error().message("执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(FirmException.class)
    @ResponseBody //为了返回数据
    public R error(FirmException e) {
        log.error(e.getMessage());  // 将异常信息写入日志文件中
        // 打印异常信息
        e.printStackTrace();

        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
