package cn.smbms.controller;


import cn.smbms.pojo.Product;

import cn.smbms.service.product.ProductService;
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

@Controller
@RequestMapping("/sys/pro")
public class ProductController {
    private Logger logger = Logger.getLogger(ProductController.class);

    @Resource
    private ProductService proService;

    @RequestMapping(value = "/list.html")
    public String getproList(Model model,
                              @RequestParam(value = "proCode", required = false) String proCode,
                              @RequestParam(value = "proName", required = false) String proName,
                              @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        logger.info("getproList ---- > proCode: " + proCode);
        logger.info("getproList ---- > proName: " + proName);
        logger.info("getproList ---- > pageIndex: " + pageIndex);

        List<Product> proList = null;

        //设置页面容量
        int pageSize = Constants.pageSize;

        //当前页码
        int currentPageNo = 1;

        if(proCode == null){
            proCode = "";
        }
        if(proName == null){
            proName = "";
        }
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/sys/pro/syserror.html";
            }
        }
        //总数量（表）
        int totalCount = 0;
        try {
            totalCount = proService.getProductCount(proCode,proName);
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
            proList = proService.getProductList(proName,proCode,currentPageNo, pageSize);
        } catch (Exception e) {

            e.printStackTrace();
        }
        model.addAttribute("proList", proList);
        model.addAttribute("proCode", proCode);
        model.addAttribute("proName", proName);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "prolist";
    }
    @RequestMapping(value="/add.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("pro") Product pro){
        return "proadd";
    }

    @RequestMapping(value="/syserror.html")
    public String sysError(){
        return "syserror";
    }

    @RequestMapping(value="/view/{id}",method= RequestMethod.GET)
    public String view(@PathVariable String id, Model model, HttpServletRequest request){
        logger.debug("view id===================== "+id);
        Product pro = new Product();
        try {
            pro = proService.getProductById(Integer.parseInt(id));
        } catch (NumberFormatException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }



        model.addAttribute(pro);
        return "proview";
    }
    //TODO
    @RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
    public String getproById(@PathVariable String id,Model model,HttpServletRequest request){
        logger.debug("getproById id===================== "+id);
        Product pro = new Product();
        try {
            pro = proService.getProductById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute(pro);
        return "promodify";
    }

    @RequestMapping(value="/modifysave.html",method=RequestMethod.POST)
    public String modifyproSave(Product pro, HttpSession session, HttpServletRequest request,
                                 @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){
        logger.debug("modifyproSave id===================== "+pro.getId());


         try {
                if(proService.modify(pro)){
                    return "redirect:/sys/pro/list.html";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        return "promodify";
    }

    @RequestMapping(value="/addsave.html",method=RequestMethod.POST)
    public String addproSave(Product pro,HttpSession session,HttpServletRequest request,
                              @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){



            try {
                if(proService.add(pro)){
                    return "redirect:/sys/pro/list.html";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        return "proadd";
    }

    @RequestMapping(value="/del.json",method=RequestMethod.POST)
    @ResponseBody
    public Object delproById(@RequestParam(value="proid") String id){
        logger.debug("delproById proid ===================== "+id);
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            boolean flag = false;
            try {
                flag = proService.smbmsdeleteproById(Integer.parseInt(id));
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
