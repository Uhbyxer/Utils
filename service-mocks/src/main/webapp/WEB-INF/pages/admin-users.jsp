
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Admin | Users</title>
		
		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
	</head>
	<body>
		<!---start-wrap---->
			<!---start-header---->
			<div class="header">
				<div class="wrap">
				<!---start-logo---->
				<div class="logo">
					<a href="index.html"><img src="images/logo.png" title="logo" /></a>
				</div>
				<!---End-logo---->
				<!---start-top-menu-search---->
				<div class="top-menu">
					<div class="top-nav">
						<ul>
							<li><a href="#">Blog</a></li>
							<li><a href="#">Videos</a></li>
							<li><a href="categories.html">Categories</a></li>
							<li><a href="contact.html">Contact</a></li>
						</ul>
					</div>
					<div class="search">
						<form>
							<input type="text" class="textbox" value="Search:" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
							<input type="submit" value=" " />
						</form>
					</div>
					<div class="clear"> </div>
				</div>
				<div class="clear"> </div>
				<!---End-top-menu-search---->
			</div>
			<!---End-header---->
		</div>
		<div class="clear"> </div>
		<!---start-content---->
		<div class="main-content">
			<div class="wrap">
			<div class="left-sidebar">
				<div class="sidebar-boxs">
					<div class="sidebar-box">
						<h2>Allbum-Name</h2>
						<a href="#"><img src="images/allbum.jpg" title="image-name" /></a>
						<h3>LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT.</h3>
						<div class="rate">
							<b style="width:100%"> </b> 
							<a href="#">Rate 1/5</a>
							<a href="#">Rate 2/5</a>
							<a href="#">Rate 3/5</a>
							<a href="#">Rate 4/5</a>
							<a href="#">Rate 5/5</a>
							<div class="clear"> </div>
						</div>
						<input type="button" value="Rate it" />
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, ut labore et dolore magna aliqua.</p>
					</div>
					<div class="clear"> </div>
					<div class="type-videos">
						<h3>Our cinemas:</h3>
						<ul>
							<c:forEach items="${auditoriums}" var="auditorium">
								<li><a href="#"><c:out value="${auditorium.name}"/></a></li>
							</c:forEach>

						</ul>
					</div>
				</div>
			</div>
			<div class="right-content">
				<div class="right-content-heading">
					<div class="right-content-heading-left">
						<h3>Latest Colletcions of videos</h3>
					</div>
					<div class="right-content-heading-right">
						<div class="social-icons">
			                <ul>
			                    <li><a class="facebook" href="#" target="_blank"> </a></li>
			                    <li><a class="twitter" href="#" target="_blank"></a></li>
			                    <li><a class="googleplus" href="#" target="_blank"></a></li>
			                    <li><a class="pinterest" href="#" target="_blank"></a></li>
			                    <li><a class="dribbble" href="#" target="_blank"></a></li>
			                    <li><a class="vimeo" href="#" target="_blank"></a></li>
			                </ul>
					</div>
					</div>
					<div class="clear"> </div>
				</div>
				<c:set var="i" value="${0}"/>
				<div class="content-grids">
					<c:forEach items="${users}" var="user">
						<c:set var="i" value="${i+1}"/>
	
						<c:choose>
    						<c:when test="${i % 4 == 0}">
    							<c:set var="gridClass" value="content-grid last-grid"/>
    						</c:when>
 							<c:otherwise>
 								<c:set var="gridClass" value="content-grid"/>
 						   </c:otherwise>
						</c:choose>
						
						<div class="${gridClass}">
							<a href="admin-users/${user.id}"><img src="images/user${user.id}.png" title="allbum-name" /></a>
							<h3>${user.name}</h3>
							<ul>
								<li><a href="#"><img src="images/likes.png" title="image-name" /></a></li>
								<li><a href="#"><img src="images/views.png" title="image-name" /></a></li>
								<li><a href="#"><img src="images/link.png" title="image-name" /></a></li>
							</ul>
							<a class="button" href="singlepage.html">Watch now</a>
						</div>
					</c:forEach>
			
					<div class="clear"> </div>
					<!---start-pagenation-----> 
<!-- 					<div class="pagenation"> -->
<!-- 						<ul> -->
						
<%-- 						<c:forEach begin="1" end="${pages}" var="i"> --%>
<%-- 							<c:choose> --%>
<%--     							<c:when test="${i == page}"> --%>
<%--     								<c:set var="pageClass" value="pagenation-selected"/> --%>
<%--     							</c:when> --%>
<%--  								<c:otherwise> --%>
<%--  									<c:set var="pageClass" value=""/> --%>
<%--  							   </c:otherwise> --%>
<%-- 							</c:choose>						 --%>
						
							
<%--     						<li><a class="${pageClass}" href="home?page=${i}"><c:out value="${i}"/></a></li> --%>
<%-- 						</c:forEach>	 --%>
<%-- 						<c:if test="${page < pages}"> --%>
<%-- 							<li><a href="home?page=${page + 1}">Next</a></li> --%>
<%-- 						</c:if>						 --%>
						
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 					<div class="clear"> </div> -->
					<!---End-pagenation----->
				</div>
			</div>
			<div class="clear"> </div>
			</div>
		</div>
		<div class="clear"> </div>
		<!---End-content---->
		<!---start-copy-right---->
		<div class="copy-right">
			<p>Design by <a href="http://w3layouts.com/">W3layouts</a></p>
		</div>
		<!---End-copy-right---->
		<!---End-wrap---->
	</body>
</html>

