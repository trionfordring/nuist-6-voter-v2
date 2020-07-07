package icu.fordring.voter.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.Arrays;

/**
 * @Description
 * @ClassName LogoutInterface
 * @Author fordring
 * @date 2020.07.06 13:21
 */
public class LogoutInterface {
    public ApiDescription build(){
        OperationBuilder operationBuilder = new OperationBuilder(
                new CachingOperationNameGenerator()
        )
                .method(HttpMethod.GET)//http请求类型
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .summary("用户登出")
                .notes("[USER_LOGOUT]")//方法描述
                .tags(Sets.newHashSet("用户"))//归类标签
                .responseMessages(Sets.newHashSet(
                        new ResponseMessageBuilder().code(200).message("登出成功").build()
                ));
        return new ApiDescription("UserController","/user/logout","用户登出",
                Arrays.asList(
                        operationBuilder.build()
                )
                ,false);
    }
}
