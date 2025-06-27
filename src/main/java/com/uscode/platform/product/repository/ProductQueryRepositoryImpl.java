package com.uscode.platform.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.uscode.platform.product.Product;
import com.uscode.platform.product.QProduct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.uscode.platform.product.QProduct.product;

public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory query;

    public ProductQueryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Product> findAllProductByUserId(Long userId) {
        return query.selectFrom(product)
                .where(product.user.id.eq(userId))
                .fetch();
    }


}
