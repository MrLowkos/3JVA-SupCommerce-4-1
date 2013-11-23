<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html>
<html lang="${pageContext.request.locale.language}">
 				
<head>
 	<meta charset="UTF-8">
 	<meta content="IE=edge" http-equiv="X-UA-Compatible">
 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
 	<title>ShowProduct | SupCommerce</title>
 	<%-- CSS bootstrap 3.0 --%> 
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 	<link rel="stylesheet" href="<c:url value="/css/style.css"/>">					
</head>
 					
<body>

<jsp:include page="/WEB-INF/template/header.jsp"/>

<section id="main-container" class="container">
	<div class="row">
		<h1 class="col-sx-12 col-sm-12 col-md-12 col-lg-12">Product Details</h1>
		<c:choose>
			<c:when test="${not empty error}">
				<div class="alert alert-danger col-sx-12 col-sm-12 col-md-12 col-lg-12">
					<h3><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${error}"/></h3>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-sx-12 col-sm-6 col-md-4 col-lg-3">
					<article class="panel panel-primary">												
						<header class="panel-heading">
							<form class="pull-right" action="<c:url value="/auth/removeProduct?id=${product.id}"/>" method="post">
								<button type="submit" class="close" >&times;</button>
							</form>
							<h3><span class="glyphicon glyphicon-tag"></span>&nbsp; Product ID: <c:out value="${product.id}"/></h3>
						</header>
						<section class="panel-body">
					       <p>Product name: <span class="item-info"><c:out value="${product.name}"/></span></p>
					       <p class="description">Product description: <span class="item-info"><c:out value="${product.content}"/></span></p>
					       <p>Product price: <span class="item-info"><fmt:formatNumber value="${product.price}" type="currency"/></span></p>
					    </section>
				    </article>
			    </div>
			</c:otherwise>
		</c:choose>
	</div>
</section>
		 
<jsp:include page="/WEB-INF/template/footer.jsp"/>

</body>
</html>