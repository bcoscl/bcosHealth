
<script src="../../pages/includes/js/PopUpsEditarSuscripcion.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModal_editSuscripcion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" 
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Editar Suscripción</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">

                
                  <form id="formCrearSuscripciones" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

                  <input class="form-control" id="modal_suscripcion_id" style="display: none;" type="text" name="text-input">
                <div class="form-group row">
                    <label class="col-md-4 col-form-label" for="text-input">Empresa Suscriptora</label>
                    <div class="col-md-8">
                        <input class="form-control" id="modal_nombre_empresa" type="text" name="text-input" placeholder="Nombre de Empresa" required disabled="disabled">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-4 col-form-label" for="text-input">Contacto Empresa</label>
                    <div class="col-md-8">
                        <input class="form-control" id="modal_contacto_empresa" type="text" name="text-input" placeholder="Contacto de Empresa" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-4 col-form-label" for="email-input">Email Contacto</label>
                    <div class="col-md-8">
                        <input class="form-control" id="modal_email_contacto" type="email" name="email-input" placeholder="email de contacto" autocomplete="email" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-4 col-form-label" for="text-input">Télefono de Contacto</label>
                    <div class="col-md-8">
                        <input class="form-control" id="modal_numero_telefono" value ="+569" type="text" name="text-input" placeholder="numero de Telefono +569" required>
                    </div>
                </div>

<!--                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="date-input">Fecha de Inicio</label>
                    <div class="col-md-9">
                        <input class="form-control" id="fecha_inicio" type="date" name="date-input" placeholder="Fecha" required>					
                    </div>
                </div>-->

                <div class="form-group row">
                    <label class="col-md-4 col-form-label" for="select1">Plan</label>
                    <div class="col-md-8">
                        <select class="form-control" id="select_plan" name="select1" required>

                        </select>
                    </div>
                </div>

               


            </form>
                

            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_editSuscricpion" class="btn btn-primary" type="button">Actualizar</button>
            </div>
        </div>

    </div>

</div>

