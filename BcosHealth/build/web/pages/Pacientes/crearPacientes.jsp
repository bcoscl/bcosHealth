<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear Paciente</title>

<script src="./js/crearPaciente.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">



<%@include file="../includes/body.jsp" %>


<!-- contenido -->



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Paciente</strong></div>
        <div class="card-body">





            <form id="crearUsers" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Rut  (sin digito verificador)</label>
                    <div class="col-md-9">
                        <input class="form-control" id="numuser_paciente" type="text" name="text-input" placeholder="Rut  (sin digito verificador)">
                        <!--<label id="user_registrado" class="alert alert-danger alert-dismissible fade show-none" style="display: none;"></label>-->
                        <div id="user_registrado" class="alert alert-dismissible fade show-none" style="display: none;" role="alert">
                            <label id="mensaje"></label>
                            <button class="close" type="button" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>

                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Nombres</label>
                    <div class="col-md-4">
                        <input class="form-control" id="nombre_paciente" type="text" name="text-input" placeholder="Nombres">
                    </div>
                    <label class="col-md-1 col-form-label" for="text-input">Apellidos</label>
                    <div class="col-md-4">
                        <input class="form-control" id="apellido_paciente" type="text" name="text-input" placeholder="Apellidos">
                    </div>
                </div>
                <div class="form-group row">

                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="direccion-input">Direccion</label>
                    <div class="col-md-9">
                        <input class="form-control" id="direccion_paciente" type="map" name="direccion-input" placeholder="Direccion" autocomplete="direccion">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="email-input">Email Contacto</label>
                    <div class="col-md-9">
                        <input class="form-control" id="email_contacto_paciente" type="email" name="email-input" placeholder="email de contacto" autocomplete="email">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Telefono de Contacto</label>
                    <div class="col-md-9">
                        <input class="form-control" id="numero_telefono_paciente" value ="+569" type="text" name="text-input" placeholder="numero de Telefono +569">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Profesión</label>
                    <div class="col-md-9">
                        <input class="form-control" id="profesion_paciente" value ="" type="text" name="text-input" placeholder="profesion">
                    </div>
                </div>



                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Estado Civil</label>
                    <div class="col-md-9">
                        <input class="form-control" id="estado_civil_paciente" value ="" type="text" name="text-input" placeholder="Soltero(a), Casado(a), Viudo(a)">
                    </div>
                </div>



                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Fecha Nacimiento</label>
                    <div class="col-md-9">
                        <input class="form-control" id="fecha_nacimiento_paciente" value ="" type="date" name="text-input" placeholder="fecha de nacimiento">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">edad</label>
                    <div class="col-md-9">
                        <input class="form-control" id="edad_paciente" value ="" type="number" name="text-input" placeholder="edad">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select2">Sexo</label>
                    <div class="col-md-9">
                        <select class="form-control" id="sexo_select" name="select2">
                            <option value="0">Seleccione Sexo</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                            <option value="SOtro">Otro</option>
                            
                        </select>
                    </div>
                    
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Prevision</label>
                    <div class="col-md-4">
                        <select class="form-control" id="prevision_select" name="select1">
                            <option value="0">Seleccione previsión</option>
                            <option value="Fonasa">Fonasa</option>
                            <option value="Fonasa">Isapre</option>
                            <option value="Sin Prevision">Particular</option>
                            <option value="Isapre">Sin previsión</option>
                        </select>
                    </div>
                    <label class="col-md-2 col-form-label" for="text-input">(*) indique la isapre</label>
                    <div class="col-md-3">
                        <input class="form-control" id="isapre_name_paciente" value ="" type="text" name="text-input" placeholder="nombre Isapre">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="selectpicker">a Little about me</label>
                    <div class="col-md-9">

                        <textarea class="form-control" id="textarea_obs_paciente" name="textarea-input" rows="4" placeholder="a Little about me..."></textarea>
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