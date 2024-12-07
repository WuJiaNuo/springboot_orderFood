package edu.gdbtu.lzq.system.handler;

import edu.gdbtu.lzq.common.exception.CacheExpiredException;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.system.exception.LanfException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 全局异常处理类
 *
 */
@ControllerAdvice
@Order(2)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("除數不能為0");
    }

    @ExceptionHandler(LanfException.class)
    @ResponseBody
    public Result error(LanfException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(CacheExpiredException.class)
    @ResponseBody
    public Result error(CacheExpiredException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }


}
