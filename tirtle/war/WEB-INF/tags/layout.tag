<%@tag description="Main Layout Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
  <head>
    <title>tirtle</title>
    
    <link rel="stylesheet" href="<c:url value="/assets/css/tirtle.css" />" type="text/css" media="screen" />
  </head>

  <body>
    <div id="header">
      <a href="<c:url value="/" />"><span class="content-title">tirtle</span></a>
    </div>
  
    <div id="content-wrapper">
      <jsp:doBody/>
    </div>
    
    <div id="footer">
      <ul class="horizontal" style="float: right">
        <li><a href="<c:url value="/random/" />">random tirtle</a></li>
        <li>|</li>
        <li><a href="http://www.limscoder.com/2011/07/tirtle-spring-web-mvc-project-running.html">about</a></li>
        <li>|</li>
        <li><a href="https://github.com/limscoder/tirtle">source</a></li>
      </ul>
    </div>
  </body>
</html>