<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <c:forEach var="tirtle" items="${tirtles}">
  <div id="splash" class="content content-centered">
    <span class="content-title"><c:out value="${tirtle['tirtle'].label}" /></span>

    <div class="tirtle-value-wrapper">
    <span class="tirtle-value">
      <c:choose>
        <c:when test="${empty tirtle['lastTirtleItem']}">0</c:when>
        <c:otherwise><c:out value="${tirtle['lastTirtleItem'].value}" /></c:otherwise>
      </c:choose>
    </span>
    </div>
    
    <c:if test="${editable}">
    <div class="button-box">
      <a href="<c:url value="/set/${tirtle['tirtle'].id}/" />" class="button">Set Tirtle Value</a>
      <a href="<c:url value="/delete/${tirtle['tirtle'].id}/" />" class="button">Delete Tirtle</a>
    </div>
    </c:if>
  </div>
  </c:forEach>

  <c:if test="${tirtleCount < 1}">
  <div id="splash" class="content content-centered">
    <span class="content-title">user has no tirtles</span>
  </div>
  </c:if>
  
  <c:if test="${editable}">
  <div class="button-box">
    <a href="<c:url value="/create/" />" class="button button-big">Add New Tirtle</a>
    <a href="<c:url value="/share/" />" class="button button-big">Share This Page</a>
  </div>
  </c:if>
</t:layout>