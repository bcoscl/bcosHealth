
<script src="../../pages/includes/js/PopUpsOlvido.js"></script>


<div class="modal fade" id="modalOlvido" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-success" role="document">
        <div class="modal-content">
            <div class="modal-header" >
                <h4 class="modal-title slide" >¿Olvidaste tu Password?</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body" id="modal_title">
                <form id="OlvidoForm">
                      <div class="form-group row">

                        <div class="col-md-12">
                            <label class="col-md-12 col-form-label" for="text-input">
                                Ingrese tu email y usuario registrado y recibirás un correo electrónico con un enlace para restablecer tu contraseña.<br> Para mayor seguridad, este enlace expira después de 30 min.
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">

                        <div class="col-md-12">
                            <input class="form-control" id="modal_olvido_numuser" type="text" name="text-input" placeholder="rut usuario registrado" required>
                            <!--<label id="user_registrado" class="alert alert-danger alert-dismissible fade show-none" style="display: none;"></label>-->

                        </div>
                    </div>
                    <div class="form-group row">

                        <div class="col-md-12">
                            <input class="form-control" id="modal_olvido_email" type="email" name="text-input" placeholder="emailregistrado@email.com" required>
                            <!--<label id="user_registrado" class="alert alert-danger alert-dismissible fade show-none" style="display: none;"></label>-->

                        </div>
                    </div>

                    <div class="form-group row">

                        <div class="col-12">
                            <button id="modal_addOlvidoPass" class="btn btn-info btn-lg btn-block" type="button" >Recuperar contraseña</button>
                        </div>

                    </div>
                </form>
            </div>

        </div>

    </div>

</div>
 