
<script src="../../pages/includes/js/PopUpsSubirArchivos.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModalsubirArchivos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Subir Archivo</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div> 
            <form id ="formArchivoPopUp" name="formArchivoPopUp" action="../../ServletUploadArchivo" method="post"
                         enctype="multipart/form-data">
                <div class="modal-body">

                     <input id="input_userid" style="display: none;" type="text" name="input_userid">
                    <h4 class="blue">
                        <span class="middle" id="modal_input_Titulo_Archivo"></span>
                    </h4>
                    <label class="col-md-12 col-form-label" for="date-input" id ="modal_ArchivoUserProfile_instruccion">Seleccione el arhivo a subir.</label>

                    <div class="row">
                        <div class="col-xs-12 col-sm-12">
                            <div class="form-group row">
                                <div class="col-md-12">
                                    <a href="javascript:void(0)"  id="fileuploadArchivo" class="btn btn-sm btn-block btn-success">
                                        <i class="ace-icon fa fa-cloud-upload bigger-120"></i>
                                        <span class="bigger-110">Buscar Archivo</span>
                                    </a>
                                    <input id="upload-file" name="upload-file" type="file" style="display: none;" />
                                </div>
                            </div>

                        </div><!-- /.col -->
                    </div><!-- /.row -->

                </div>
                <div class="modal-footer">
                    <!--<button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>-->
                    <input id="modal_subirArchivo" class="btn btn-primary" type="submit"></button>
                </div></form>
        </div>

    </div>

</div>