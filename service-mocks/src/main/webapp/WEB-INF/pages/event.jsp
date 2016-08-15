<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Spring - Movies | ${event.name}</title>
		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
	</head>
	<body>
		<!---start-wrap---->
			<!---start-header---->
			<div class="header">
				<div class="wrap">
				<!---start-logo---->
				<div class="logo">
					<a href="movies/${event.name}"><img src="images/gridallbum${event.id}.png" title="logo" /></a>
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
					<div class="clear"> </div>
					<div class="type-videos">
						<h3>Schedule</h3>
						<ul>
							<c:forEach items="${assignments}" var="assignment">
								<li>${assignment.auditorium.name} Time: ${assignment.dateTime}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="right-content">
				<div class="right-content-heading">
					<div class="right-content-heading-left">
						<h3><c:out value="${event.name}"/></h3>
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
				<div class="inner-page">
				<div class="title">
					<h3>Price: <c:out value="${event.name}"/></h3>
					<ul>
						<li><h4>Rating: <c:out value="${event.rating}"/></h4></li>
					</ul>
				</div>
<!-- 				<div class="video-inner"> -->
<!-- 					<iframe src="//player.vimeo.com/video/24681824" width="100%" height="500px" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>  -->
<!-- 				</div> -->
				<div class="clear"> </div>
				<div class="video-details">
					<ul>
						<li><p>Uploaded on <a href="#">June 21, 2013</a> by <a href="#">Lorem</a></p></li>
						<li><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</span></li>
					</ul>
				</div>
				<div class="clear"> </div>
				<div class="tags">
					<ul>
						<li><h3>Tags:</h3></li>
						<li><a href="#">Games</a> ,</li>
						<li><a href="#">HD-Videos</a></li>
					</ul>
				</div>
				<div class="clear"> </div>
				<div class="share-artical">
		  				<h3> Also share on</h3>
		  					<ul>
		  						<li><a href="#"><img src="images/facebook.png" title="facebook">Facebook</a></li>
		  						<li><a href="#"><img src="images/twiter.png" title="Twitter">Twiiter</a></li>
		  						<li><a href="#"><img src="images/in.png" title="linked-in">Linked-in</a></li>
		  						<li><a href="#"><img src="images/google+.png" title="google+">Google+</a></li>
		  						<li><a href="#"><img src="images/pintrest.png" title="pintrest">Pintrest</a></li>
		  						<li><a href="#"><img src="images/rss.png" title="rss">Rss</a></li>
		  					</ul>
		  		</div>
		  		<div class="artical-commentbox">
		  						 	<h3>leave a comment</h3>
		  						 	<div class="table-form">
									<form>
										<input type="text" class="textbox" value="Name:" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Name';}">
										<input type="text" class="textbox" value="Email:" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}">
										<input type="text" class="textbox" value="Phone:" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Phone';}">
									</form>
									<a href="#">submit comment</a>
								</div>
		  						 </div>
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

