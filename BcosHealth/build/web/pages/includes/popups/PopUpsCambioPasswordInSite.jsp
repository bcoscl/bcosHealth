
<script src="../../pages/includes/js/PopUpsCambioPasswordInSite.js"></script>


<div class="modal fade" id="modalCambioPasswordInSite" tabindex="-1" role="dialog" 
     aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-primary" role="document">
        <div class="modal-content">
            <div class="modal-header" >
                <h4 class="modal-title slide" >Cambio de Password</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                   
                </button>
            </div>
            <div class="modal-body" id="modal_title">
                <form id="CambioForm">

                    <div class="form-group row">

                        <div class="col-md-12">

                            Ingresa la nueva Contraseņa

                            <input class="form-control" id="modal_nuevaPass" type="password" name="text-input" placeholder="nueva password" required>
                            <!--<label id="user_registrado" class="alert alert-danger alert-dismissible fade show-none" style="display: none;"></label>-->

                        </div>
                    </div>
                    <div class="form-group row">

                        <div class="col-md-12">

                            Repita la Contraseņa

                            <input class="form-control" id="modal_nuevaPass_rep" type="password" name="text-input" placeholder="repite la password" required>
                            <!--<label id="user_registrado" class="alert alert-danger alert-dismissible fade show-none" style="display: none;"></label>-->

                        </div>
                    </div>

                    <div class="form-group row">

                        <div class="col-12">
                            <button id="modal_cambioPasswordInSite" class="btn btn-primary btn-lg btn-block" type="button" >Cambiar Password</button>
                        </div>

                    </div>
                </form>
            </div>

        </div>

    </div>

</div>

