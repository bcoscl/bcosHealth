<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - crear Suscripciones</title>

<script src="./js/userProfile.js"></script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">

<%@include file="../includes/body.jsp" %>



<div id="ui-view"><div>

        <span>	User</span>

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

                        </div>

                        <%@include file="./userProfileBody.jsp" %>


                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--</div>-->





<!--
<div id="msgResult" class="alert alert-success alert-dismissible fade show-none" role="alert">
    <strong>Perfect!</strong> Suscripcion Registrado exitosamente
    <button class="close" type="button" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>

<div id="msgResultError" class="alert alert-danger alert-dismissible fade show-none" role="alert">
    <strong>Ups!</strong> Se produjo un error al realizar la solicitud, por favor intentalo de nuevo
    <button class="close" type="button" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>



<div id="load" class="sk-fading-circle" style="display: none;">
    <div class="sk-circle1 sk-circle"></div>
    <div class="sk-circle2 sk-circle"></div>
    <div class="sk-circle3 sk-circle"></div>
    <div class="sk-circle4 sk-circle"></div>
    <div class="sk-circle5 sk-circle"></div>
    <div class="sk-circle6 sk-circle"></div>
    <div class="sk-circle7 sk-circle"></div>
    <div class="sk-circle8 sk-circle"></div>
    <div class="sk-circle9 sk-circle"></div>
    <div class="sk-circle10 sk-circle"></div>
    <div class="sk-circle11 sk-circle"></div>
    <div class="sk-circle12 sk-circle"></div>
</div>-->
<%@include file="../includes/message/message.jsp" %>
<!-- foofter -->
<%@include file="../includes/footer.jsp" %>