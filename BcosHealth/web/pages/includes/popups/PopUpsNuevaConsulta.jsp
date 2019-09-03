
<script src="../../pages/includes/js/PopUpsNuevaConsulta.js"></script>

<!--<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="js/bootstrap-datetimepicker.min.js"></script>-->


<div class="modal fade" id="primaryModalConsulta" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-primary" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title slide">Nueva Consulta</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formnuevaconsulta">
                    <h4 class="blue">
                        <span class="middle" id="modal_input_consulta_pacienteName"></span>
                    </h4>
                    <label class="col-md-12 col-form-label" for="date-input" id ="modal_consulta_instruccion"></label>



                    <div class="row">


                        <div class="col-xs-12 col-sm-12">


                            <input class="form-control" id="modal_input_consulta_id" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_consulta_accion" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_consulta_numuserpaciente" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_consulta_pacientename_hid" style="display: none;" type="text" name="text-input">



                            <div class="profile-user-info">

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Motivo </div>

                                    <div class="profile-info-value">
                                        <input class="form-control" id="modal_input_consulta_titulo" type="text" name="text-input" placeholder="motivo de la consulta" required>
                                    </div>

                                </div>

                                <div class="profile-info-row slidedown">
                                    <div class="profile-info-name"> Fecha </div>

                                    <div class="profile-info-value">
                                        <input class="form-control" id="modal_input_consulta_fecha" type="date" name="text-input" placeholder="motivo de la consulta">
                                    </div>

                                </div>
                                <div class="profile-info-row slidedown">
                                    <div class="profile-info-name"> Hora </div>


                                    <div class="profile-info-value">
                                        <input class="form-control" id="modal_input_consulta_hora" type="time" name="text-input" placeholder="motivo de la consulta">
                                    </div>

                                </div>


                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Indicaciones </div>

                                    <div class="profile-info-value" >
                                        <textarea class="form-control" id="modal_input_consulta_observacion" name="textarea-input" rows="4" placeholder="Comentario sobre la consulta" required></textarea>
                                        <!--<input class="form-control" id="modal_input_consulta_observacion" type="text" name="text-input" placeholder="Comentario sobre la consulta">-->
                                    </div>
                                </div>


                            </div>
                        </div><!-- /.col -->
                    </div><!-- /.row -->








                </form>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbutton_nueva_consulta" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>