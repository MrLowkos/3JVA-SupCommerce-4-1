<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="errorMessage" value="No product registered !"/>

<!DOCTYPE html>
<html lang="${pageContext.request.locale.language}">
 				
<head>
 	<meta charset="UTF-8">
 	<meta content="IE=edge" http-equiv="X-UA-Compatible">
 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
 	<title>ListProduct | SupCommerce</title>
 	<%-- CSS bootstrap 3.0 --%> 
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 	<link rel="stylesheet" href="<c:url value="/css/style.css"/>" >					
</head>
 					
<body>

<jsp:include page="/WEB-INF/template/header.jsp"/>

<section id="main-container" class="container">
	<div class="row">
		<h1 class="col-sx-12 col-sm-12 col-md-12 col-lg-12">Product List</h1>
		<c:choose>
			<c:when test="${empty products}">
				<div class="alert alert-danger col-sx-12 col-sm-12 col-md-12 col-lg-12">
					<h3><span class="glyphicon glyphicon-warning-sign"></span>&nbsp; <c:out value="${errorMessage}"/></h3>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach var="p" items="${products}">
					<div class="col-sx-12 col-sm-6 col-md-4 col-lg-3">
						<article class="panel panel-primary">												
							<header class="panel-heading clearfix">
								<form class="pull-right" action="<c:url value="/auth/removeProduct?id=${p.id}"/>" method="post">
									<button type="submit" class="close" >&times;</button>
								</form>
								<h3>
									<a href="<c:url value="/showProduct?id=${p.id}"/>" >
										<span class="glyphicon glyphicon-tag"></span>&nbsp; Product Id: <c:out value="${p.id}"/>
									</a>
								</h3>
							</header>
							<section class="panel-body">
						       <p>Product name: <span class="item-info"><c:out value="${p.name}"/></span></p>
						       <p class="description">Product description: <span class="item-info"><c:out value="${p.content}"/></span></p>
						       <p>Product price: <span class="item-info"><fmt:formatNumber value="${p.price}" type="currency"/></span></p>
						    </section>
					    </article>
				   </div>
			   </c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</section>

<jsp:include page="/WEB-INF/template/footer.jsp"/>

</body>
</html>