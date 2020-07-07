package icu.fordring.voter.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * @Description 登录接口描述类
 * @ClassName LoginInterface
 * @Author fordring
 * @date 2020.07.06 11:57
 */
public class LoginInterface  {
    public ApiDescription build(){
        OperationBuilder operationBuilder = new OperationBuilder(
                new CachingOperationNameGenerator()
        )
                .method(HttpMethod.POST)//http请求类型
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .summary("用户登录")
                .notes("[USER_LOGIN]")//方法描述
                .tags(Sets.newHashSet("用户"))//归类标签
                .parameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .description("用户名")//参数描述
                                        .type(new TypeResolver().resolve(String.class))//参数数据类型
                                        .name("name")//参数名称
                                        .parameterType("query")//参数类型
                                        .parameterAccess("access")
                                        .required(true)//是否必填
                                        .modelRef(new ModelRef("string")) //参数数据类型
                                        .build(),
                                new ParameterBuilder()
                                        .description("密码")
                                        .type(new TypeResolver().resolve(String.class))
                                        .name("password")
                                        .parameterType("query")
                                        .parameterAccess("access")
                                        .required(true)
                                        .modelRef(new ModelRef("string")) //<5>
                                        .build(),
                                new ParameterBuilder()
                                        .description("是否记住密码")
                                        .type(new TypeResolver().resolve(Boolean.class))
                                        .name("remember")
                                        .defaultValue("false")//参数默认值
                                        .parameterType("query")
                                        .parameterAccess("access")
                                        .required(false)
                                        .modelRef(new ModelRef("boolean")) //<5>
                                        .build()
                        )
                ).responseMessages(Sets.newHashSet(
                        new ResponseMessageBuilder().code(200).message("登录成功").build(),
                        new ResponseMessageBuilder().code(403).message("登录失败").build()
                ));
        return new ApiDescription("UserController","/user/login","用户登录",
                Arrays.asList(operationBuilder.build()),false);

    }
}
