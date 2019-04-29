<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear Sucursales</title>

<script src="./js/crearExamenes.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">

<%@include file="../includes/body.jsp" %>




<!-- contenido -->



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Exámenes</strong></div>
        <div class="card-body">
            <form id="formCrearExamen" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Nombre Examen</label>
                    <div class="col-md-9">
                        <input class="form-control" id="nombre_examen" type="text" name="text-input" placeholder="Nombre del examen" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Observacion</label>
                    <div class="col-md-9">
                        <input class="form-control" id="obs_examen" type="text" name="text-input" placeholder="observacion del examen" required>
                    </div>
                </div>



                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Paciente</label>
                    <div class="col-md-9">
                        <select class="form-control" data-live-search="true" id="select_Pacientes" required>

                        </select>
                    </div>
                    
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Adjunto</label>
                    <div class="col-md-3">

                        <a href="javascript:void(0)"  id="fileuploadExamen" class="btn btn-sm btn-block btn-success">
                            <i class="ace-icon fa fa-cloud-upload bigger-120"></i>
                            <span class="bigger-110">Subir Examen</span>
                        </a>
                        <input id="upload-file-examen" type="file" style="display: none;"/>

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
</div>-->
<%@include file="../includes/message/message.jsp" %>
<!-- foofter -->
<%@include file="../includes/footer.jsp" %>