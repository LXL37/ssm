<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面</span>
    </div>
    <div class="search">
        <form method="post" action="${pageContext.request.contextPath }/sys/pro/list.html">
            <span>商品名称：</span>
            <input name="proName" type="text" value="${proName }">

            <span>商品编码：</span>
            <input name="proCode" type="text" value="${proCode }">
            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath }/sys/pro/add.html">添加商品</a>
        </form>
    </div>
    <!--商品操作表格-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">商品编码</th>
            <th width="20%">商品名称</th>
            <th width="10%">商品类型</th>
            <th width="10%">商品描述</th>
            <th width="10%">商品价格</th>

            <th width="30%">操作</th>
        </tr>
        <c:forEach var="pro" items="${proList }" varStatus="status">
            <tr>
                <td>
                    <span>${pro.proCode }</span>
                </td>
                <td>
                    <span>${pro.proName }</span>
                </td>
                <td>
                    <span>${pro.proType}</span>
                </td>
                <td>
                    <span>${pro.proDesc}</span>
                </td>
                <td>
                    <span>${pro.price}</span>
                </td>

                <td>
                    <span><a class="viewpro" href="javascript:;" proid=${pro.id } proname=${pro.proName }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifypro" href="javascript:;" proid=${pro.id } proname=${pro.proName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deletepro" href="javascript:;" proid=${pro.id } proname=${pro.proName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removePro">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain" >
            <p>你确定要删除该商品吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/prolist.js"></script>
