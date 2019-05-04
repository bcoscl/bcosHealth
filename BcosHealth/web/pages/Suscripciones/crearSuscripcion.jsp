<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear Suscripciones</title>

<script src="./js/crearSuscripciones.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">

<%@include file="../includes/body.jsp" %>




<!-- contenido -->



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Suscripciones</strong></div>
        <div class="card-body">
            <form id="formCrearSuscripciones" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Empresa Suscriptora</label>
                    <div class="col-md-9">
                        <input class="form-control" id="nombre_empresa" type="text" name="text-input" placeholder="Nombre de Empresa" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Contacto Empresa</label>
                    <div class="col-md-9">
                        <input class="form-control" id="contacto_empresa" type="text" name="text-input" placeholder="Contacto de Empresa" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="email-input">Email Contacto</label>
                    <div class="col-md-9">
                        <input class="form-control" id="email_contacto" type="email" name="email-input" placeholder="email de contacto" autocomplete="email" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Télefono de Contacto</label>
                    <div class="col-md-9">
                        <input class="form-control" id="numero_telefono" value ="+569" type="text" name="text-input" placeholder="numero de Telefono +569" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="date-input">Fecha de Inicio</label>
                    <div class="col-md-9">
                        <input class="form-control" id="fecha_inicio" type="date" name="date-input" placeholder="Fecha" required>					
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Plan</label>
                    <div class="col-md-9">
                        <select class="form-control" id="select_plan" name="select1" required>

                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Estado</label>
                    <div class="col-md-9">
                        <label class="switch switch-label switch-pill switch-success">
                            <input  id="checkbox_activo" class="switch-input" type="checkbox" required>
                            <span id="checkcontroller" class="switch-slider" data-checked="on" data-unchecked="off"></span>
                        </label>
                    </div>
                </div>


            </form>
        </div>
        <div class="card-footer">
            <button id="submitButton" class="btn btn-sm btn-primary" type="submit">
                <i class="fa fa-dot-circle-o"></i> Guardar</button>
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

<!--
<div id="msgResult" class="alert alert-success alert-dismissible fade show-none" role="alert">
    <strong>Perfect!</strong> Suscripcion Registrado exitosamente
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
</div>-->
<%@include file="../includes/message/message.jsp" %>
<!-- foofter -->
<%@include file="../includes/footer.jsp" %>