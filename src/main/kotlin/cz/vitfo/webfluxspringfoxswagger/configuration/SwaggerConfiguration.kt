package cz.vitfo.webfluxspringfoxswagger.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@Configuration
@EnableSwagger2WebFlux
class SwaggerConfiguration {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .enable(true)
//                .select()
//                .paths(PathSelectors.any())
//                .build()
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enableUrlTemplating(true)
                .enable(true)
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Webflux springfox swagger application")
                .description("Project to show usage of swagger in webflux rest application")
                .version("1.0.0")
                .contact(Contact("vitfo", "www.vitfo.cz", "noemail"))
                .license("Apache License, Version 2.0")
                .licenseUrl("https://opensource.org/licenses/Apache-2.0")
                .build()
    }
}