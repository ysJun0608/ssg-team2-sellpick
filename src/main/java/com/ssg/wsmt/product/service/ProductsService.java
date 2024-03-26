package com.ssg.wsmt.product.service;

import com.ssg.wsmt.product.dto.ProductsDTO;

public interface ProductsService {
    void productListInventory();
    void register(ProductsDTO productsDTO);
    void modify(ProductsDTO productsDTO);
}
