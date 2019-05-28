
<script src="../../pages/includes/js/PopUpsSubirArchivoExamen.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModalsubirArchivosExamen" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Subir Archivo</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div> 
            <form id ="formArchivoPopUp_examen" name="formArchivoPopUp_examen" action="../../ServletUploadArchivoExamen" method="post"
                         enctype="multipart/form-data">
                <div class="modal-body">

                     <input id="input_userid_examen" style="display: none;" type="text" name="input_userid_examen">
                    <h4 class="blue">
                        <span class="middle" id="modal_input_Titulo_Archivo_examen"></span>
                    </h4>
                    <label class="col-md-12 col-form-label" for="date-input" id ="modal_ArchivoUserProfile_instruccion_examen">Seleccione el arhivo a subir.</label>

                    <div class="row">
                        <div class="col-xs-12 col-sm-12">
                            <div class="form-group row">
                                <div class="col-md-12">
                                    <a href="javascript:void(0)"  id="fileuploadArchivo_examen" class="btn btn-sm btn-block btn-success">
                                        <i class="ace-icon fa fa-cloud-upload bigger-120"></i>
                                        <span class="bigger-110">Buscar Archivo</span>
                                    </a>
                                    <input id="upload-file_examen" name="upload-file_examen" type="file" style="display: none;" />
                                </div>
                            </div>

                        </div><!-- /.col -->
                    </div><!-- /.row -->

                </div>
                <div class="modal-footer">
                    <!--<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>-->
                    <input id="modal_subirArchivo_examen" class="btn btn-primary" type="submit"></button>
                </div></form>
        </div>

    </div>

</div>