<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <div id="splash" class="content content-centered">
    <span class="content-title">Permanently Delete: <c:out value="${tirtle.label}" /></span>
    
    <form action="<c:url value="/delete/${tirtle.id}/" />" method="post">
      <button type="submit" class="button">Delete Tirtle</button>
    </form>
</t:layout>