<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<h2>${error.title}</h2>

<h3>${error.message}</h3>

<a href="${pageContext.request.contextPath}/${error.link}">Back to the Main Page</a>