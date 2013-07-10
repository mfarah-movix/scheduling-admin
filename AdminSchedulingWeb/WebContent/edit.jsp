<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>Admin Scheduling</title>
	
	<!-- Scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/bootstrap-select.min.js" type="text/javascript"></script>
	<script src="js/jquery.validate.js" type="text/javascript"></script>
	
	<!-- CSS -->
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
	<link href="css/font-awesome.css" rel="stylesheet" type="text/css" />      
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	
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
        .marginless{
		    margin:0 !important;
		}
		
		input{
			font-size: 13px;
		}
		
		.error{
			color: #b94a48 !important;
			border-color: #b94a48 !important;
			-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075) !important;
     		-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075) !important;
         	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075) !important;
		}
		
		.error :focus{
		  	border-color: #953b39 !important;
		  	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #d59392 !important;
		    -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #d59392 !important;
		    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #d59392 !important;
		}

		#flashMessage{
			background-color:#
		}
		
		label.error {
			display: none !important;
		}
    </style>
    <script type="text/javascript">
    	function joinDay(groupId, dayId){
    		schedule = "";
    		id = "#dayG" + groupId + "D" + dayId;
    		i = 0;
    		$.each($(id + " :input"), function(index, value){
    			if(value.value != ""){
	    			separator = i++ % 2 == 0 ? " - " : ", ";
	    			schedule += $.trim(value.value) + separator;
    			}
    		});
    		return schedule.substring(0,schedule.length - 2);
    	}
    	
    	function joinDayGroup(groupId){
    		schedule = "";
    		$.each($(".dayG" + groupId), function(index, value){
    			schedule += joinDay(groupId, index) + " | ";
    		});
    		return schedule.substring(0,schedule.length - 3);
    	}
    	
    	function setDaysMap(){
    		daysMap = "";
    		var qty = $("select.weekDays").length - 1;
    		for(var i = 0; i < qty; i++){
    			daysMap += $("#weekDays" + i).val() + "=" + joinDayGroup(i) + "/"; 
    		}
    		$("<input>").attr({
    		    type: "hidden",
    		    id: "daysMap",
    		    name: "daysMap",
    		    value: daysMap
    		}).appendTo("#eventsForm");
    		console.log(daysMap);
    	}
    	
    	function reNameDay(groupId, dayId){
    		id = "#dayG" + groupId + "D" + dayId;
    		name = "rangeG"+ groupId + "D" + dayId + "R";
    		rangeId = 0;
    		i = 0;
    		console.log(id);
    		$.each($(id + " :input"), function(index, value){
    			if(i % 2 == 0){
	    			$(value).attr({name: name + rangeId + ".from"});
    			} else {
    				$(value).attr({name: name + rangeId + ".until"});
	    			rangeId++;
    			}
    			i++;
    		});
    	}
    	
    	function reNameDayGroup(groupId){
    		$.each($(".dayG" + groupId), function(index, value){
    			reNameDay(groupId, index);
    		});
    	}
    	
    	function reNameFull(){
    		$.each($(".dayGroup"), function(index, value){
    			reNameDayGroup(index);
    		});
    	}
    	
    	function refreshAddDaysSelect(){
    		var days = new Array("LU", "MA", "MI", "JU", "VI", "SA", "DO");
    		qty = $("select.weekDays").length - 1;
    		for(var i = 0; i < qty; i++){
    			$.each($("#weekDays" + i + " option:selected"), function(index, value){
    				console.log(i + "-" + value.value);
    				days.splice(days.indexOf(value.value), 1);
    				console.log("days:" + days);
    			});
    		}
    		var addGroupDaySelect = $("#addGroupDaySelect").find("option").remove().end();
    		for(var i = 0; i < days.length; i++){
    			addGroupDaySelect.append('<option value="' + days[i] + '">' + days[i] + '</option>').val(days[i]);
    		}
    	}
    	
    	function getMaxParentsRangesEnd(element){
    		maxHour = "00:00";
    		beforeMe = true;
    		$.each($(element).parent().parent().parent().find('input'), function(index, value){
    			if($(value)[0] === $(element)[0]){
    				return false;
    			}
    			if(value.name.indexOf('until') > 0 && beforeMe){
    				maxHour = value.value > maxHour ? value.value : maxHour;
    			}
    		});
     		return maxHour;
    	}
    	
    	$(document).ready(function(){
    		$.validator.addMethod("time24", function(value, element) {
        	    return value == "*" || /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/.test(value);
        	}, "");

    		$.validator.addMethod("lastRangeByDay", function(value, element) {
        	    return value == "*" || value >= getMaxParentsRangesEnd(element);
        	}, "");

    		$('.selectpicker').selectpicker();

	    	$(".addRange").live("click", function(e){
	    		e.preventDefault();
	    		var dayGroupId = this.id;
	    		var newNewRange = $("#newRange" + dayGroupId).clone();
	    		$("#newRange" + dayGroupId).show().attr("id", "");
	    		$(this).before(newNewRange);
	    	});
	    	
	    	$(".addDay").live("click", function(e){
	    		e.preventDefault();
	    		var groupId = this.id;
	    		var newDayId = $("#newDayId" + groupId).val();
	    		$("#newDayId" + groupId).val(parseInt(newDayId, 10) + 1);
	    		var newNewDay = $("#newDay" + groupId).clone();
	    		$("#newDay" + groupId).show();
	    		$("#newDay" + groupId).addClass("day" + groupId);
				$("#newDay" + groupId).attr("id", "day" + groupId + "D" + newDayId);
				$("#newFrom" + groupId).attr("id", "");
				$("#newUntil" + groupId).attr("id", "");
				$("#newDayTitle" + groupId).text(newDayId + " día" + (newDayId > 1 ? "s" : "") + " más");
				$("#newDayTitle" + groupId).attr("id", "");
				$("#newAddRange" + groupId).attr("id", groupId + "D" + newDayId);
				$("#newRange" + groupId).attr("id", "newRange" + groupId + "D" + newDayId);
	    		$(this).parent().before(newNewDay);
	    	});
	    	
	    	$('#addDayGroup').live("click",  function(e){
	    		e.preventDefault();
	    		nextId = $("select.weekDays").length - 1;
	    		var newNewDayGroup = $('#newDayGroup').clone(); 
	    		$('#newDayGroup').show();
	    		$('#newWeekDays').attr("name", "dayGroup" + nextId);
	    		$('#newWeekDays').attr("id", "weekDays" + nextId);
	    		
	    		$('#newGroupNewDayId').val("1");
	    		$('#newGroupNewDayId').attr("id", "newDayIdG" + nextId);
	    		$('#newDayGroup').attr("id", "G" + nextId);
	    		$('#newDayGroupAddDay').attr("id", "G" + nextId);
	    		$('#newDayGroupFirstDay').addClass("dayG" + nextId);
	    		$('#newDayGroupFirstDay').attr("id", "dayG" + nextId + "D0");
	    		$('#newDayGroupFirstRange').attr("id", "newRangeG" + nextId + "D0");
	    		$('#newDayGroupAddRange').attr("id", "G" + nextId + "D0");
	    		$('#newDayGroupNewDay').addClass("day" + nextId);
	    		$('#newDayGroupNewDay').attr("id", "newDayG" + nextId);
	    		$('#newDayGroupNewDayTitle').attr("id", "newDayTitleG" + nextId);
	    		$('#newDayGroupNewRange').attr("id", "newRangeG" + nextId);
	    		$('#newGroupDayNewDayFrom').attr("id", "newFromG" + nextId);
	    		$('#newGroupDayNewDayUntil').attr("id", "newUntilG" + nextId);
	    		$('#newDayGroupNewAddRange').attr("id", "newAddRangeG" + nextId);
	    		$(this).parent().before(newNewDayGroup);
	    	});
	    	
	    	$(".deleteRange").live("click", function(e){
	    		e.preventDefault();
	    		$(this).parent().parent().remove();
	    	});
	    	
	    	$(".deleteDay").live("click", function(e){
	    		e.preventDefault();
	    		groupId = $(this).parent().parent().attr("id");
	    		$(this).parent().remove();
	    		$("#newDayId" + groupId).val(parseInt($("#newDayId" + groupId).val(), 10) - 1);
	    	});
	    	
	    	$('#eventsForm').validate({
	    		errorPlacement: function(error, element) { },
	            submitHandler: function(form){
	 	    		reNameFull();
	 	   			setDaysMap();
	 	   			form.submit();
	 	   			parent.$.fancybox.close();
	            }
	        });
    	});
    	
    </script>
</head>
<body style="font-size:13px;">
	<div class="container-fluid">
	<fieldset>
		<legend>${action == 'edit' ? 'Editar' : 'Crear'}<span id="flashMessage"></span></legend>
		<form method="post" action="events" id="eventsForm">
			<input type="hidden" name="eventId" value="${event.getId()}" />
			<input type="hidden" name="action" value="${action}" />
			<div class="row">
				<div class="span2">
					<label>Operador</label>
					<select class="selectpicker span2" name="operador" title="Operador">
					<c:forEach var="operator" items="${operators}">
						<c:set var="opCountry" value="${operator.getPais().getCodigo().toUpperCase()}" />
			        	<c:set var="opName" value="${operator.name().split('_')[0].substring(0,1).toUpperCase().concat(operator.name().split('_')[0].substring(1).toLowerCase())}" />
			        	<c:if test="${opCountry == 'CO' && opName == 'Comcel'}">
			        		<c:set var="opCountry" value="CO" />
			        		<c:set var="opName" value="Claro" />
			        	</c:if>
			        	<c:if test="${opCountry == 'PA' && opName == 'Cableandwireless'}">
			        		<c:set var="opCountry" value="PA" />
			        		<c:set var="opName" value="C&W" />
			        	</c:if>
						<option value="${operator.name()}" ${event.getOperador().name() == operator.name() ? 'selected="selected"' : ''} >
						${opCountry} ${opName}
						</option>
					</c:forEach>
					</select>
				</div>
				<div class="span1">
					<label>Producto</label>
					<select class="selectpicker span1" name="producto" title="Producto">
					<c:forEach var="product" items="${products}">
						<option value="${product.name()}" ${event.getProducto() == product.name() ? 'selected="selected"' : ''} >${product.name()}</option>
					</c:forEach>
					</select>
				</div>
			
				<div class="span5">
					<label>ServicioPrecio</label>
					<input class="span5" type="text" value="${event.getSp() == '' ? 'Todos' : event.getSp()}" name="sp" style="font-size:13px;"/>
				</div>
			
				<div class="span2">
					<label>Tipo</label>
					<select class="selectpicker span2" name="tipo" title="Tipo">
					<c:forEach var="type" items="${types}" varStatus="index">
						<option value="${index.count}" ${event.getTipo() == type.name() ? 'selected="selected"' : ''} >${type.name()}</option>
					</c:forEach>
					</select>
				</div>
			
				<div class="span2">
					<label>Estado</label>
					<select class="selectpicker span2" name="estado" title="Estado">
						<option value="1" selected="selected">Activo</option>
						<option value="0">Inactivo</option>
					</select>
				</div>
			</div>
	
			<hr>
	
			<c:forEach var="days" items="${event.getDias()}" varStatus="dayGroupIndex">
			<div class="row dayGroup" id="G${dayGroupIndex.count - 1}" style="border-bottom:1px solid black; padding-bottom: 15px; margin-bottom: 15px;">
				<div class="span3">
					<div>
						<label>Días</label>
						<select class="selectpicker span3 weekDays" multiple name="dayGroup${dayGroupIndex.count - 1}" id="weekDays${dayGroupIndex.count - 1}" title="Seleccione días">
							<c:forEach var="weekDay" items="${weekDays}" >
							<option value="${weekDay}" <c:if test="${fn:contains(days.key, weekDay)}">selected="selected"</c:if>>${weekDay}</option>
							</c:forEach>
						</select>
					</div>
					<div style="border-right: 0px solid black;">
						<a class="btn" href="#"><i class="icon-trash"></i><br />Eliminar</a>
					</div>
				</div>
				<c:forEach var="hourRanges" items="${fn:split(days.value, '|')}" varStatus="daysIndex">
				<c:if test="${daysIndex.last}">
					<input type="hidden" id="newDayIdG${dayGroupIndex.count - 1}" value="${daysIndex.count}"/>
				</c:if>
				<div id="dayG${dayGroupIndex.count - 1}D${daysIndex.count - 1}" class="span3 text-center dayG${dayGroupIndex.count - 1}" style="border-right: 1px solid black;">
					<span class="dayTitle">
					<c:if test="${daysIndex.first}">
					Mismo Día
					</c:if>
					<c:if test="${!daysIndex.first}">
					${daysIndex.count - 1} día${daysIndex.count > 2 ? 's' : ''} más
					</c:if>
					</span>
					&nbsp;
					<div class="row" style="border: 0px solid yellow;">
						<div class="span1 text-center" style="border: 0px solid red;">
							Desde
						</div>
						<div class="span1 text-center" style="border: 0px solid red;">
							Hasta
						</div>
					</div>
					<c:forEach var="range" items="${fn:split(hourRanges, ',')}" varStatus="rangeIndex">
					<div class="row" style="border: 0px solid yellow;">
						<c:set var="rangeArray" value="${fn:split(range, '-')}" />
						<div class="span1 text-center control-group" style="border: 0px solid red;">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" value="${rangeArray[0].trim()}" name="rangeG${dayGroupIndex.count - 1}D${daysIndex.count - 1}R${rangeIndex.count - 1}.from" />
						</div>
						<div class="span1 text-center control-group" style="border: 0px solid red;">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" value="${rangeArray[1].trim()}" name="rangeG${dayGroupIndex.count - 1}D${daysIndex.count - 1}R${rangeIndex.count - 1}.until" />
						</div>
						<div class="btn-group text-left span1" style="padding-top:3px;">
				        	<a class="btn deleteRange" href="#"><i class="icon-trash icon-large"></i></a> 
				        </div>
					</div>
					</c:forEach>
					<div class="row" style="display:none;" id="newRangeG${dayGroupIndex.count - 1}D${daysIndex.count - 1}">
						<div class="span1 text-center control-group" style="border: 0px solid red;">
							<input class="span1 required time24 lastRangeByDay" size=6 type="text" placeholder="00:00" />
						</div>
						<div class="span1 text-center control-group" style="border: 0px solid red;">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" />
						</div>
						<div class="btn-group text-left span1" style="padding-top:3px;">
					       	<a class="btn deleteRange" href="#"><i class="icon-trash icon-large"></i></a> 
					    </div>
					</div>
					<a class="btn btn-small addRange" href="#" id="G${dayGroupIndex.count - 1}D${daysIndex.count - 1}"><i class="icon-plus"></i></a>
				</div>	
				</c:forEach>
				<div class="span3 text-center" style="border-right: 1px solid black; display:none;" id="newDayG${dayGroupIndex.count - 1}">
					<span class="dayTitle" id="newDayTitleG${dayGroupIndex.count - 1}"></span>
					&nbsp;
					<a class="btn deleteDay" href="#"><i class="icon-trash icon-large"></i></a>
					<div class="row" style="border: 0px solid yellow;">
						<div class="span1 text-center" style="border: 0px solid red;">
							Desde
						</div>
						<div class="span1 text-center" style="border: 0px solid red;">
							Hasta
						</div>
					</div>
					<div class="row" style="border: 0px solid yellow;" id="newRangeG${dayGroupIndex.count - 1}">
						<div class="span1 text-center control-group" style="border: 0px solid red;">
							<input class="span1 required time24 lastRangeByDay" size=6 type="text" placeholder="00:00" id="newFromG${dayGroupIndex.count - 1}" />
						</div>
						<div class="span1 text-center control-group" style="border: 0px solid red;">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" id="newUntilG${dayGroupIndex.count - 1}" />
						</div>
						<div class="btn-group text-left span1" style="padding-top:3px;">
					       	<a class="btn deleteRange" href="#" id="newDeleteRange"><i class="icon-trash icon-large"></i></a> 
					    </div>
					</div>
					<a class="btn btn-small addRange" href="#" id="newAddRangeG${dayGroupIndex.count - 1}"><i class="icon-plus"></i></a>
				</div>
				<div class="span2 text-center" style="margin-left: 14px; padding-right: 15px;">
					<a class="btn btn-large addDay" href="#" id="G${dayGroupIndex.count - 1}"><i class="icon-plus"></i><br />Extender Jornada</a>
				</div>
			</div>
			</c:forEach>
			<div class="row dayGroup" id="newDayGroup" style="display:none; border-bottom:1px solid black; padding-bottom: 15px; margin-bottom: 15px;">
				<div class="span3">
					<div>
						<label>Días</label>
						<select class="selectpicker span3 weekDays" multiple name="newDayGroup" id="newWeekDays" title="Seleccione Días">
							<c:forEach var="weekDay" items="${weekDays}" >
							<option value="${weekDay}">${weekDay}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<a class="btn" href="#"><i class="icon-trash"></i><br />Eliminar</a>
					</div>
				</div>
				<input type="hidden" id="newGroupNewDayId" />
				<div id="newDayGroupFirstDay" class="span3 text-center" style="border-right: 1px solid black;">
					<span class="dayTitle">Mismo Día</span>
					<div class="row">
						<div class="span1 text-center">
							Desde
						</div>
						<div class="span1 text-center">
							Hasta
						</div>
					</div>
					<div class="row">
						<div class="span1 text-center control-group">
							<input class="span1 required time24 lastRangeByDay" size=6 type="text" placeholder="00:00" />
						</div>
						<div class="span1 text-center control-group">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" />
						</div>
						<div class="btn-group text-left span1" style="padding-top:3px;">
				        	<a class="btn deleteRange" href="#"><i class="icon-trash icon-large"></i></a> 
				        </div>
					</div>
					<div class="row" style="display:none;" id="newDayGroupFirstRange">
						<div class="span1 text-center control-group">
							<input class="span1 required time24 lastRangeByDay" size=6 type="text" placeholder="00:00" id="newGroupDayFrom" />
						</div>
						<div class="span1 text-center control-group">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" id="newGroupDayUntil" />
						</div>
						<div class="btn-group text-left span1" style="padding-top:3px;">
					       	<a class="btn deleteRange" href="#" id="newDayGroupDeleteRange"><i class="icon-trash icon-large"></i></a> 
					    </div>
					</div>
					<a class="btn btn-small addRange" href="#" id="newDayGroupAddRange"><i class="icon-plus"></i></a>
				</div>
				<div class="span3 text-center" style="border-right: 1px solid black; display:none;" id="newDayGroupNewDay">
					<span class="dayTitle" id="newDayGroupNewDayTitle"></span>
					<div class="row">
						<div class="span1 text-center">
							Desde
						</div>
						<div class="span1 text-center">
							Hasta
						</div>
					</div>
					<div class="row" id="newDayGroupNewRange">
						<div class="span1 text-center control-group">
							<input class="span1 required time24 lastRangeByDay" size=6 type="text" placeholder="00:00" id="newGroupDayNewDayFrom" />
						</div>
						<div class="span1 text-center control-group">
							<input class="span1 required time24" size=6 type="text" placeholder="00:00" id="newGroupDayNewDayUntil" />
						</div>
						<div class="btn-group text-left span1" style="padding-top:3px;">
					       	<a class="btn deleteRange" href="#" id="newDayGroupNewDeleteRange"><i class="icon-trash icon-large"></i></a> 
					    </div>
					</div>
					<a class="btn btn-small addRange" href="#" id="newDayGroupNewAddRange"><i class="icon-plus"></i></a>
				</div>
				<div class="span2 text-center" style="margin-left: 14px; padding-right: 15px;">
					<a class="btn btn-large addDay" href="#" id="newDayGroupAddDay"><i class="icon-plus"></i><br />Extender Jornada</a>
				</div>
			</div>
			<div>
<!-- 				<select id="addGroupDaySelect" class="selectpicker span2" name="operador" title="Agregar días"> -->
<%-- 					<c:forEach var="weekDay" items="${weekDays}" > --%>
<%-- 						<option value="${weekDay}" selected="selected">${weekDay}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
				<a class="btn btn-large" id="addDayGroup" href="#"><i class="icon-plus"></i><br />Agregar días</a>
			</div>
			<div class="form-actions">
				<button class="btn btn-primary" type="submit">Guardar</button>
				<button class="btn btn-danger" onclick="parent.$.fancybox.close()" type="button">Cancelar</button>
			</div>
		</form>
	</fieldset>
	</div>
</body>
</html>