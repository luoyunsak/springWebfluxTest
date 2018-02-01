package com.webfluxtest.repository;

import com.webfluxtest.model.Product;
import com.webfluxtest.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author luoyun
 * @ClassName: IntelliJ IDEA
 * @Description: 操作类型
 * @date 2018/1/29
 */
@Repository
public class ProductRepository {
    private final List<Product> Products = Arrays.asList(new Product("chuangxing", "changsha"), new Product("weikang", "changsha"));

    public Mono<Product> getProByName(String name) {
        return Mono.justOrEmpty(Products.stream().filter(pro -> {
            return pro.getName().equals(name);
        }).findFirst().orElse(null));
    }

    public Flux<Product> getProduct() {
        return Flux.fromIterable(Products);
    }
}
