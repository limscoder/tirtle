<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:layout>
  <div id="splash" class="content content-centered">
    <span class="content-title">Add New Tirtle</span>
    
    <form:form modelAttribute="createTirtleForm" action="/create/" method="post">
      <dl>
        <dd><form:label for="label" path="label">Description -- describe what the tirtle will track</form:label></dd>
        <dt><form:errors path="label" class="error" /><form:input path="label" class="long" placeholder="How many lines of code did I write today?" /></dt>
      </dl>
      
      <button type="submit" class="button">Add New Tirtle</button>
    </form:form>
</t:layout>