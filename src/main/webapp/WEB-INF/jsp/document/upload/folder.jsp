<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="table table-hover">
    <thead>
        <tr>
            <th>文件名</th>
            <th>文件大小</th>
            <th>最后修改日期</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
       <c:forEach var="item"  items="${dirInfo}" varStatus="status">
           <c:if test="${status.index%2==0}">
           <tr class="active">
           </c:if>
           <c:if test="${status.index%2==1}">
              <tr class="warning">
           </c:if>
            <tr class="warning">
               <td>${item.name}</td>
               <td>${item.size}</td>
               <td>${item.lastModifyTime}</td>
               <td>
                   <button>下载</button>&nbsp;<button>删除</button>
               </td>
          </tr>
       </c:forEach>
    </today>
</table>


