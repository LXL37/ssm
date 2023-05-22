var roleCode = null;
var roleName = null;
var modifyBy = null;
var createdBy = null;
var addBtn = null;
var backBtn = null;

$(function(){
    roleCode = $("#roleCode");
    roleName = $("#roleName");
    modifyBy = $("#modifyBy");
    createdBy = $("#createdBy");
    addBtn = $("#add");
    backBtn = $("#back");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    roleCode.next().html("*");
    roleName.next().html("*");
    modifyBy.next().html("*");
    createdBy.next().html("*");



    addBtn.bind("click",function(){
      
            if(confirm("是否确认提交数据")){
                $("#roleForm").submit();
            }
        
    });

    backBtn.on("click",function(){
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