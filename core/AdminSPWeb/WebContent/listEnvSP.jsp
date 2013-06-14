<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>Admin SP</title>
	
	<link rel="stylesheet" href="http://fortawesome.github.io/Font-Awesome/assets/css/site.css">
	<link rel="stylesheet" href="http://fortawesome.github.io/Font-Awesome/assets/css/pygments.css">
	<link rel="stylesheet" href="http://fortawesome.github.io/Font-Awesome/assets/font-awesome/css/font-awesome.min.css">
	
	<style>
	        .header{
	            height:70px;
	            background-color:#691760;
	            color: white;
	            font-size:55pt;
	            padding:40px 0px 0px 30px;
	        }
	
	        .myFooter{
	            height:20px;
	            background-color:#691760;
	            color: white;
	            font-size:10pt;
	            padding:5px;
	            text-align:center;
	        }
	    </style>
</head>
<body>

<div class="navbar navbar-inverse navbar-static-top hidden-print">
    <div class="navbar-inner">
        <ul class="nav">
            <li><a href="#">SP</a></li>
            <li class="active"><a href="#">Scheduling</a></li>
            <li><a href="#">Admin3</a></li>
            <li><a href="#">Admin4</a></li>
            <li><a href="#">Admin5</a></li>
            <li><a href="#">Admin6</a></li>
            <li><a href="#">Admin7</a></li>
            <li><a href="#">Admin8</a></li>
            <li><a href="#">Admin9</a></li>
        </ul>
        <ul class="nav pull-right">
            <li><a href="mailto:dave@fontawesome.io"><i class="icon-user"></i>&nbsp; jperez</a></li>
        </ul>
    </div>
</div>

<!--div class="jumbotron hidden-print maincolor">
    <div class="container"><h1>ServicePrice</h1></div>
</div-->

<div class="header">Service Price</div>

<div class="navbar navbar-inverse navbar-static-top">
    <div class="navbar-inner">
        <ul class="nav">
            <li class="active"><a href="#">Envío</a></li>
            <li class="divider-vertical"></li>
            <li><a href="#">Recepción</a></li>
            <li class="divider-vertical"></li>
            <li><a href="#">Billing</a></li>
            <li class="divider-vertical"></li>

        </ul>
        <form class="navbar-search pull-right">
            <input type="text" class="search-query" placeholder="Search">
        </form>
    </div>
</div>

<table class="table table-condensed table-striped table-bordered" style="margin:0px;">
     <tr>
        <th>Estado</th>
        <th>Operador</th>
        <th>Tipo</th>
        <th>Servicio</th>
        <th>Precio</th>
        <th>Canal</th>
        <th>Argumentos</th>
        <th>Cache</th>
        <th width="1" colspan="5">Acciones</th>

    </tr>
    <c:forEach var="i" begin="1" end="5">
    	<tr>
    	<td width="1"><i class="icon-ok-sign icon-large"></i></td>
        <td>Pe Claro</td>
        <td>SMS/WP</td>
        <td>sus3_ClaroSV_7733_cobro</td>
        <td>0.09</td>
        <td>channelClaroSV_Bill</td>
        <td>493|1|7733|Suscripciones_7733</td>
        <td width="1"><i class="icon-ok-sign icon-large"></i></td>
        <td width="1"><i class="icon-play icon-large"></i></td>
        <td width="1"><i class="icon-list-alt icon-large"></i></td>
        <td width="1"><i class="icon-time icon-large"></i></td>
        <td width="1"><i class="icon-edit icon-large"></i></td>
        <td width="1"><i class="icon-trash icon-large"></i></td>
    </tr>
	</c:forEach>
</table>

<div class="myFooter">
   Movix - 2013
</div>

</body>
</html>