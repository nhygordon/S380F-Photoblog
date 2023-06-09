<!DOCTYPE html>
<html>
<head>
  <title>Customer Support</title>
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<h2>Edit Blog #${blog.id}</h2>
<form:form method="POST" enctype="multipart/form-data"
           modelAttribute="blogForm">
  <form:label path="subject">Subject</form:label><br/>
  <form:input type="text" path="subject" /><br/><br/>
  <form:label path="body">Body</form:label><br/>
  <form:textarea path="body" rows="5" cols="30" /><br/><br/>
  <b>Add more photos</b><br />
  <input type="file" name="photos" multiple="multiple"/><br/><br/>
  <input type="submit" value="Save"/><br/><br/>
</form:form>
<a href="<c:url value="/blog" />">Return to list blogs</a>
</body>
</html>
