
<script src="../../pages/includes/js/PopUpsCronica.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModalCronica" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Enfermedad Cronica</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formCronica">
                    <h4 class="blue">
                        <span class="middle" id="modal_input_cronica_pacienteName"></span>
                    </h4>
                    <label class="col-md-12 col-form-label" for="date-input" id ="modal_cronica_instruccion"></label>



                    <div class="row">


                        <div class="col-xs-12 col-sm-12">



                            <input class="form-control" id="modal_input_croncia_id" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_cronica_accion" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_cronica_numuserpaciente" style="display: none;" type="text" name="text-input">

                            <div class="profile-user-info">

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Nombre </div>

                                    <div class="profile-info-value">
                                        <input class="form-control" id="modal_input_croncia_nombre" type="text" name="text-input" placeholder="Enfermedad Cronica" required>
                                    </div>
                                </div>

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Observación </div>

                                    <div class="profile-info-value" >
                                        <input maxlength="40" class="form-control" id="modal_input_cronica_observacion" type="text" name="text-input" placeholder="Comentario sobre la enfermedad" required>
                                    </div>
                                </div>


                            </div>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </form>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbutton_cronica" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>