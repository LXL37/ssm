package cn.smbms.controller;

import cn.smbms.pojo.Bill;

import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: alyosha
 * @Date: 2023/5/17 16:57
 */
@Controller
@RequestMapping("/sys/bill")
public class BillController {
    private Logger logger = Logger.getLogger(BillController.class);

    @Resource
    private BillService billService;

    @RequestMapping(value = "/list.html")
    public String getBillList(Model model,
                              @RequestParam(value = "billCode", required = false) String billCode,
                              @RequestParam(value = "productName", required = false) String productName,
                              @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        logger.info("getBillList ---- > billCode: " + billCode);
        logger.info("getBillList ---- > productName: " + productName);
        logger.info("getBillList ---- > pageIndex: " + pageIndex);

        List<Bill> billList = null;

        //设置页面容量
        int pageSize = Constants.pageSize;

        //当前页码
        int currentPageNo = 1;

        if(billCode == null){
            billCode = "";
        }
        if(productName == null){
            productName = "";
        }
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/sys/bill/syserror.html";
            }
        }
        //总数量（表）
        int totalCount = 0;
        try {
            totalCount = billService.getBillCount(billCode,productName);
        } catch (Exception e) {

            e.printStackTrace();
        }
        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        try {
            billList = billService.getBillList(productName,billCode,currentPageNo, pageSize);
        } catch (Exception e) {

            e.printStackTrace();
        }
        model.addAttribute("billList", billList);
        model.addAttribute("billCode", billCode);
        model.addAttribute("productName", productName);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "billlist";
    }
    @RequestMapping(value="/add.html",method=RequestMethod.GET)
    public String add(@ModelAttribute("bill") Bill bill){
        return "billadd";
    }

    @RequestMapping(value="/syserror.html")
    public String sysError(){
        return "syserror";
    }

    @RequestMapping(value="/view/{id}",method= RequestMethod.GET)
    public String view(@PathVariable String id, Model model, HttpServletRequest request){
        logger.debug("view id===================== "+id);
        Bill bill = new Bill();
        try {
            bill = billService.getBillById(Integer.parseInt(id));
        } catch (NumberFormatException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }



        model.addAttribute(bill);
        return "billview";
    }
    //TODO
    @RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
    public String getBillById(@PathVariable String id,Model model,HttpServletRequest request){
        logger.debug("getbillById id===================== "+id);
        Bill bill = new Bill();
        try {
            bill = billService.getBillById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute(bill);
        return "billmodify";
    }

    @RequestMapping(value="/modifysave.html",method=RequestMethod.POST)
    public String modifybillSave(Bill bill, HttpSession session, HttpServletRequest request,
                                     @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){
        logger.debug("modifybillSave id===================== "+bill.getId());

            bill.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
            bill.setModifyDate(new Date());
            try {
                if(billService.modify(bill)>0){
                    return "redirect:/sys/bill/list.html";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        return "billmodify";
    }

    @RequestMapping(value="/addsave.html",method=RequestMethod.POST)
    public String addbillSave(Bill bill,HttpSession session,HttpServletRequest request,
                                  @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){


            bill.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
            bill.setCreationDate(new Date());

            try {
                if(billService.add(bill)>0){
                    return "redirect:/sys/bill/list.html";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        return "billadd";
    }

    @RequestMapping(value="/del.json",method=RequestMethod.POST)
    @ResponseBody
    public Object delbillById(@RequestParam(value="proid") String id){
        logger.debug("delbillById proid ===================== "+id);
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            boolean flag = false;
            try {
                flag = billService.smbmsdeletebillById(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(flag){//删除成功
                resultMap.put("delResult", "true");
            }else{//删除失败
                resultMap.put("delResult", "false");
            }
        }else{
            resultMap.put("delResult", "notexit");
        }
        return JSONArray.toJSONString(resultMap);
    }
}