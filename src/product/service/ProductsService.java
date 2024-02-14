package product.service;

import java.io.BufferedReader;

public interface ProductsService {
    public abstract void productListInventory();
    public   abstract boolean isProductOnSale(BufferedReader br);
    public abstract void createProduct();
    public abstract void updateProduct();
}
