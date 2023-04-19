<!DOCTYPE html>
<html>
<head>
  <title>Customer Support</title>
</head>
<body>
<h2>blog #${blogId}: <c:out value="${blog.subject}"/></h2>
[<a href="<c:url value="/blog/delete/${blog.id}" />">Delete</a>]<br/><br/>
<i>Customer Name - <c:out value="${blog.customerName}"/></i><br/><br/>
<c:out value="${blog.body}"/><br/><br/>
<c:if test="${! empty blog.photos}">
  Photos:
  <c:forEach items="${blog.photos}" var="photo" varStatus="status">
    <c:if test="${!status.first}">, </c:if>
    <a href="<c:url value="/blog/${blogId}/photo/${photo.id}" />">
      <c:out value="${photo.name}"/></a>
    [<a href="<c:url value="/blog/${blogId}/delete/${photo.id}" />">Delete</a>]
  </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/blog" />">Return to list blogs</a>
</body>
</html>
