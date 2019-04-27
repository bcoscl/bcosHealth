<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear Sucursales</title>

<script src="./js/crearSucursales.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">

<%@include file="../includes/body.jsp" %>




<!-- contenido -->



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Sucursales</strong></div>
        <div class="card-body">
            <form id="formCrearSuscursales" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Nombre Sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="nombre_sucursal" type="text" name="text-input" placeholder="Nombre de la sucursal" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Comuna Sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="comuna_sucursal" type="text" name="text-input" placeholder="Comuna de la sucursal" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="email-input">Telefono Sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="numero_telefono" value ="" name="email-input" placeholder="Telefono de contacto +569"  required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Correo Sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="correo_sucursal"  type="email"  name="text-input" placeholder="sucursal@suc.cl" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="date-input">Contacto Sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="contacto_sucursal" type="text"  placeholder="Nombre de Contacto sucursal" required>					
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Empresa</label>
                    <div class="col-md-9">
                        <select class="form-control" id="select_empresa" name="select1" required>

                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Estado</label>
                    <div class="col-md-9">
                        <label class="switch switch-label switch-pill switch-success">
                            <input  id="checkbox_activo" class="switch-input" type="checkbox" >
                            <span id="checkcontroller" class="switch-slider" data-checked="on" data-unchecked="off"></span>
                        </label>
                    </div>
                </div>


            </form>
        </div>
        <div class="card-footer">
            <button id="submitButton" class="btn btn-sm btn-primary" type="submit">
                <i class="fa fa-dot-circle-o"></i> Submit</button>
            <!--<button class="btn btn-sm btn-danger" type="reset">
            <i class="fa fa-ban"></i> Reset</button>-->
        </div>
    </div>

</div>



<!-- fin contenido -->		  
<!--
</div>
</main>    
</div>-->


<div id="msgResult" class="alert alert-success alert-dismissible fade show-none" role="alert">
    <strong>Perfect!</strong> Registrado exitosamente
    <button class="close" type="button" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>

<div id="msgResultError" class="alert alert-danger alert-dismissible fade show-none" role="alert">
    <strong>Ups!</strong> Se produjo un error al realizar la solicitud, por favor intentalo de nuevo
    <button class="close" type="button" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>



<div id="load" class="sk-fading-circle" style="display: none;">
    <div class="sk-circle1 sk-circle"></div>
    <div class="sk-circle2 sk-circle"></div>
    <div class="sk-circle3 sk-circle"></div>
    <div class="sk-circle4 sk-circle"></div>
    <div class="sk-circle5 sk-circle"></div>
    <div class="sk-circle6 sk-circle"></div>
    <div class="sk-circle7 sk-circle"></div>
    <div class="sk-circle8 sk-circle"></div>
    <div class="sk-circle9 sk-circle"></div>
    <div class="sk-circle10 sk-circle"></div>
    <div class="sk-circle11 sk-circle"></div>
    <div class="sk-circle12 sk-circle"></div>
</div>

<!-- foofter -->
<%@include file="../includes/footer.jsp" %>