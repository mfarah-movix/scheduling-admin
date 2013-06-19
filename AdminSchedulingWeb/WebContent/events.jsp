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
			oTable = $('#example').dataTable({
				"sDom" : 'rtlp<"clear">',
				"sPaginationType": "bootstrap",
				"bAutoWidth": false, // Disable the auto width calculation 
				"aoColumns": [
					{ "sWidth": "8%" },
					{ "sWidth": "7%" },
					{ "sWidth": "7%" },
					{ "sWidth": "22%"},
					{ "sWidth": "7%"},
					{ "sWidth": "7%"},
					{ "sWidth": "28%"},
					{ "sWidth": "14%"}
				]
			});
			$.extend( $.fn.dataTableExt.oStdClasses, {
			    "sWrapper": "dataTables_wrapper form-inline"
			} );
			
			$('#search-box').keyup(function() {
				oTable.fnFilter( $(this).val() );
			});
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
        <form class="navbar-search pull-right">
            <input id="search-box" type="text" class="search-query" placeholder="Search">
        </form>
    </div>
</div>

<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
	<thead>
		<tr>
	        <th>Operador</th>
	        <th>Producto</th>
	        <th>ProductId</th>
	        <th>SP</th>
	        <th>Tipo</th>
	        <th>Días</th>
	        <th>Horario</th>
	        <th>Acciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="event" items="${events}">
		<tr class="odd_gradeA">
	        <td>${event.getOperador().toString()}</td>
			<td>${event.getProducto()}</td>
	        <td>${event.getProductId()}</td>
	        <td>${event.getSP() }</td>
	        <td>${event.getTipo() }</td>
	        <td>${event.getDias() }</td>
	        <td>${event.getHorario() }</td>
	        <td><i class="icon-ok-sign icon-large"></i></td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
<div class="myFooter">
   Movix - 2013
</div>

</body>
</html>