<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <div id="splash" class="content content-centered">
    <a href="<c:url value="/" />"><span class="content-title">tirtle</span></a>
    
    <p class="highlight">Track and share life's daily numbers.</p>
    
    <ul class="highlight">
      <li>How many miles have I run today?</li>
      <li>How many hours have I worked today?</li>
      <li>How many calories have I eaten today?</li>
      <li>How many words have I written today?</li>
    </ul>
    
    <div class="button-box">
      <a href="<c:url value="/random/" />" class="button button-big">See Example Tirtle</a> <a href="<c:url value="/login/" />" class="button button-big">Login or Sign-Up</a>
    </div>
  </div>
</t:layout>