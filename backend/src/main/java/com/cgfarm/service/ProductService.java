package com.cgfarm.service;

import com.cgfarm.entity.Product;
import com.cgfarm.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getListByCategoryId(long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

}
