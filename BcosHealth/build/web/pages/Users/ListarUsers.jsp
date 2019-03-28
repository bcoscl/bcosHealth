<!DOCTYPE html>


<html lang="en">
  <head>
    <base href="./">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    
    
    
    <title>Bcos Health</title>
    <!-- Icons-->
    <link href="./css/coreui-icons.min.css" rel="stylesheet">
    <link href="./css/flag-icon.min.css" rel="stylesheet">
    <link href="./css/font-awesome.min.css" rel="stylesheet">
    <link href="./css/simple-line-icons.css" rel="stylesheet">
    <!-- Main styles for this application-->
    <link href="css/style.css" rel="stylesheet">
    <link href="./css/pace.min.css" rel="stylesheet">

	
    <!-- CoreUI and necessary plugins-->

    <script src="./js/jquery.min.js"></script>
    <script src="./js/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/pace.min.js"></script>
    <script src="./js/perfect-scrollbar.min.js"></script>
    <script src="./js/coreui.min.js"></script>
    <!-- Plugins and scripts required by this view-->
    <script src="./js/Chart.min.js"></script>
    <script src="./js/custom-tooltips.min.js"></script>
    <script src="./js/main.js"></script>
	<script>
	$(document).ready(function () {

   
	$.ajax({						 
			url: "http://localhost:8080/SPR/SED/MantenedorIdiomas",						
			dataType: "text",
			data: {opcion : "navData"},
			beforeSend: function () {
				$("#navData").html("<div  class=\"container centered loader\"></div>");	
									
			},
			success: function(data){ 
				$("#navData").html(data);
				
  				
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
						$("#navData").html("<div  class=\"container\"><div class=\"alert alert-warning\"><strong>Warning!</strong> Ups ! no pudimos obtener la informacion, intentalo mas nuevamente mas tarde.."+mensaje+"</div></div>");
				
			}
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
</script>
	
	
  </head>
  <body class="app header-fixed sidebar-fixed aside-menu-fixed sidebar-lg-show">
    <header class="app-header navbar">
      
	  <button class="navbar-toggler sidebar-toggler d-lg-none mr-auto" type="button" data-toggle="sidebar-show">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a class="navbar-brand" href="#">
        <img class="navbar-brand-full" src="img/brand/logo.png" width="89" height="40" alt=" Logo full">
        <img class="navbar-brand-minimized" src="img/brand/logo.png" width="30" height="30" alt=" Logo minimized">
      </a>
      <button class="navbar-toggler sidebar-toggler d-md-down-none" type="button" data-toggle="sidebar-lg-show">
        <span class="navbar-toggler-icon"></span>
      </button>
	  
	  
	  <!-- opciones superiores -->
      <ul class="nav navbar-nav d-md-down-none">
        <li class="nav-item px-3">
          <a class="nav-link" href="#">Suscripciones</a>
        </li>
		<li class="nav-item px-3">
          <a class="nav-link" href="#">Fichas</a>
        </li>
		<li class="nav-item px-3">
          <a class="nav-link" href="#">Pacientes</a>
        </li>        
      </ul>
	  
	  
	  <!-- bara superior derecha -->
      <ul class="nav navbar-nav ml-auto">
			<li class="breadcrumb-item d-md-down-none">Dr. Cristobal - Kinesiologo</li>          
			<li class="nav-item dropdown">		
				  <a class="nav-link" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
					<img class="img-avatar" src="img/avatars/6.jpg" alt="admin@bootstrapmaster.com">
				  </a>
				<div class="dropdown-menu dropdown-menu-right">
				<div class="dropdown-header text-center">
				  <strong>Account</strong>
				</div>
					<a class="dropdown-item" href="#">
					  <i class="fa fa-user"></i> Profile</a>
					<a class="dropdown-item" href="#">
					  <i class="fa fa-lock"></i> Logout</a>
				</div>
			</li>
      </ul>
     
    </header>
    <div class="app-body">
	<!-- menu Lateral -->
      <div class="sidebar">
        <nav class="sidebar-nav">
          <ul class="nav">
            <li class="nav-item">
              <a class="nav-link" href="index.html">
                <i class="nav-icon icon-home"></i> Home
                
              </a>
            </li>
            <li class="nav-title">Administracion</li>
            <li class="nav-item nav-dropdown">			
              <a class="nav-link nav-dropdown-toggle" href="#">
                <i class="nav-icon icon-settings"></i> Settings</a>
				<ul class="nav-dropdown-items">
					<li class="nav-item nav-dropdown">		
					  <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
						<i class="nav-icon icon-tag"></i>Suscripciones</a>
						
						<ul class="nav-dropdown-items">
							<li class="nav-item ng-scope" >
								<a class="nav-link" href="./ListarSuscripciones.html">
								<i class="nav-icon icon-list"></i>Listar Suscripciones
								</a>
							</li>
							<li class="nav-item ng-scope">
								<a class="nav-link" href="./crearSuscripciones.html">
								<i class="nav-icon icon-plus"></i>Crear Suscripciones
								</a>
							</li>
							
						
						</ul>
						
						
					</li>
					
					<li class="nav-item nav-dropdown">		
					  <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
						<i class="nav-icon icon-credit-card"></i>Planes</a>
						
						<ul class="nav-dropdown-items">
							<li class="nav-item ng-scope" >
								<a class="nav-link" href="./ListarPlanes.html">
								<i class="nav-icon icon-list"></i>Listar Planes
								</a>
							</li>
							<li class="nav-item ng-scope">
								<a class="nav-link" href="./crearPlanes.html">
								<i class="nav-icon icon-plus"></i>Crear Planes
								</a>
							</li>
							
						
						</ul>
						
						
					</li>
                
				</ul>
            </li>
			
            <li class="nav-item nav-dropdown">              
              <a class="nav-link nav-dropdown-toggle" href="#">
                <i class="nav-icon icon-people"></i> Users</a>
				
				<ul class="nav-dropdown-items">
							<li class="nav-item">
								<a class="nav-link" href="./ListarUsers.html">
								<i class="nav-icon icon-list"></i>Listar Users
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="./crearUsers">
								<i class="nav-icon icon-plus"></i>Crear Users
								</a>
							</li>
							
						
						</ul>
				
            </li>
			<li class="nav-item nav-dropdown">	
					   <a class="nav-link nav-dropdown-toggle" href="#">
						<i class="nav-icon icon-location-pin"></i>Sucursales</a>
						<ul class="nav-dropdown-items">
							<li class="nav-item">
								<a class="nav-link" href="./listarSucursales.html">
								<i class="nav-icon icon-list"></i>Listar Sucursales
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="./crearSucursales">
								<i class="nav-icon icon-plus"></i>Crear Sucursales
								</a>
							</li>
							
						
						</ul>
						
					</li>
			
            <li class="nav-title">Modulos</li>
			
            <li class="nav-item nav-dropdown">
              <a class="nav-link nav-dropdown-toggle" href="#">
                <i class="nav-icon icon-note"></i> Fichas</a>
				<ul class="nav-dropdown-items">
					<li class="nav-item">
						<a class="nav-link" href="./listarFichas.html">
						<i class="nav-icon icon-list"></i>Listar Fichas
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./crearFichas.html">
						<i class="nav-icon icon-plus"></i>Crear Fichas
						</a>
					</li>
					
                
				</ul>
				
              
            </li>
            <li class="nav-item nav-dropdown">
              <a class="nav-link nav-dropdown-toggle" href="#">
                <i class="nav-icon icon-paper-clip"></i> Examenes</a>
				  <ul class="nav-dropdown-items">
						<li class="nav-item">
							<a class="nav-link" href="./listarExamenes.html">
							<i class="nav-icon icon-list"></i>Listar Examenes
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="./crearExamenes.html">
							<i class="nav-icon icon-plus"></i>Crear Examenes
							</a>
						</li>
						
					
				  </ul>
			  
            </li>
            <li class="nav-item nav-dropdown">  
              <a class="nav-link nav-dropdown-toggle" href="#">
                <i class="nav-icon icon-emotsmile"></i> Pacientes</a>
				
				<ul class="nav-dropdown-items">
					<li class="nav-item">
						<a class="nav-link" href="./listarPacientes.html">
						<i class="nav-icon icon-list"></i>Listar Pacientes
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./crearPacientes.html">
						<i class="nav-icon icon-plus"></i>Crear Pacientes
						</a>
					</li>
					
                
              </ul>		
				
              
            </li>
           
          </ul>
        </nav>
        <button class="sidebar-minimizer brand-minimizer" type="button"></button>
      </div>
      <main class="main">
        <!-- Breadcrumb-->
        <ol class="breadcrumb d-md-none">
          <li class="breadcrumb-item">Dr. Cristobal - Kinesiologo</li>  
        </ol>
		<ol class="breadcrumb"></ol>
		
        <div class="container-fluid"  >
        
		<!-- contenido -->
		
			
		
			<div id="ui-view"><div><link href="./css/dataTables.bootstrap4.css" rel="stylesheet">
			
			<span >	Listar Users</span>
			
			<div class="animated fadeIn">
			<div class="card">

			<div class="card-body">
			<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
			<div class="row"><div class="col-sm-12 col-md-6">
			<div class="dataTables_length" id="DataTables_Table_0_length">
			<!--<label>Show <select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="custom-select custom-select-sm form-control form-control-sm">
			<option value="10">10</option><option value="25">25</option>
			<option value="50">50</option><option value="100">100</option>
			</select> entries</label>-->
			</div>
			</div>
			<div class="col-sm-12 col-md-6">
			<div id="DataTables_Table_0_filter" class="dataTables_filter">
			<label>Search:<input type="search" id="entradafilter" class="form-control form-control-sm" placeholder="" aria-controls="DataTables_Table_0">
			</label>
			</div>
			</div>
			</div>
			<div class="row">
			<div class="col-sm-12">
			<table class="table table-striped table-bordered datatable dataTable no-footer" id="DataTables_Table_0" role="grid" aria-describedby="DataTables_Table_0_info" style="border-collapse: collapse !important">
			<thead>
			<tr role="row">
				<th class="sorting_asc" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Username: activate to sort column descending" style="width: 20%;">Rut</th>
				<th class="sorting_asc" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Username: activate to sort column descending" style="width: 20%;">Nombre</th>
				<th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 10%;">Apellido</th>
				<th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 10%;">Especialidad</th>
				<th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 10%;">Mail</th>
				
				<th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 15%;">Telefono</th>
				<th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Role: activate to sort column ascending" style="width: 10%;">Fecha Creacion</th>
				<th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Role: activate to sort column ascending" style="width: 10%;">Creado Por</th>
				<th class="sorting " tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending" style="width: 10%;">Estado</th>
				<th class="sorting" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Actions: activate to sort column ascending" style="width: 5%;">Acciones</th>
			</tr>
			</thead>
			<tbody class="contenidobusqueda">
			<tr role="row" class="odd">
			<td class="sorting_1 ">8765435</td>
			<td class="sorting_1 ">Adam Alister</td>
			<td class="d-none d-sm-table-cell">Alister</td>
			<td class="d-none d-sm-table-cell">Kinesiologo</td>
			<td class="d-none d-sm-table-cell">email</td>
			<td class="d-none d-sm-table-cell">+56963344469</td>
			
			<td class="d-none d-sm-table-cell">2012/01/21</td>
			<td class="d-none d-sm-table-cell">Alexis Cantero</td>
			
			<td >
				
					<label class="switch switch-label switch-pill switch-success">
					<input class="switch-input" type="checkbox" checked="">
					<span class="switch-slider" data-checked="on" data-unchecked="off"></span>
					</label>
			
			</td>
			<td>
			<!--<a class="btn btn-success" href="#">
			<i class="fa fa-search-plus"></i>
			</a>-->
			<a class="btn btn-info" href="#">
			<i class="fa fa-edit"></i>
			</a>
			<!--<a class="btn btn-danger" href="#">
			<i class="fa fa-trash-o"></i>
			</a>-->
			</td>
			</tr>
			<tr role="row" class="odd">
			<td class="sorting_1 ">78219430</td>
			<td class="sorting_1 ">Luis Sebastian</td>
			<td class="d-none d-sm-table-cell">Farias</td>
			<td class="d-none d-sm-table-cell">Secretaria</td>
			<td class="d-none d-sm-table-cell">numero Contacto2</td>
			
			<td class="d-none d-sm-table-cell">+569444678</td>
			<td class="d-none d-sm-table-cell">2012/01/21</td>
			<td class="d-none d-sm-table-cell">Cristobal</td>
			<td >
					<label class="switch switch-label switch-pill switch-success">
					<input class="switch-input" type="checkbox" checked="">
					<span class="switch-slider" data-checked="on" data-unchecked="off"></span>
					</label>
			</td>
			<td>
			<!--<a class="btn btn-success" href="#">
			<i class="fa fa-search-plus"></i>
			</a>-->
			<a class="btn btn-info" href="#">
			<i class="fa fa-edit"></i>
			</a>
			<!--<a class="btn btn-danger" href="#">
			<i class="fa fa-trash-o"></i>
			</a>-->
			</td>
			</tr>
			</tbody>
			</table>
			</div></div>
			<div class="row"><div class="col-sm-12 col-md-5">
			<div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">Showing 1 to 10 of 32 entries</div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate"><ul class="pagination"><li class="paginate_button page-item previous disabled" id="DataTables_Table_0_previous"><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li><li class="paginate_button page-item active"><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0" class="page-link">1</a></li><li class="paginate_button page-item "><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" class="page-link">2</a></li><li class="paginate_button page-item "><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="3" tabindex="0" class="page-link">3</a></li><li class="paginate_button page-item "><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="4" tabindex="0" class="page-link">4</a></li><li class="paginate_button page-item next" id="DataTables_Table_0_next"><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="5" tabindex="0" class="page-link">Next</a></li></ul></div></div></div></div>
			</div>
			</div>
			</div></div></div>
			</div>
		
		
		
		<!-- fin contenido -->		  
		  
        </div>
      </main>    
    </div>
	
	<!-- foofter -->
    <footer class="app-footer">
      <div>
        <a href="https://www.bcos.cl">https://www.Bcos.cl</a>
        <span>&copy; 2019 BcosLabs.</span>
      </div>
      <div class="ml-auto">
       
      </div>
    </footer>
	
	
	
  </body>
</html>
