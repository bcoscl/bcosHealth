
<script src="../../pages/includes/js/PopUpsAttentionList.js"></script>

<!--Modal -->

<div class="modal fade" id="largeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Lista de Atención</h4>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">

                <form id="formattentionList">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Nombre</label>
                            <input type="text" class="form-control" id="modal_nombre" disabled required>
                        </div>
                        <div class="form-group col-md-6">
                            <label >Rut</label>
                            <input type="text" class="form-control" id="modal_numuser" disabled required>
                        </div>
                    </div>
                    <div class="form-row">

                        <div class="form-group col-md-12">
                            <label>Agregar a la lista de</label>
                            <select class="form-control" id="medico_select" name="select1" required>

                            </select>
                        </div>

                    </div>
                    <div class="form-row">

                        <div class="form-group col-md-12">
                            <label>¿Motivo de la visita?</label>
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <div class="form-check">
                                        <input class="form-check-input"  name="exampleRadios" type="radio" id="gridCheck1">
                                        <label class="form-check-label" for="gridCheck1">Consulta</label>
                                    </div>
                                </div>
                                <div class="form-group col-md-3">
                                    <div class="form-check">
                                        <input class="form-check-input"  name="exampleRadios" type="radio" id="gridCheck2">
                                        <label class="form-check-label" for="gridCheck2">Control</label>
                                    </div>
                                </div>
                                <div class="form-group col-md-4">
                                    <div class="form-check">
                                        <input class="form-check-input" name="exampleRadios" type="radio" id="gridCheck3">
                                        <label class="form-check-label" for="gridCheck3">muestra exámenes</label>
                                    </div>
                                </div>
                                <div class="form-group col-md-2">
                                    <div class="form-check">
                                        <input class="form-check-input" name="exampleRadios" type="radio" id="gridCheck4">
                                        <label class="form-check-label" for="gridCheck4">otro</label>
                                    </div>
                                </div>

                            </div>
                            <input type="text" class="form-control" id="modal_motivo" required>
                        </div>

                    </div>

                </form>

            </div>

            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                <button id="modal_addAtbutton" class="btn btn-primary" type="button">Agregar</button>
            </div>
        </div>

    </div>

</div>