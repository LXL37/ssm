package cn.smbms.service.product;

import cn.smbms.pojo.Product;
import cn.smbms.pojo.Product;

import java.util.List;

public interface ProductService {

    int getProductCount(String proCode, String proName);

    List<Product> getProductList(String proCode, String proName, Integer currentPageNo, Integer pageSize);
    List<Product> getProductListAll();

    Product getProductById(int parseInt);

    boolean modify(Product pro);

    boolean smbmsdeleteproById(int parseInt);

    boolean add(Product pro);
}
