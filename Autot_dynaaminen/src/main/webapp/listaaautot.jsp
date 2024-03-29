<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Listaa autot</title>

</head>
<body>
<table id="listaus">
	<thead>
		<tr>
		<th colspan="5" class="oikealle"><span id="uusiAuto">Lis�� uusi auto</span></th>
		</tr>
		<tr>
			<th class="oikealle">Hakusana:</th>
			<th colspan="2"><input type="text" id="hakusana"></th>
			<th colspan ="2"><input type="button" value="Hae" id="hakunappi"></th>
		</tr>	
		<tr>
			<th>Rekisterinumero</th>
			<th>Merkki</th>
			<th>Malli</th>
			<th>Vuosi</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script>
$(document).ready(function(){
	
	$("#uusiAuto").click(function(){
		document.location="lisaaauto.jsp";
	})
	
	haeAutot();
	$("#hakunappi").click(function(){		
		haeAutot();
	});
	$(document.body).on("keydown", function(event){
		  if(event.which==13){ //Enteri� painettu, ajetaan haku
			  haeAutot();
		  }
	});
	$("#hakusana").focus();//vied��n kursori hakusana-kentt��n sivun latauksen yhteydess�
});	

function haeAutot(){
	$("#listaus tbody").empty();
	$.ajax({url:"autot/"+$("#hakusana").val(), type:"GET", dataType:"json", success:function(result){//Funktio palauttaa tiedot json-objektina		
		$.each(result.autot, function(i, field){  
        	var htmlStr;
        	htmlStr+="<tr id='rivi_"+field.rekno+"'>";
        	htmlStr+="<td>"+field.rekno+"</td>";
        	htmlStr+="<td>"+field.merkki+"</td>";
        	htmlStr+="<td>"+field.malli+"</td>";
        	htmlStr+="<td>"+field.vuosi+"</td>";
        	htmlStr+="<td><a href='muutaauto.jsp?rekno="+field.rekno+"'>Muuta</a>&nbsp";
        	htmlStr+="<span class='poista' onclick=poista('"+field.rekno+"')>Poista</span></td>";
        	htmlStr+="</tr>";
        	$("#listaus tbody").append(htmlStr);
        });	
    }});
}
function poista(rekno){
	if(confirm("Poista auto " + rekno + "?")){
		$.ajax({url:"autot/"+rekno, type:"DELETE", dataType:"json", success:function(result){//Funktio palauttaa tiedot json-objektina
			if(result.response==0){
				alert("Auton " + rekno + " poisto ep�onnistui.");
			}else if(result.response==1){
				$("#rivi_"+rekno).css("background-color", "red");
				alert("Auton " + rekno + " poisto onnistui.");
				haeAutot();
			}
						
		}});
	}	
}
</script>
</body>
</html>