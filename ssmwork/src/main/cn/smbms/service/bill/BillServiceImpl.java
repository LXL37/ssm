package cn.smbms.service.bill;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: alyosha
 * @Date: 2023/5/17 16:57
 */
@Service
public class BillServiceImpl implements BillService{
    @Resource
    private BillMapper billMapper;
    @Override
    public int getBillCount(String billCode, String productName) {
        return billMapper.getBillCount(billCode, productName);
    }

    @Override
    public List<Bill> getBillList(String productName, String billCode, int currentPageNo, int pageSize) {
        currentPageNo = (currentPageNo-1)*pageSize;
        return billMapper.getBillList(productName, billCode, currentPageNo, pageSize);
    }

    @Override
    public Bill getBillById(int parseInt)  {
        return billMapper.getBillById(parseInt);
    }

    @Override
    public boolean modify(Bill bill) {
        return billMapper.modify(bill);
    }

    @Override
    public boolean add(Bill bill) {
        return billMapper.add(bill);
    }

    @Override
    public boolean smbmsdeletebillById(int parseInt) {
        return billMapper.deleteBillById(parseInt);
    }
}
