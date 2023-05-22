<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面 >> 商品修改页</span>
    </div>
    <div class="proAdd">
        <form id="proForm" name="proForm" method="post" action="${pageContext.request.contextPath }/sys/pro/modifysave.html" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${pro.id }"/>
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div class="">
                <label for="proCode">订单编码：</label>
                <input type="text" name="proCode" id="proCode" value="${pro.proCode }" readonly="readonly">
            </div>
            <div>
                <label for="proName">订单名称：</label>
                <input type="text" name="proName" id="proName" value="${pro.proName }">
                <font color="red"></font>
            </div>

            <div>
                <label for="proType">商品类型：</label>
                <input type="text" name="proType" id="proType" value="${pro.proType }">
                <font color="red"></font>
            </div>

            <div>
                <label for="proDesc">商品描述：</label>
                <input type="text" name="proDesc" id="proDesc" value="${pro.proDesc }">
                <font color="red"></font>
            </div>

            <div>
                <label for="price">价格：</label>
                <input type="text" name="price" id="price" value="${pro.price }">
            </div>


            <div class="proAddBtn">
                <input type="button" name="save" id="save" value="保存">
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/promodify.js"></script>