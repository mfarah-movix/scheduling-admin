<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>Admin SP</title>
	
		<!-- CSS -->
	<link href="css/demo_page.css" rel="stylesheet" type="text/css" />
	<link href="css/demo_table.css" rel="stylesheet" type="text/css" />      
	<link href="css/demo_table_jui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/DT_bootstrap.css">
	<!-- Scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery.dataTables.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready( function () {

		} );
		
	</script>
	
	<link rel="stylesheet" href="css/site.css">
	<link rel="stylesheet" href="css/pygments.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	
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
        </ul>
        <ul class="nav pull-right">
            <li><a href="mailto:dave@fontawesome.io"><i class="icon-user"></i>&nbsp; jperez</a></li>
        </ul>
    </div>
</div>

<div class="header">Scheduling / SUS</div>

<div class="navbar navbar-inverse navbar-static-top">
    <div class="navbar-inner">
        <ul class="nav">
            <li class="active"><a href="#">Eventos Calendarizados</a></li>
            <li><a href="#">Calendario</a></li>
            <li><a href="#">Falta Aprobación</a></li>
            <li><a href="#">Historial</a></li>
        </ul>
    </div>
</div>

<fieldset>
	<form class="form-inline">
		<div>
			<div class="span2" style="float:left;">
				<label>Operador</label>
				<select>
					<option value="1">ENTEL</option>
				</select>
			</div>
			<div class="span2" style="float:left;">
				<label>Producto</label>
				<select >
					<option value="1">SUS</option>
				</select>
			</div>
		
			<div class="span3" style="float:left;">
				<label>ServicioPrecio</label>
				<input type="text" />
			</div>
		
			<div class="span1" style="float:left;">
				<label>Tipo</label>
				<select>
					<option value="1">Cobro</option>
				</select>
			</div>
		
			<div class="span2" style="float:left;">
				<label>Estado</label>
				<select disabled="disabled">
					<option value="1">Activo</option>
				</select>
			</div>
		
			<div class="span3" style="float:left;">
				<label>Comentario</label>
				<input type="text" />
			</div>
		</div>
	
		<div style="clear:left;">
		</div>
	
		<hr>
		<div>
			<div style="width:200px; float:left;">
				<div>
					<label>Días</label>
					<select>
						<option>LU a DO</option>
					</select>
				</div>
				<a style="position: absolute; margin-left: auto; margin-right: auto;">Eliminar</a>
	
			</div>
			Mismo día
		</div>
	</form>
</fieldset>

<div class="myFooter">
   Movix - 2013
</div>

</body>
</html>