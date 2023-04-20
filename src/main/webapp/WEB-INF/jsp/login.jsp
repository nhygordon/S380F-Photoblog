<!DOCTYPE html>
<html>
<head>
  <title>Photo Blog Login</title>
  <style>
    .style{
      width: 300px;
      background-color: cornflowerblue;
      border: 2px solid black;
      padding: 10px;
      font-size: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
    }
  </style>
</head>
<body>
<div class="style">
  <c:if test="${param.error != null}">
    <p>Login failed.</p>
  </c:if>
  <c:if test="${param.logout != null}">
    <p>You have logged out.</p>
  </c:if>
  <h2>Photo Blog Login</h2>
  <form action="login" method="POST">
    <label for="username">Username:</label><br/>
    <input type="text" id="username" name="username"/><br/><br/>
    <label for="password">Password:</label><br/>
    <input type="password" id="password" name="password"/><br/><br/>
    <input type="checkbox" id="remember-me" name="remember-me"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Log In"/>
  </form>
</div>
</body>
</html>
