package com.cgfarm.controller;

import com.cgfarm.entity.Product;
import com.cgfarm.links.ProductLinks;
import com.cgfarm.origin.Origin;
import com.cgfarm.service.ErrorReportService;
import com.cgfarm.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = Origin.CROSS_ORIGIN)
@RequestMapping(path = ProductLinks.PRODUCT)
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ErrorReportService errorReportService;

    @GetMapping(path = ProductLinks.GET_BY_CATEGORY)
    public List<Product> getProductByCategory(@PathVariable String categoryId) {
        try {
            return productService.getListByCategoryId(Long.parseLong(categoryId));
        }catch (Exception e ){
            errorReportService.generateErrorReport(e, "controller", "ProductController", "getProductByCategory");
            return null;
        }
    }
}
