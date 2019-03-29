/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

   $("#submitButton").click(function(e) { 
	$.ajax({						 
                url: "http://localhost:8080/BcosHealth/ServletSucursales",						
                dataType: "text",
                data: {sucursalName : $("#sucursalName").val(),
                       comunaName: $("#comunaName").val() },
                //beforeSend: function () {
                //        $("#navData").html("<div  class=\"container centered loader\"></div>");	

                //},
                success: function(data){ 
                    alert('Insert OK');    
                    //$("#navData").html(data);


                },
                error: function(jqXHR, textStatus, errorThrown) {
                                        var mensaje;
                                          if (jqXHR.status == 500) {
                                                  // Server side error
                                                  mensaje = " Error server side - status : "+jqXHR.status;
                                                } else if (jqXHR.status == 404) {
                                                  mensaje = " Sitio not found - status : "+jqXHR.status;
                                                } else {
                                                        mensaje = " - status : "+jqXHR.status;												
                                                }	
                                       // $("#navData").html("<div  class=\"container\"><div class=\"alert alert-warning\"><strong>Warning!</strong> Ups ! no pudimos obtener la informacion, intentalo mas nuevamente mas tarde.."+mensaje+"</div></div>");

                }
        });

   });
   
	$(".actualizar").click(function(e) { 
        
		var codigo 		= $(this).parent().parent().find("td:eq(0)").text();
		var texto 		= $(this).parent().parent().find("td:eq(1)").text();
		var traduccion 	= $(this).parent().parent().find("td:eq(2)").text();
		var referencia 	= $(this).parent().parent().find("td:eq(3)").text();
		
		// 1 idioma
		// 2 codigo
		// 3 referencia
		// #original
		$("#1").html("Ingles");
		$("#2").html(codigo);
		$("#3").html(referencia);
		$("#original").val(texto);
		
		
    });
	

	$(".Export").click(function(e) {
        window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#tableData').html()));
        
		//window.open('data:application/pdf;base64,' + Base64.encode(buffer) + encodeURIComponent($('#tableData').html()));
        e.preventDefault();
    });

   $('#entradafilter').keyup(function () {
      var rex = new RegExp($(this).val(), 'i');
        $('.contenidobusqueda tr').hide();
        $('.contenidobusqueda tr').filter(function () {
            return rex.test($(this).text());
        }).show();

        })


$('th').click(function() {
    var table = $(this).parents('table').eq(0)
    var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
    this.asc = !this.asc
    if (!this.asc) {
      rows = rows.reverse()
    }
    for (var i = 0; i < rows.length; i++) {
      table.append(rows[i])
    }
    setIcon($(this), this.asc);
  })

  function comparer(index) {
    return function(a, b) {
      var valA = getCellValue(a, index),
        valB = getCellValue(b, index)
      return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB)
    }
  }

  function getCellValue(row, index) {
    return $(row).children('td').eq(index).html()
  }

  function setIcon(element, asc) {
    $("th").each(function(index) {
      $(this).removeClass("sorting");
      $(this).removeClass("asc");
      $(this).removeClass("desc");
    });
    element.addClass("sorting");
    if (asc) element.addClass("asc");
    else element.addClass("desc");
  }

  
    $("#succesAlert").hide();
            $("#success-alert").click(function showAlert() {
               $("#succesAlert").show();
                window.setTimeout(function () { 
                            $("#succesAlert").alert('close'); }, 2000);               
                  }); 
  
  
});

 function showIdioma(val){
		

		$.ajax({
						 
							url: "http://localhost:8080/SPR/SED/MantenedorIdiomas",						
							dataType: "html",
							data: {token : val,
								   opcion : "tableData"},
							 beforeSend: function () {
								$("#tableData").html("<div  class=\"container centered loader\"></div>");	
													
							},
							success: function(data){ 
								$("#tableData").html(data);
									var rex = new RegExp(val, 'i');
										 
											$('.contenidobusqueda tr').hide();
											 // $('.contenidobusqueda tr .'+val).show();
											 $('.contenidobusqueda tr').filter(function (val) {
												 return rex.test($(this).attr("class"));		   
											}).show();								
												
							},
							error: function(jqXHR, textStatus, errorThrown) {
										var mensaje;
										  if (jqXHR.status == 500) {
											  // Server side error
											  mensaje = " Error server side - status : "+jqXHR.status;
											} else if (jqXHR.status == 404) {
											  mensaje = " Sitio not found - status : "+jqXHR.status;
											} else {
												mensaje = " - status : "+jqXHR.status;												
											}	
										$("#tableData").html("<div  class=\"container\"><div class=\"alert alert-warning\"><strong>Warning!</strong> Ups ! no pudimos obtener la informacion, intentalo mas nuevamente mas tarde.."+mensaje+"</div></div>");
								
							}
		});
 
	


      
                   

  }
  


