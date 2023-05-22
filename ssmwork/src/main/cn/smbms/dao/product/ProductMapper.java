package cn.smbms.dao.product;

import cn.smbms.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {


    /**
     * 获取角色列表
     * @return
     * @throws Exception
     */
    public List<Product> getProductListAll();
    int getProductCount(@Param("proCode")String proCode, @Param("proName")String proName);

    List<Product> getProductList(@Param("proName")String proName, @Param("proCode")String proCode, @Param("from")int currentPageNo, @Param("pageSize")int pageSize);
    public Product getProductById(@Param("id") Integer id);


    int modify(Product pro);

    int add(Product pro);

    int deleteProductById(@Param("id")int parseInt);
}
