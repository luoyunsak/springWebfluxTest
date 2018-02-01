package com.webfluxtest.service;

import com.webfluxtest.model.Product;
import com.webfluxtest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author luoyun
 * @ClassName: IntelliJ IDEA
 * @Description: 操作类型
 * @date 2018/1/29
 */
@Service
public class ProductHandler {
    @Autowired
    ProductRepository productRepository;

    public Mono<ServerResponse> handleGetPros(ServerRequest request) {
        return ServerResponse.ok().body(productRepository.getProduct(), Product.class);
    }

    public Mono<ServerResponse> handleGetProByName(ServerRequest request) {
        return productRepository.getProByName(request.pathVariable("name"))
                .flatMap(pro -> ServerResponse.ok().body(Mono.just(pro), Product.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> handleGetPro(ServerRequest request){
        //request.body()
        Mono<Map> name=request.bodyToMono(new HashMap<>().getClass());
        Map<String,String> names=name.block();
        System.out.println(names.get("name"));
        Product product=new Product("changsha","name");
        //String pName=Product::getName;
        return productRepository.getProByName(Optional.ofNullable(names.get("name")).orElse("0"))
                .flatMap(pro -> ServerResponse.ok().body(Mono.just(pro), Product.class))
                .switchIfEmpty(ServerResponse.notFound().build());
        //return ServerResponse.ok().body(Mono.just(new Product("changsha","luoyun")),Product.class);
    }
}
