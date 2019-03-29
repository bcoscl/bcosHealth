<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@include file="../includes/header.jsp" %>

<title>Bcos Health - Crear Sucursales</title>

<script src="./js/sucursales.js"></script>

<%@include file="../includes/body.jsp" %>


<!-- contenido -->


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



<div id="ui-view">
    <div class="card">
        <div class="card-header">
            <strong>Crear Sucursal</strong></div>
        <div class="card-body">
            <form class="form-horizontal" action="/ServletPlanes" method="post" enctype="multipart/form-data">

                <div id="succesAlert" class="alert alert-success" role="alert">
                    <strong>Holy guacamole!</strong> You should check in on some of those fields below.

                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Nombre de la Sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="sucursalName" maxlength="15" type="text" name="text-input" placeholder="Nombre de la Sucursal" required="true">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Comuna de la sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="comunaName" type="text" name="text-input" placeholder="Comuna de la sucursal" required="true">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Teléfono de la sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="telefono" type="text" name="text-input" placeholder="Teléfono de la sucursal" required="true">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Correo de la sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="correo" type="text" name="text-input" placeholder="Correo de la sucursal" required="true">
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="text-input">Contacto de la sucursal</label>
                    <div class="col-md-9">
                        <input class="form-control" id="contacto" type="text" name="text-input" placeholder="Contacto de la sucursal" required="true">
                    </div>
                </div>



                <!--
                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="select1">Creado por</label>
                    <div class="col-md-9">
                        <input class="form-control" id="disabled-input" type="text" name="disabled-input" placeholder="Alexis Cantero - Admin" disabled="">
                    </div>
                </div>-->


            </form>
        </div>
        <div class="card-footer">
            <button id="" class="btn btn-sm btn-primary"  type="button">
                <!--<button id="success-alert" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#successModal" type="submit">-->
                <i class="fa fa-dot-circle-o"></i> Submit</button>
            <!--<button class="btn btn-sm btn-danger" type="reset">
            <i class="fa fa-ban"></i> Reset</button>-->
        </div>




    </div>

</div>




<!-- fin contenido -->		  
<%@include file="../includes/footer.jsp" %>