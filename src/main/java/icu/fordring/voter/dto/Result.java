package icu.fordring.voter.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description 封装结果
 * @ClassName Result
 * @Author fordring
 * @date 2020.07.05 14:45
 */
@Data
public class Result<T> {

    protected int status;
    protected T data;
    protected String message;

    public Result(int status,T data,String message){
        this.status=status;
        this.data=data;
        this.message=message;
    }
    public Result(HttpStatus status, T data, String message){
        this(status.value(),data,message);
    }
    public Result(T data,String message){
        this(200,data,message);
    }
    public Result(int status,T data){
        this(status,data,null);
    }
    public Result(HttpStatus status,T data){
        this(status,data,null);
    }
    public Result(String message){
        this(200,null,message);
    }
    public Result(){}
}
