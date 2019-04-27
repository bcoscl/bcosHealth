<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear Configuraciones</title>

<script src="./js/crearConfiguraciones.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">

<%@include file="../includes/body.jsp" %>




<!-- contenido -->



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Configuraciones</strong></div>
        <div class="card-body">
            <form id="formCrearConfiguraciones" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Grupo</label>
                    <div class="col-md-9">
                        <input class="form-control" id="grupo" type="text" name="text-input" placeholder="Agrupador" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">subGrupo</label>
                    <div class="col-md-9">
                        <input class="form-control" id="subgrupo" type="text" name="text-input" placeholder="Sub Agrupador" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="email-input">Param1</label>
                    <div class="col-md-9">
                        <input class="form-control" id="param1" type="email" name="email-input" placeholder="parametro 1" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Param2</label>
                    <div class="col-md-9">
                        <input class="form-control" id="param2" value ="" type="text" name="text-input" placeholder="parametro 1">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Param3</label>
                    <div class="col-md-9">
                        <input class="form-control" id="param3" value ="" type="text" name="text-input" placeholder="parametro 2">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Param4</label>
                    <div class="col-md-9">
                        <input class="form-control" id="param4" value ="" type="text" name="text-input" placeholder="parametro 4">
                    </div>
                </div>


            </form>

        </div>
        <div class="card-footer">
            <button id="submitButton" class="btn btn-sm btn-primary" type="button">
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
</div>

<!-- foofter -->
<%@include file="../includes/footer.jsp" %>