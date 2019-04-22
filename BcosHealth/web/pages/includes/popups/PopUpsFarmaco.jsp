
<script src="../../pages/includes/js/PopUpsFarmaco.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModalFarmaco" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Farmacos</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <h4 class="blue">
                    <span class="middle" id="modal_input_farmaco_pacienteName"></span>
                </h4>
                <label class="col-md-12 col-form-label" for="date-input" id ="modal_farmaco_instruccion"></label>



                <div class="row">


                    <div class="col-xs-12 col-sm-12">


                        <input class="form-control" id="modal_input_farmaco_id" style="display: none;" type="text" name="text-input">
                        <input class="form-control" id="modal_input_farmaco_accion" style="display: none;" type="text" name="text-input">
                        <input class="form-control" id="modal_input_farmaco_numuserpaciente" style="display: none;" type="text" name="text-input">



                        <div class="profile-user-info">

                            <div class="profile-info-row">
                                <div class="profile-info-name"> Nombre </div>

                                <div class="profile-info-value">
                                    <input class="form-control" id="modal_input_farmaco_nombre" type="text" name="text-input" placeholder="Nombre farmaco">
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name"> Observación </div>

                                <div class="profile-info-value" >
                                    <input maxlength="40" class="form-control" id="modal_input_farmaco_observacion" type="text" name="text-input" placeholder="Ingrese la medicación">
                                </div>
                            </div>


                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->









            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbutton_farmaco" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>