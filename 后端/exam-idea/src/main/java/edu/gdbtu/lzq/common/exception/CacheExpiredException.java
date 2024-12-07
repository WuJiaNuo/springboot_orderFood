package edu.gdbtu.lzq.common.exception;

import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * cache 失效异常处理类
 *
 */
@Data
public class CacheExpiredException extends RuntimeException {

    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param code
     * @param message
     */
    public CacheExpiredException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    public CacheExpiredException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "LanfException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
