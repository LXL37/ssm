package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;

import cn.smbms.service.role.RoleService;
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
 * @Date: 2023/5/18 18:21
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
  
        private Logger logger = Logger.getLogger(cn.smbms.controller.RoleController.class);

        @Resource
        private RoleService roleService;

        @RequestMapping(value = "/list.html")
        public String getRoleList(Model model,
                                  @RequestParam(value = "roleCode", required = false) String roleCode,
                                  @RequestParam(value = "roleName", required = false) String roleName,
                                  @RequestParam(value = "pageIndex", required = false) String pageIndex) {
            logger.info("getRoleList ---- > roleCode: " + roleCode);
            logger.info("getRoleList ---- > roleName: " + roleName);
            logger.info("getRoleList ---- > pageIndex: " + pageIndex);

            List<Role> roleList = null;

            //设置页面容量
            int pageSize = Constants.pageSize;

            //当前页码
            int currentPageNo = 1;

            if(roleCode == null){
                roleCode = "";
            }
            if(roleName == null){
                roleName = "";
            }
            if(pageIndex != null){
                try{
                    currentPageNo = Integer.valueOf(pageIndex);
                }catch(NumberFormatException e){
                    return "redirect:/sys/role/syserror.html";
                }
            }
            //总数量（表）
            int totalCount = 0;
            try {
                totalCount = roleService.getRoleCount(roleCode,roleName);
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
                roleList = roleService.getRoleList(roleCode,roleName,currentPageNo, pageSize);
            } catch (Exception e) {

                e.printStackTrace();
            }
            model.addAttribute("roleList", roleList);
            model.addAttribute("roleCode", roleCode);
            model.addAttribute("roleName", roleName);
            model.addAttribute("totalPageCount", totalPageCount);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("currentPageNo", currentPageNo);
            return "rolelist";
        }
        @RequestMapping(value="/add.html",method= RequestMethod.GET)
        public String add(@ModelAttribute("role") Role role){
            return "roleadd";
        }

        @RequestMapping(value="/syserror.html")
        public String sysError(){
            return "syserror";
        }

        @RequestMapping(value="/view/{id}",method= RequestMethod.GET)
        public String view(@PathVariable String id, Model model, HttpServletRequest request){
            logger.debug("view id===================== "+id);
            Role role = new Role();
            try {
                role = roleService.getRoleById(Integer.parseInt(id));
            } catch (NumberFormatException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }



            model.addAttribute(role);
            return "roleview";
        }
        //TODO
        @RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
        public String getRoleById(@PathVariable String id,Model model,HttpServletRequest request){
            logger.debug("getroleById id===================== "+id);
            Role role = new Role();
            try {
                role = roleService.getRoleById(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            model.addAttribute(role);
            return "rolemodify";
        }

        @RequestMapping(value="/modifysave.html",method=RequestMethod.POST)
        public String modifyroleSave(Role role, HttpSession session, HttpServletRequest request,
                                     @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){
            logger.debug("modifyroleSave id===================== "+role.getId());


                role.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
                role.setModifyDate(new Date());
                try {
                    if(roleService.modify(role)>0){
                        return "redirect:/sys/role/list.html";
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            return "rolemodify";
        }

        @RequestMapping(value="/addsave.html",method=RequestMethod.POST)
        public String addroleSave(Role role,HttpSession session,HttpServletRequest request,
                                  @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){

                role.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
                role.setCreationDate(new Date());

                try {
                    if(roleService.add(role)>0){
                        return "redirect:/sys/role/list.html";
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            return "roleadd";
        }

        @RequestMapping(value="/del.json",method=RequestMethod.POST)
        @ResponseBody
        public Object delroleById(@RequestParam(value="proid") String id){
            logger.debug("delroleById proid ===================== "+id);
            HashMap<String, String> resultMap = new HashMap<String, String>();
            if(!StringUtils.isNullOrEmpty(id)){
                boolean flag = false;
                try {
                    flag = roleService.smbmsdeleteroleById(Integer.parseInt(id));
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
