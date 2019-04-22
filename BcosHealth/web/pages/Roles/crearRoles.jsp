<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@include file="../includes/header.jsp" %>

<title>Bcos Health - crear Planes</title>

<script src="./js/crearRol.js"></script>

<%@include file="../includes/body.jsp" %>


<!-- contenido -->

<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Roles</strong></div>
        <div class="card-body">
            <form class="form-horizontal" method="post" id="formCrearRoles">              

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Nombre del Rol</label>
                    <div class="col-md-9">
                        <input class="form-control input_user" id="RoleName" maxlength="15" value="" type="text" name="text-input" placeholder="Rol" required>
                    </div>
                </div>


        </div>
        <div class="card-footer">
            <button id="submitButton" class="btn btn-sm btn-primary"  type="button">
                <!--<button id="success-alert" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#successModal" type="submit">-->
                <i class="fa fa-dot-circle-o"></i> Submit</button>
            <!--<button class="btn btn-sm btn-danger" type="reset">
            <i class="fa fa-ban"></i> Reset</button>-->
        </div>
        </form>



    </div>


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

    
    



<!--<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog modal-success" role="document">
<div class="modal-content">
<div class="modal-header">
<h4 class="modal-title">Modal title</h4>
<button class="close" type="button" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">Ã</span>
</button>
</div>
<div class="modal-body">
<p>One fine bodyâ¦</p>
</div>
<div class="modal-footer">
<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
<button class="btn btn-success" type="button">Save changes</button>
</div>
</div>

</div>

</div>-->
    

    <!-- fin contenido -->		  
    <%@include file="../includes/footer.jsp" %>