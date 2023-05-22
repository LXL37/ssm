var proObj;

//商品管理页面上点击删除按钮弹出删除框(prolist.jsp)
function deletePro(obj){
    $.ajax({
        type:"POST",
        url:path+"/sys/pro/del.json",
        data:{proid:obj.attr("proid")},
        dataType:"json",
        success:function(data){
            if(data.delResult == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delResult == "false"){//删除失败
                //alert("对不起，删除商品【"+obj.attr("proname")+"】失败");
                changeDLGContent("对不起，删除商品【"+obj.attr("proname")+"】失败");
            }else if(data.delResult == "notexist"){
                //alert("对不起，商品【"+obj.attr("proname")+"】不存在");
                changeDLGContent("对不起，商品【"+obj.attr("proname")+"】不存在");
            }
        },
        error:function(data){
            //alert("对不起，删除失败");
            changeDLGContent("对不起，删除失败");
        }
    });
}

function openYesOrNoDLG(){
    $('.zhezhao').css('display', 'block');
    $('#removePro').fadeIn();
}

function cancleBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removePro').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
$(function(){
    $(".viewPro").on("click",function(){
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        window.location.href=path+"/sys/pro/view/"+ obj.attr("proid");
    });

    $(".modifyPro").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/sys/pro/modify/"+ obj.attr("proid");
    });

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deletePro(proObj);
    });

    $(".deletePro").on("click",function(){
        proObj = $(this);
        changeDLGContent("你确定要删除商品【"+proObj.attr("proname")+"】吗？");
        openYesOrNoDLG();
    });

    /*	$(".deletePro").on("click",function(){
            var obj = $(this);
            if(confirm("你确定要删除商品【"+obj.attr("proname")+"】吗？")){
                $.ajax({
                    type:"GET",
                    url:path+"/jsp/pro.do",
                    data:{method:"delpro",proid:obj.attr("proid")},
                    dataType:"json",
                    success:function(data){
                        if(data.delResult == "true"){//删除成功：移除删除行
                            alert("删除成功");
                            obj.parents("tr").remove();
                        }else if(data.delResult == "false"){//删除失败
                            alert("对不起，删除商品【"+obj.attr("proname")+"】失败");
                        }else if(data.delResult == "notexist"){
                            alert("对不起，商品【"+obj.attr("proname")+"】不存在");
                        }else{
                            alert("对不起，该商品【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
                        }
                    },
                    error:function(data){
                        alert("对不起，删除失败");
                    }
                });
            }
        });*/
});