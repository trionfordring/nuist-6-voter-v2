package icu.fordring.voter.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.spi.service.contexts.DocumentationContext;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @ClassName AdditionInterface
 * @Author fordring
 * @date 2020.07.06 13:42
 */
@ConditionalOnProperty("app.dev.enable")
@Component
public class AdditionInterface  implements ApiListingScannerPlugin {
    private LoginInterface loginInterface = new LoginInterface();
    private LogoutInterface logoutInterface = new LogoutInterface();

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return Arrays.asList(
                loginInterface.build(),
                logoutInterface.build()
        );
    }
}
