var roleCode = null;
var roleName = null;
var modifyBy = null;
var createdBy = null;
var addBtn = null;
var backBtn = null;


function priceReg (value){
    value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
    value = value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//去掉特殊符号￥
    if(value.indexOf(".")>0){
        value = value.substring(0,value.indexOf(".")+3);
    }
    return value;
}


$(function(){
    roleCode = $("#roleCode");
    roleName = $("#roleName");
    modifyBy = $("#modifyBy");
    createdBy = $("#createdBy");

    roleId = $("#roleId");
    addBtn = $("#save");
    backBtn = $("#back");

    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    roleCode.next().html("*");
    roleName.next().html("*");
    modifyBy.next().html("*");
    createdBy.next().html("*");

    roleId.next().html("*");

    $.ajax({
        type:"GET",//请求类型
        url:path+"/sys/role/modify/"+ obj.attr("roleid"),//请求的url
        data:{method:"getrolelist"},//请求参数
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            if(data != null){
                var pid = $("#pid").val();
                $("select").html("");//通过标签选择器，得到select标签，适用于页面里只有一个select
                var options = "<option value=\"0\">请选择</option>";
                for(var i = 0; i < data.length; i++){
                    //alert(data[i].id);
                    //alert(data[i].proName);
                    if(pid != null && pid != undefined && data[i].id == pid ){
                        options += "<option selected=\"selected\" value=\""+data[i].id+"\" >"+data[i].proName+"</option>";
                    }else{
                        options += "<option value=\""+data[i].id+"\" >"+data[i].proName+"</option>";
                    }

                }
                $("select").html(options);
            }
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            validateTip(roleId.next(),{"color":"red"},imgNo+" 获取供应商列表error",false);
        }
    });
    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */

    roleName.on("focus",function(){
        validateTip(roleName.next(),{"color":"#666666"},"* 请输入商品名称",false);
    }).on("blur",function(){
        if(roleName.val() != null && roleName.val() != ""){
            validateTip(roleName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(roleName.next(),{"color":"red"},imgNo+" 商品名称不能为空，请重新输入",false);
        }

    });

    modifyBy.on("focus",function(){
        validateTip(modifyBy.next(),{"color":"#666666"},"* 请输入商品单位",false);
    }).on("blur",function(){
        if(modifyBy.val() != null && modifyBy.val() != ""){
            validateTip(modifyBy.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(modifyBy.next(),{"color":"red"},imgNo+" 单位不能为空，请重新输入",false);
        }

    });

    roleId.on("focus",function(){
        validateTip(roleId.next(),{"color":"#666666"},"* 请选择供应商",false);
    }).on("blur",function(){
        if(roleId.val() != null && roleId.val() != "" && roleId.val() != 0){
            validateTip(roleId.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(roleId.next(),{"color":"red"},imgNo+" 供应商不能为空，请选择",false);
        }

    });

    createdBy.on("focus",function(){
        validateTip(createdBy.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位",false);
    }).on("keyup",function(){
        this.value = priceReg(this.value);
    }).on("blur",function(){
        this.value = priceReg(this.value);
    });

    totalPrice.on("focus",function(){
        validateTip(totalPrice.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位",false);
    }).on("keyup",function(){
        this.value = priceReg(this.value);
    }).on("blur",function(){
        this.value = priceReg(this.value);
    });

    addBtn.on("click",function(){



        if(confirm("是否确认提交数据")){
            $("#roleForm").submit();
        }

    });
    $(function(){
        backBtn = $("#back");
        backBtn.on("click",function(){
            //alert("view : "+referer);
            if(referer != undefined
                && null != referer
                && "" != referer
                && "null" != referer
                && referer.length > 4){
                window.location.href = referer;
            }else{
                history.back(-1);
            }
        });
    });

});