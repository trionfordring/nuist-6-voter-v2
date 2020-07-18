package icu.fordring.voter.config;

import icu.fordring.voter.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

/**
 * @Description 全局异常处理器
 * @ClassName GlobalExceptionHandler
 * @Author fordring
 * @date 2020.07.05 14:59
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @Author fordring
     * @Description  处理主动产生的客户端异常
     * @Date 2020/7/5 15:05
     * @Param [e, response]
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ExceptionHandler(value = HttpClientErrorException.class)
    public Result<Object> handleHttpClientErrorException(HttpClientErrorException e, HttpServletResponse response) {
        log.error(e.getLocalizedMessage());
        response.setContentType("application/json");
        Result<Object> resultVO = new Result<>();
        resultVO.setStatus(e.getStatusCode().value());
        resultVO.setMessage(e.getStatusText());
        resultVO.setData(null);
        return resultVO;
    }
    /**
     * @Author fordring
     * @Description  处理权限不足
     * @Date 2020/7/6 11:51
     * @Param [e, response]
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result<Object> handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response) {
        log.error("用户访问[{}]权限不足,method:[{}],content-type:[{}]",request.getRequestURI(),request.getMethod(),request.getContentType());
        response.setContentType("application/json");
        Result<Object> resultVO = new Result<>();
        resultVO.setStatus(403);
        resultVO.setMessage("权限不足");
        resultVO.setData(null);
        return resultVO;
    }
    /**
     * @Author fordring
     * @Description  处理一些格式异常
     * @Date 2020/7/17 13:03
     * @Param [request, response, e]
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Object> handleIllegalArgumentException(HttpServletRequest request, HttpServletResponse response,IllegalArgumentException e) {
        log.error("用户输入格式错误[{} - {} - {}]:{}",request.getRequestURI(),request.getMethod(),request.getContentType(),e.getMessage());
        response.setContentType("application/json");
        Result<Object> resultVO = new Result<>();
        resultVO.setStatus(400);
        resultVO.setMessage("输入格式错误:"+e.getMessage());
        resultVO.setData(null);
        return resultVO;
    }
    /**
     * @Author fordring
     * @Description  处理参数校验异常
     * @Date 2020/7/17 13:03
     * @Param [request, response, e]
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ExceptionHandler(value = javax.validation.ConstraintViolationException.class)
    public Result<Object> handleConstraintViolationException(HttpServletRequest request, HttpServletResponse response,javax.validation.ConstraintViolationException e) {
        log.error("用户输入格式错误[{} - {} - {}]:{}",request.getRequestURI(),request.getMethod(),request.getContentType(),e.getMessage());
        response.setContentType("application/json");
        Result<Object> resultVO = new Result<>();
        resultVO.setStatus(400);
        StringBuilder sb = new StringBuilder("输入格式错误:");
        for(ConstraintViolation<?> constraintViolation:e.getConstraintViolations()){
            sb.append(constraintViolation.getMessage()).append(",");
        }
        resultVO.setMessage(sb.subSequence(0,sb.length()-1).toString());
        resultVO.setData(null);
        return resultVO;
    }
    /**
     * @Author fordring
     * @Description  处理抛出的其他异常
     * @Date 2020/7/5 15:06
     * @Param [e, response]
     * @return icu.fordring.voter.dto.Result<java.lang.Object>
     **/
    @ExceptionHandler(value = Exception.class)
    public Result<Object> handleException(Exception e, HttpServletResponse response) {
        log.error(e.getLocalizedMessage());
        e.printStackTrace();
        response.setContentType("application/json");
        Result<Object> resultVO = new Result<>();
        resultVO.setStatus(500);
        resultVO.setMessage(e.getLocalizedMessage());
        resultVO.setData(null);
        return resultVO;
    }
    
}
