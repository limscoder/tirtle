<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:layout>
  <div id="splash" class="content content-centered">
    <span class="content-title">Set Tirtle Value: <c:out value="${tirtle.label}" /></span>
    
    
    <form:form modelAttribute="setTirtleForm" action="/set/${tirtle.id}/" method="post">
      <dl>
        <dd><form:label for="value" path="value">Value -- enter a new tirtle value.</form:label></dd>
        <dt><form:errors path="value" class="error" /><form:input path="value" placeholder="8" pattern="^\d+\.*\d*$"/></dt>
      </dl>
      
      <button type="submit" class="button">Set Tirtle Value</button>
    </form:form>
</t:layout>