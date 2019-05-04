<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear usuario</title>

<script src="./js/crearUsers.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">



<%@include file="../includes/body.jsp" %>


<!-- contenido -->



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Usuario</strong></div>
        <div class="card-body">





            <form id="crearUsers" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Rut  (sin digito verificador)</label>
                    <div class="col-md-9">
                        <input class="form-control" id="numuser_user" type="text" name="text-input" placeholder="Rut  (sin digito verificador)" required>
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
                    <div class="col-md-9">
                        <input class="form-control" id="nombre_user" type="text" name="text-input" placeholder="Nombres" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Apellidos</label>
                    <div class="col-md-9">
                        <input class="form-control" id="apellido_user" type="text" name="text-input" placeholder="Apellidos" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="email-input">Email Contacto</label>
                    <div class="col-md-9">
                        <input class="form-control" id="email_contacto_user" type="email" name="email-input" placeholder="email de contacto" autocomplete="email" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Telefono de Contacto</label>
                    <div class="col-md-9">
                        <input class="form-control" id="numero_telefono_user" value ="" type="text" name="text-input" placeholder="numero de Telefono +569" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="selectpicker">Profesión</label>
                    <div class="col-md-9" id="profesiones_select_content">

                        <!--                        <select class="selectpicker" multiple id="profesion_select">
                                                    <option>- Kinesionlogo -</option>
                                                    <option>- Traumatologo -</option>
                                                    <option>- General -</option>
                                                    <option>- Psicologo -</option>
                                                </select>-->
                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="selectpicker">a Little about me</label>
                    <div class="col-md-9">

                        <textarea class="form-control" id="textarea_obs" name="textarea-input" rows="4" placeholder="a Little about me..." required></textarea>
                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="selectpicker">Sucursales</label>
                    <div class="col-md-9" id="suc_select_content" required>
                        <!--
                                                <select class="selectpicker" multiple id="sucursal_select">
                                                    <option>[ SUC 1 ]</option>
                                                    <option>[ SUC 2 ]</option>
                                                    <option>[ SUC 3 ]</option>
                                                    <option>[ SUC 4 ]</option>
                                                </select>-->
                    </div>

                </div>



                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="selectpicker">Roles</label>
                    <div class="col-md-9" id="roles_select_content" required>


                    </div>

                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="selectpicker">Password</label>
                    <div class="col-md-3">
                        <input type="text" id="password" name="password" maxlength="10" class="form-control input_pass" value="" placeholder="password" required> 

                    </div>

                    <div class="col-md-3">
                        <div class="form-check form-check-inline mr-1">
                            <button class="btn btn-pill btn-danger" id="btn_generate" type="button">Generar Clave</button>

                        </div>

                    </div>

                    <div class="col-md-3">
                        <div class="form-check form-check-inline mr-1">
                            <!--                            <input class="form-check-input" id="inline-checkbox1" type="checkbox" value="check1">
                                                        <label class="form-check-label" for="inline-checkbox1">enviar por email</label>
                            -->
                        </div>

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
    <strong>Perfect!</strong> usuario Registrado exitosamente
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