
<script src="../../pages/includes/js/PopUpsEvaluacion.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModaleva" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Evaluación Corporal</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formeva">
                    <h4 class="blue">
                        <span class="middle" id="modal_input_eva_pacienteName"></span>
                    </h4>
                    <label class="col-md-12 col-form-label" for="date-input" id ="modal_eva_instruccion"></label>



                    <div class="row">


                        <div class="col-xs-12 col-sm-12">



                            <input class="form-control" id="modal_input_eva_id" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_eva_accion" style="display: none;" type="text" name="text-input">
                            <input class="form-control" id="modal_input_eva_numuserpaciente" style="display: none;" type="text" name="text-input">

                            <div class="profile-user-info">

                                <div  class="profile-info-row">
                                    <div class="fechaRow profile-info-name"> Fecha de Evaluación </div>

                                    <div class="profile-info-value">
                                        <input class="fechaRow form-control" id="modal_input_eva_fecha" value="" type="date" name="modal_input_eva_fecha" required>
                                    </div>


                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Talla (mts)</div>

                                    <div class="profile-info-value">
                                        <input class="form-control OnlyRealNumbers" onkeypress="return validaNumericos(event);" maxlength="4" id="modal_input_eva_talla" type="text" name="modal_input_eva_talla" placeholder="Altura en metros" required>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Peso (kg) </div>

                                    <div class="profile-info-value">
                                        <input class="form-control OnlyRealNumbers" onkeypress="return validaNumericos(event);" id="modal_input_eva_peso" type="text" name="modal_input_eva_peso" maxlength="4" placeholder="Peso en Kg" required>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Grasa Corporal (fat %) </div>

                                    <div class="profile-info-value">
                                        <input class="form-control OnlyRealNumbers" onkeypress="return validaNumericos(event);"  id="modal_input_eva_fat" type="text" name="modal_input_eva_fat" maxlength="4" placeholder="Porcentaje grasa Corporal - fat" required>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Grasa viceral (fatv |n| ) </div>

                                    <div class="profile-info-value">
                                        <input class="form-control OnlyRealNumbers" onkeypress="return validaNumericos(event);"  id="modal_input_eva_fatv" type="text" name="modal_input_eva_fatv" maxlength="4" placeholder="Valor absoluto, grasa viceral - fatv" required>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name "> Musculatura (musc %) </div>

                                    <div class="profile-info-value">
                                        <input class="form-control OnlyRealNumbers" onkeypress="return validaNumericos(event);" id="modal_input_eva_musc" type="text" name="text-input" maxlength="4" placeholder="Porcentaje muscular" required>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> IMC </div>

                                    <div class="profile-info-value">
                                        <input class="form-control OnlyRealNumbers" onkeypress="return validaNumericos(event);" id="modal_input_eva_imc" type="text" name="text-input" maxlength="4" placeholder="Índice IMC" required>
                                    </div>
                                </div>

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Observación </div>

                                    <div class="profile-info-value" >
                                        <!--<input maxlength="200" class="form-control" id="modal_input_eva_observacion" type="text" name="text-input" placeholder="Comentario sobre la Evaluación" required>-->
                                        <textarea class="form-control" id="modal_input_eva_observacion" name="textarea-input" rows="2" placeholder="Comentario sobre la Evaluación" required></textarea>
                                    </div>
                                </div>


                            </div>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </form>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbutton_eva" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>