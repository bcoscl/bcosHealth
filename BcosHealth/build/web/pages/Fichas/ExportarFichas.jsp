<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@include file="../includes/header.jsp" %>


<title>Bcos Health - Exportar Fichas</title>

<script src="./js/ExportarFichas.js"></script>



<link href="../../pages/Fichas/css/detalleFichas.css" rel="stylesheet">  
<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">
<link href="./css/ExportarFichas.css" rel="stylesheet">

<%@include file="../includes/body.jsp" %>


<!-- contenido -->



<div id="ui-view"><div>

        <span >	Exportar Fichas</span>

        <div class="animated fadeIn">
            <div class="card">

                <div class="card-body">
                    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                        <div class="row">

                            <div class="col-sm-9 col-md-9">
                                <div class="dataTables_length" id="DataTables_Table_0_length">
                                    <form id="form_Export">   
                                        <div id="pacientes_selectmultiple_content">


                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-sm-1 col-md-2">

                                <button id="BuscarButton" class="btn btn-block btn-primary" type="button">
                                    <i class="fa fa-search"></i> 
                                    Buscar</button>


                            </div>
                            <div class="col-sm-2 col-md-1">


                                <button id="Export" onclick="javascript:Export();" class="btn btn-block btn-ghost-danger active" type="button" aria-pressed="true"><span class="fa fa-print"></span></button>  

                            </div>

                        </div>


                        <div class="row" id='contenido'>


<!--
                            <div class="card-body">
                                <div id="accordion" role="tablist">


                                    <div class="card mb-0">
                                        <div  id="headingOne" role="tab">
                                            <table class="table table-striped table-bordered datatable dataTable no-footer" id="DataTables_Table_0" role="grid" aria-describedby="DataTables_Table_0_info" style="border-collapse: collapse !important">
                                                <tbody> 
                                                    <tr role="row" class="odd">
                                                        <td class="sorting_1">    
                                                            <div class="row">        
                                                                <div class="col-xs-12 col-sm-2 middle">            
                                                                    <img class="img-thumbnail editable img-responsive" alt=" Avatar" id="avatar2" src="../Perfil/img/ProfilePerson.jpg">        
                                                                </div> /.col         
                                                                <div class="col-xs-12 col-sm-10">            
                                                                    <h4 class="blue">                
                                                                        <span class="middle" id="input_nombre">Luis Sebastian Farias Catalan</span> 
                                                                        <a data-toggle="collapse" href="#collapse3_id"
                                                                           aria-expanded="false" aria-controls="collapseOne" 
                                                                           class="collapsed">Luis Sebastian Farias Catalan</a>
                                                                    </h4>            
                                                                    <div class="profile-user-info">                
                                                                        <div class="profile-info-row">                    
                                                                            <div class="profile-info-name"> Rut </div>                    
                                                                            <div class="profile-info-value" id="input_rut">                        
                                                                                <span>15729920</span>                 
                                                                            </div>        
                                                                            <div class="profile-info-name"> Direccion </div>                    
                                                                            <div class="profile-info-value" id="input_rut">                        
                                                                                <span>Carmen 297,Santiago</span>                 
                                                                            </div>        
                                                                        </div>            
                                                                        <div class="profile-info-row">              
                                                                            <div class="profile-info-name"> Telefono </div>          
                                                                            <div class="profile-info-value" id="input_motivo">    
                                                                                <span>+569963344467</span>         
                                                                            </div>         
                                                                            <div class="profile-info-name"> email </div>          
                                                                            <div class="profile-info-value" id="input_motivo">    
                                                                                <span>acantero88@gmail.com</span>         
                                                                            </div>         
                                                                        </div>           
                                                                        <div class="profile-info-row">    
                                                                            <div class="profile-info-name">Estado Civil</div>        
                                                                            <div class="profile-info-value" id="input_fecha">          
                                                                                <span>Soltero</span>           
                                                                            </div>         
                                                                            <div class="profile-info-name">Sexo</div>        
                                                                            <div class="profile-info-value" id="input_fecha">          
                                                                                <span>Masculino</span>           
                                                                            </div>         
                                                                        </div>        
                                                                    </div>      
                                                                </div> /.col   
                                                            </div> /.row 
                                                        </td>
                                                    </tr>    
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="collapse" id="collapse3_id" role="tabpanel" aria-labelledby="headingOne" 
                                             data-parent="#accordion" style="">
                                            <div class="card-body">
                                                <div class="card-body">
                                                    <div class="email-app mb-4"> 
                                                        <main class="inbox">  
                                                            <ul class="messages contenidobusqueda">
                                                                
                                                                <li class="message">
                                                                    <div class="actions">    
                                                                        <span class="action">  
                                                                            <i class="fa fa-user-md"></i>    
                                                                        </span>    
                                                                        <span class="action">  
                                                                            <i class="fa fa-commenting"></i>    
                                                                        </span>
                                                                    </div>
                                                                    <div class="header">
                                                                        <span class="from">Dr. Alexis Antonio Cantero Castro </span>                                                                               
                                                                        <span class="date">  
                                                                            <span class="fa fa-paper-clip"></span>Tuesday  , 23 April     2019 - 20:42:07. 
                                                                            <i class="fa fa-calendar"></i>
                                                                        </span>
                                                                    </div>
                                                                    <div class="title">control</div>
                                                                    <div class="description">tercer control de sano</div>  
                                                                </li>
                                                                
                                                            </ul>
                                                        </main>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <span class="hr"></span>   





                                </div>
                            </div>-->




                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--</div>-->




<!--<div id="msgResult" class="alert alert-success alert-dismissible fade show-none" role="alert">
    <strong>Perfect!</strong> Plan Registrado exitosamente
    <button class="close" type="button" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">×</span>
    </button>
</div>-->


<!-- fin contenido -->

<%@include file="../includes/message/message.jsp" %>
<%@include file="../includes/footer.jsp" %>