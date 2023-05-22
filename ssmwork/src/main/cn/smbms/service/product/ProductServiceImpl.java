package cn.smbms.service.product;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.dao.product.ProductMapper;

import cn.smbms.pojo.Product;
import cn.smbms.pojo.Product;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    @Resource
    private ProductMapper productMapper;
    @Override
    public List<Product> getProductList(String proName, String proCode,
                                          Integer currentPageNo, Integer pageSize){
        // TODO Auto-generated method stub
        currentPageNo = (currentPageNo-1)*pageSize;
        return productMapper.getProductList(proName, proCode, currentPageNo, pageSize);
    }

    @Override
    public List<Product> getProductListAll() {
        return productMapper.getProductListAll();

    }

    @Override
    public Product getProductById(int parseInt) {
        return productMapper.getProductById(parseInt);
    }

    @Override
    public int getProductCount(String proName, String proCode)
           {
        // TODO Auto-generated method stub
        return productMapper.getProductCount(proName, proCode);
    }


    @Override
    public boolean add(Product Product){
        return productMapper.add(Product)>0;
    }



    @Override
    public boolean modify(Product Product){
        return productMapper.modify(Product)>0;
    }

    @Override
    public boolean smbmsdeleteproById(int parseInt) {
        return productMapper.deleteProductById(parseInt)>0;
    }

}
