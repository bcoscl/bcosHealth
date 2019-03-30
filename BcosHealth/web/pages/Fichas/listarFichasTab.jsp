<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@include file="../includes/header.jsp" %>


<title>Bcos Health - Listar Fichas2</title>

<script src="./js/listarFichasTab.js"></script>



<%@include file="../includes/body.jsp" %>
<!-- Contenido -->



<div class="row">
    <div class="col">
        <ul class="nav nav-pills mb-1" id="pills-tab" role="tablist">
            <li class="nav-item ">
                <a class="nav-link active show" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Profile</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Consultas</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab" aria-controls="pills-contact" aria-selected="false">Examenes</a>
            </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">


            <div class="tab-pane fade active show" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                <!-- tab 1 -->   


                <!-- Detalle Fichas -->
                
                <%@include file="DetalleFicha.jsp" %>
                
                <!-- Detalle Fichas -->


                <!-- FIN tab 1 -->   
            </div>
            <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                <!-- tab 2-->
                <%@include file="../Consultas/consultas.jsp" %>
                <!-- FIN tab 2-->
            </div>
            <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
                <!-- tab 3 -->


                 <%@include file="../Examenes/examenesList.jsp" %>


                <!-- FIN tab 3 -->
            </div>


        </div>
    </div>
</div>





<!-- fin del contenido -->

<%@include file="../includes/footer.jsp" %>




