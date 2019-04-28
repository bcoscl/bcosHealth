<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - Listar Pacientes</title>
<script src="./js/listarPaciente.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">
<%@include file="../includes/body.jsp" %>

<!-- contenido -->



<div id="ui-view"><div>

        <span >	Listar Pacientes</span>

        <div class="animated fadeIn">
            <div class="card">

                <div class="card-body">
                    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                        <div class="row"><div class="col-sm-12 col-md-6">
                                <div class="dataTables_length" id="DataTables_Table_0_length">
                                    <!--<label>Show <select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="custom-select custom-select-sm form-control form-control-sm">
                                    <option value="10">10</option><option value="25">25</option>
                                    <option value="50">50</option><option value="100">100</option>
                                    </select> entries</label>-->
                                </div>
                            </div>
                            <div class="col-sm-12 col-md-6">
                                <div id="DataTables_Table_0_filter" class="dataTables_filter">
                                    <label>Buscar:<input type="search" id="entradafilter" class="form-control form-control-sm" placeholder="" aria-controls="DataTables_Table_0">
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="contenido">
                            
                            <!--tabla con el contenido, se dubuja con los Datos-->
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- fin contenido -->		  

        <!--Mensajes de carga, mensajes de error y success-->
        <%@include file="../includes/message/message.jsp" %>

        <!--modal de agregar a la lista de atencion de medicos-->
        <%@include file="../includes/popups/PopUpsAttentionList.jsp" %>


        <%@include file="../includes/footer.jsp" %>
