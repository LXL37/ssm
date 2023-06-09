var roleObj;

//角色管理页面上点击删除按钮弹出删除框(rolelist.jsp)
function deleteRole(obj){
    $.ajax({
        type:"POST",
        url:path+"/sys/role/del.json",
        data:{roleid:obj.attr("roleid")},
        dataType:"json",
        success:function(data){
            if(data.delResult == "true"){//删除成功：移除删除行
                cancleBtn();
                obj.parents("tr").remove();
            }else if(data.delResult == "false"){//删除失败
                //alert("对不起，删除角色【"+obj.attr("rolename")+"】失败");
                changeDLGContent("对不起，删除角色【"+obj.attr("rolename")+"】失败");
            }else if(data.delResult == "notexist"){
                //alert("对不起，角色【"+obj.attr("rolename")+"】不存在");
                changeDLGContent("对不起，角色【"+obj.attr("rolename")+"】不存在");
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
    $('#removeRole').fadeIn();
}

function cancleBtn(){
    $('.zhezhao').css('display', 'none');
    $('#removeRole').fadeOut();
}
function changeDLGContent(contentStr){
    var p = $(".removeMain").find("p");
    p.html(contentStr);
}
$(function(){
    $(".viewRole").on("click",function(){
        //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
        var obj = $(this);
        window.location.href=path+"/sys/role/view/"+ obj.attr("roleid");
    });

    $(".modifyRole").on("click",function(){
        var obj = $(this);
        window.location.href=path+"/sys/role/modify/"+ obj.attr("roleid");
    });

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        deleteRole(roleObj);
    });

    $(".deleteRole").on("click",function(){
        roleObj = $(this);
        changeDLGContent("你确定要删除角色【"+roleObj.attr("rolename")+"】吗？");
        openYesOrNoDLG();
    });

    /*	$(".deleteRole").on("click",function(){
            var obj = $(this);
            if(confirm("你确定要删除角色【"+obj.attr("rolename")+"】吗？")){
                $.ajax({
                    type:"GET",
                    url:path+"/jsp/role.do",
                    data:{method:"delrole",roleid:obj.attr("roleid")},
                    dataType:"json",
                    success:function(data){
                        if(data.delResult == "true"){//删除成功：移除删除行
                            alert("删除成功");
                            obj.parents("tr").remove();
                        }else if(data.delResult == "false"){//删除失败
                            alert("对不起，删除角色【"+obj.attr("rolename")+"】失败");
                        }else if(data.delResult == "notexist"){
                            alert("对不起，角色【"+obj.attr("rolename")+"】不存在");
                        }else{
                            alert("对不起，该角色【"+obj.attr("rolename")+"】下有【"+data.delResult+"】条订单，不能删除");
                        }
                    },
                    error:function(data){
                        alert("对不起，删除失败");
                    }
                });
            }
        });*/
});