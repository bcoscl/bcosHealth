
<script src="../../pages/includes/js/PopUpsEditPacienteInformation.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModalPacienteInformation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Editar Información Paciente</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">

                <form id="formEditPaciente">


                    <div id="home" class="tab-pane in active">
                        <div class="row">


                            <div class="col-xs-12 col-sm-12">





                                <div class="profile-user-info">

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Nombre </div>

                                        <div class="profile-info-value">
                                            <input class="form-control" id="modal_input_paciente_nombre" type="text" name="text-input" placeholder="Nombre" disabled>
                                        </div>
                                    </div>

                                    <div class="profile-info-row" style="display: none;">
                                        <div class="profile-info-name"> Rut </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_rut" type="text" name="text-input" placeholder="Rut" disabled>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Direccion </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_direccion" type="text" name="text-input" placeholder="direccion"  required>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Edad </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_edad" type="number" name="text-input" placeholder="edad" required >
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> F.Nacimiento </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_fechaNacimiento" type="date" name="text-input" placeholder="fecha"  required>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Prevision </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_prevision" type="text" name="text-input" placeholder="prevision"  required>
                                        </div>
                                    </div>
                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Telefono </div>

                                        <div class="profile-info-value" id="input_telefono">
                                            <input class="form-control" id="modal_input_paciente_telefono" type="text" name="text-input" placeholder="telefono"  required>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> email </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_email" type="text" name="text-input" placeholder="email"  required>
                                        </div>
                                    </div>
                                    <!--                        </div>-->

                                    <!--                        <div class="hr hr-8 dotted"></div>-->

                                    <!--                        <div class="profile-user-info">-->
                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> Profesion </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_profesion" type="text" name="text-input" placeholder="profesion"  required>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name" > Estado Civil </div>

                                        <div class="profile-info-value" >
                                            <input class="form-control" id="modal_input_paciente_estado_civil" value ="" type="text" name="text-input" placeholder="Soltero(a), Casado(a), Viudo(a)" required>
                                        </div>
                                    </div>
                                    <div class="profile-info-row">
                                        <div class="profile-info-name" > Sexo </div>

                                        <div class="profile-info-value" >
                                            <select class="form-control" id="modal_sexo_paciente_select" name="select2" required>
                                                <option value="">Seleccione Sexo</option>
                                                <option value="Masculino">Masculino</option>
                                                <option value="Femenino">Femenino</option>
                                                <option value="SOtro">Otro</option>

                                            </select>
                                        </div>
                                    </div>

                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.row -->

                        <div class="space-20"></div>
                        <hr>
                        <div class="row">
                            <div class="col-xs-12 col-sm-12">
                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-small">
                                        <h4 class="widget-title smaller">
                                            <i class="ace-icon fa fa-check-square-o bigger-110"></i>
                                            Little About Me
                                        </h4>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main" >
                                            <textarea class="form-control" id="modal_input_paciente_aboutMe" name="textarea-input" rows="2" placeholder="a Little about me..." required></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /#home -->





                </form>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbutton_editInformation" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>