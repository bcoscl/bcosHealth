
<script src="../../pages/includes/js/PopUpsExamenes.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModalExamen" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Examenes</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formExamenespup">

                    <h4 class="blue">
                        <span class="middle" id="modal_input_examenes_pacienteName"></span>
                    </h4>
                    <label class="col-md-12 col-form-label" for="date-input" id ="modal_examenes_instruccion"></label>

                    <input class="form-control" id="modal_input_examenes_id" style="display: none;" type="text" name="text-input">
                    <input class="form-control" id="modal_input_examenes_accion" style="display: none;" type="text" name="text-input">
                    <input class="form-control" id="modal_input_examenes_numuserpaciente" style="display: none;" type="text" name="text-input">


                    <div class="row">


                        <div class="col-xs-12 col-sm-12">

                            <div class="profile-user-info">

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Nombre </div>

                                    <div class="profile-info-value">
                                        <input class="form-control" id="modal_input_examenes_nombre" type="text" name="text-input" placeholder="Nombre Examen" required>
                                    </div>
                                </div>

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Observación </div>

                                    <div class="profile-info-value" >
                                        <input class="form-control" id="modal_input_examenes_observacion" type="text" name="text-input" placeholder="Observación del Examen" required>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> Examen </div>

                                    <div class="profile-info-value" >
                                        <a href="javascript:void(0)"  id="fileuploadExamen" class="btn btn-sm btn-block btn-success">
                                            <i class="ace-icon fa fa-cloud-upload bigger-120"></i>
                                            <span class="bigger-110">Subir Examen</span>
                                        </a>
                                        <input id="upload-file-examen" type="file" style="display: none;"/>
                                    </div>
                                </div>

                            </div>
                        </div><!-- /.col -->
                    </div><!-- /.row -->








                </form>
            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbuttonExamenes" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>