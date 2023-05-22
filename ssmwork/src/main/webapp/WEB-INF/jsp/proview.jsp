<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面 >> 信息查看</span>
    </div>
    <div class="proView">
        <p><strong>商品编码：</strong><span>${pro.proCode }</span></p>
        <p><strong>商品名称：</strong><span>${pro.proName }</span></p>
        <p><strong>商品类型：</strong><span>${pro.proType }</span></p>
        <p><strong>商品描述：</strong><span>${pro.proDesc }</span></p>
        <p><strong>商品价格：</strong><span>${pro.price }</span></p>




        <div class="proAddBtn">
            <input type="button" id="back" name="back" value="返回" >
        </div>
    </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/proview.js"></script>
