package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
	
	/**
	 * 根据供应商ID查询订单数量
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public int getBillCountByProviderId(@Param("providerId") Integer providerId)throws Exception;
	
	/**
	 * 根据供应商ID删除订单信息
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public int deleteBillByProviderId(@Param("providerId") Integer providerId)throws Exception;

    int getBillCount(@Param("billCode")String billCode, @Param("productName")String productName);

	List<Bill> getBillList(@Param("productName")String productName, @Param("billCode")String billCode, @Param("from")int currentPageNo, @Param("pageSize")int pageSize);
	public Bill getBillById(@Param("id") Integer id);


    int modify(Bill bill);

	int add(Bill bill);

	int  deleteBillById(@Param("id")int parseInt);
}
