<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>blogs</h2>
<a href="<c:url value="/blog/create" />">Create a blog</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(blogDatabase) == 0}">
        <i>There are no blogs in the system.</i>
    </c:when>
    <c:otherwise>
        <c:forEach items="${blogDatabase}" var="entry">
            blog ${entry.key}:
            <a href="<c:url value="/blog/view/${entry.key}" />">
                <c:out value="${entry.value.subject}"/></a>
            (customer: <c:out value="${entry.value.customerName}"/>)<br />
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
