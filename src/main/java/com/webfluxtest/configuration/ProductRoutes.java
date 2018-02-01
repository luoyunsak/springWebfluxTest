package com.webfluxtest.configuration;

import com.webfluxtest.service.ProductHandler;
import com.webfluxtest.service.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author luoyun
 * @ClassName: IntelliJ IDEA
 * @Description: 操作类型
 * @date 2018/1/29
 */
@Configuration
public class ProductRoutes {

    private ProductHandler productHandler;

    public ProductRoutes(ProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @Bean
    public RouterFunction<?> ProductFunction() {
        return route(GET("/api/pro").and(accept(MediaType.APPLICATION_JSON)), productHandler::handleGetPros)
                .and(route(GET("/api/pro/{name}").and(accept(MediaType.APPLICATION_JSON)), productHandler::handleGetProByName))
                .andNest(path("/name"),route(POST("/pro/get").and(accept(MediaType.APPLICATION_JSON)),productHandler::handleGetPro));
    }
}
