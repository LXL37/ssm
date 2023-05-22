package cn.smbms.service.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: alyosha
 * @Date: 2023/5/17 16:57
 */
public interface BillService {

    int getBillCount(String billCode, String productName);

    List<Bill> getBillList(String productName, String billCode, int currentPageNo, int pageSize);

    Bill getBillById(int parseInt);

    int modify(Bill bill);

    int add(Bill bill);

    boolean smbmsdeletebillById(int parseInt);
}
