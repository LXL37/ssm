var proCode = null;
var proName = null;
var proType = null;
var proDesc = null;
var addBtn = null;
var backBtn = null;

$(function(){
    proCode = $("#proCode");
    proName = $("#proName");
    proType = $("#proType");
    proDesc = $("#proDesc");
    addBtn = $("#add");
    backBtn = $("#back");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    proCode.next().html("*");
    proName.next().html("*");
    proType.next().html("*");
    proDesc.next().html("*");



    addBtn.bind("click",function(){
     
            if(confirm("是否确认提交数据")){
                $("#proForm").submit();
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