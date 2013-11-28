<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
 				
<head>
 	<meta charset="UTF-8">
 	<meta content="IE=edge" http-equiv="X-UA-Compatible">
 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
 	<title>AddProduct - JSP</title>
 	<%-- CSS bootstrap 3.0 --%> 
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 	<link rel="stylesheet" href="<c:url value="/css/sticky-footer.css"/>">
 	<link rel="stylesheet" href="<c:url value="/css/style.css"/>">					
</head>
 					
<body>

<div id="wrap">
    
	<%@include file="/WEB-INF/template/header.jsp" %>
	
	<section id="main-container" class="container">
		<div class="page-header">
			<h1>Add Product <small>Fill product fields</small></h1>
		</div>
		<form class="form-horizontal" action="addProduct" method="post">
		
			<div class="row form-group ${not empty nameError ? 'has-error' : ''}">	
				<div class="form-group input-group col-xs-6">
					<span class="input-group-addon label-fix"><label for="product-name" class="control-label">Name</label></span>
					<input type="text" name="product-name" id="product-name" class="form-control" value="<c:out value="${name}"/>" required="required"/>
				</div>
				<div class="col-xs-6">
					<c:if test="${not empty nameError}">
						<span class="help-block"><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${nameError}"/></span>
					</c:if>
				</div>
			</div>
			
			<div class="row form-group ${not empty contentError ? 'has-error' : ''}">	
				<div class="form-group input-group col-xs-6">
					<span class="input-group-addon label-fix"><label for="product-content" class="control-label">Description</label></span>
					<input type="text" name="product-content" id="product-content" class="form-control" value="<c:out value="${content}"/>" required="required"/>
				</div>
				<div class="col-xs-6">
					<c:if test="${not empty contentError}">
						<span class="help-block"><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${contentError}"/></span>
					</c:if>
				</div>
			</div>
			
			<div class="row form-group ${not empty priceError ? 'has-error' : ''}">
				<div class="form-group input-group col-xs-6">
					<span class="input-group-addon label-fix"><label for="product-price" class="control-label">Price</label></span>
					<input type="number" step="0.01" min="0" max="1000000" name="product-price" id="product-price" class="form-control" value="<c:out value="${price}"/>" placeholder="0.00" required="required"/>
					<span class="input-group-addon">€</span>					
				</div>
				<div class="col-xs-6">
					<c:if test="${not empty priceError}">
						<span class="help-block"><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${priceError}"/></span>
					</c:if>
				</div>
			</div>
			
			<div class="form-group">
			     <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp; Add</button>
		  	</div>
		
		</form>
		
	</section>
	
	<div id="push"></div>

</div>
	
<%@include file="/WEB-INF/template/footer.jsp" %>

</body>
</html>