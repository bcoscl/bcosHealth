<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<!-- profile -->


<div id="user-profile-2" class="user-profile">
    <div id="tabs" class="tabbable">
        <!--        <ul class="nav nav-tabs padding-18">-->
        <ul  class="nav nav-tabs">
            <li class="nav-item btn-group">
                <a id="a-profile" data-toggle="tab" class="profile nav-link active" href="#home">
                    <i class="blue ace-icon fa fa-user bigger-120"></i>
                    Perfil
                </a>
                <button type="button" class="btn btn-sm btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="javascript:popupEditPacienteInformation();">
                        Editar Información</a>                   
                </div>
            </li>

            <li class="nav-item btn-group">
                <a data-toggle="tab" class="profile nav-link" href="#Cronicas">
                    <i class="ace-icon fa fas fa-heartbeat bigger-120" style="color: #ff2600"></i>
                    Enfermedades Crónicas
                </a>
                <button type="button" class="btn btn-sm btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="javascript:popupAddCronica();">
                        <!--<i class="ace-icon fa fas fa-plus-square bigger-120" style="color: #ff2600"></i>-->
                        Agregar Enfermedad crónica</a></a>                   
                </div>
            </li>

            <li class="nav-item btn-group">
                <a data-toggle="tab"  class="profile nav-link" href="#Farmacos">
                    <i class="ace-icon fa  fa-flask bigger-120" style="color: #00b50e"></i>
                    Fármacos
                </a>
                <button type="button" class="btn btn-sm btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="javascript:popupAddFarmacos();">
                        <!--<i class="ace-icon fa  fa-plus-square bigger-120" style="color: #00b50e" ></i>-->
                        Agregar Fármaco</a>                 
                </div>

            </li>


        </ul>

        <div class="tab-content no-border padding-24">

            <div id="home" class="profile tab-pane in active">
                <!-- profile -->
                <%@include file="../Perfil/pacienteProfileBody.jsp" %>

                <!-- fin profile -->
            </div><!-- /#home -->


            <div id="Cronicas" class="profile tab-pane">
                <!-- Enfermedades Cronicas -->

                <%@include file="../Cronicas/cronicas.jsp" %>
                <div class="space-12"></div>

                <!-- FIN Enfermedades Cronicas -->
            </div><!-- /#feed -->

            <div id="Farmacos" class="profile tab-pane">


                <!-- FARMACOS -->
                <%@include file="../Farmacos/farmacos.jsp" %>



                <!-- FIN FARMACOS -->
            </div>

            <div class="hr hr10 hr-double"></div>


        </div><!-- /#Farmacos -->


    </div>
</div>





<!-- fin Profile -->


