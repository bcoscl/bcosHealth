<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>-->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/easy-pie-chart/2.1.3/jquery.easypiechart.js"></script>-->

<script src="https://cdn.jsdelivr.net/jquery.easy-pie-chart/1.0.1/jquery.easy-pie-chart.js"></script>
<link href="https://cdn.jsdelivr.net/jquery.easy-pie-chart/1.0.1/jquery.easy-pie-chart.css" rel="stylesheet">
<script src="../../pages/Evaluaciones/js/evaluaciones.js"></script>

<div class="profile-feed row">
    <div class="col-sm-12">


       
            <div class="row">   
                <div class="col-xs-12 col-sm-5 ">  
                    <h4 class="blue">
                        <span class="middle" id="input_nombre_eva"></span>

                    </h4>  
                </div>   
                <div class="col-xs-12 col-sm-3">   
                </div>   
                <div class="col-xs-12 col-sm-3 ">   
                </div>   
                <div class="col-xs-12 col-sm-1">  
                    <button id="Export" onclick="javascript:Export();" class="btn btn-block btn-ghost-danger active" type="button" aria-pressed="true">
                        <span class="fa fa-print">

                        </span>
                    </button>   
                </div>
            </div> 
       


        <div id="contenidoEvaluaciones">




            <div id="user-profile-2" class="user-profile">
                <div id="tabs" class="tabbable">
                    <!--        <ul class="nav nav-tabs padding-18">-->
                    <ul  class="nav nav-tabs">


                        <li class="nav-item btn-group">
                            <a  id="hrefevadash" data-toggle="tab" class="eva nav-link active" href="#evaDash">
                                <i class="ace-icon fa fas fa-dashboard bigger-120" ></i>
                                DashBoard
                            </a>

                        </li>

                        <li class="nav-item btn-group">
                            <a data-toggle="tab"  class="eva nav-link" href="#evaReg">
                                <i class="ace-icon fa  fa-database bigger-120" ></i>
                                Registros
                            </a>


                        </li>


                    </ul>

                    <!--<div class="tab-content no-border padding-24">-->
                    <div class="tab-content no-border">



                        <div id="evaDash" class="eva tab-pane in active">
                            <!-- Evaluaciones DashBoard -->

                            <h5 class="blue">
                                <span class="middle" id="6meses">Ultimas 6 Evaluaciones...</span>

                            </h5>
                            <span id="infoReturn" ></span>
                            <%--<%@include file="../Cronicas/cronicas.jsp" %>--%>

                            <div id="viewDashBoard" class="row">

                                <div class="col-sm-6 col-lg-4">
                                    <div class="card text-white bg-dark">
                                        <div class="card-body pb-0">
                                            <div id="lineChart_title_1" class="text-value"></div>
                                            <!--<div id="lineChart_des_1">Peso Ultima Evaluacion 19-06-2019</div>-->
                                            <div id="lineChart_des_1"></div>
                                        </div>
                                        <div id="canvas_1" class="chart-wrapper mt-3 mx-3" style="height:80px;">
<!--                                            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                                                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0">

                                                    </div>

                                                </div>
                                                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:200%;height:200%;left:0; top:0">                                                    
                                                    </div>                                                    
                                                </div>                                                
                                            </div>-->
                                            <!--<div id="canvas_1">-->
                                            <canvas class="chart chartjs-render-monitor" id="lineChart_1" height="70" width="339" style="display: block; width: 339; height: 70px;"></canvas>
                                            <!--</div>-->
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-4">
                                    <div class="card text-white bg-dark">
                                        <div class="card-body pb-0">
                                            <div id="lineChart_title_2" class="text-value"></div>
                                            <!--<div id="lineChart_des_1">Peso Ultima Evaluacion 19-06-2019</div>-->
                                            <div id="lineChart_des_2"></div>
                                        </div>
                                        <div id="canvas_2" class="chart-wrapper mt-3 mx-3" style="height:80px;">
<!--                                            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                                                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0">

                                                    </div>

                                                </div>
                                                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:200%;height:200%;left:0; top:0">                                                    
                                                    </div>                                                    
                                                </div>                                                
                                            </div>-->
                                            <!--<div id="canvas_2">-->
                                            <canvas class="chart chartjs-render-monitor" id="lineChart_2" height="70" width="339" style="display: block; width: 339; height: 70px;"></canvas>
                                            <!--</div>-->
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-4">
                                    <div class="card text-white bg-dark">
                                        <div class="card-body pb-0">
                                            <div id="lineChart_title_3" class="text-value"></div>
                                            <!--<div id="lineChart_des_1">Peso Ultima Evaluacion 19-06-2019</div>-->
                                            <div id="lineChart_des_3"></div>
                                        </div>
                                        <div id="canvas_3" class="chart-wrapper mt-3 mx-3" style="height:80px;">
<!--                                            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                                                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0">

                                                    </div>

                                                </div>
                                                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:200%;height:200%;left:0; top:0">                                                    
                                                    </div>                                                    
                                                </div>                                                
                                            </div>-->
                                            <!--<div id="canvas_3">-->
                                            <canvas class="chart chartjs-render-monitor" id="lineChart_3" height="70" width="339" style="display: block; width: 339; height: 70px;"></canvas>
                                            <!--</div>-->
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-4">
                                    <div class="card text-white bg-dark">
                                        <div class="card-body pb-0">
                                            <div id="lineChart_title_4" class="text-value"></div>
                                            <!--<div id="lineChart_des_1">Peso Ultima Evaluacion 19-06-2019</div>-->
                                            <div id="lineChart_des_4"></div>
                                        </div>
                                        <div id="canvas_4" class="chart-wrapper mt-3 mx-3" style="height:80px;">
<!--                                             <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                                               <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0">

                                                    </div>

                                                </div>
                                                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:200%;height:200%;left:0; top:0">                                                    
                                                    </div>                                                    
                                                </div>                                                
                                            </div>-->
                                            <!--<div id="canvas_4">-->
                                            <canvas class="chart chartjs-render-monitor" id="lineChart_4" height="70" width="339" style="display: block; width: 339; height: 70px;"></canvas>
                                            <!--</div>-->
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-4">
                                    <div class="card text-white bg-dark">
                                        <div class="card-body pb-0">
                                            <div id="lineChart_title_5" class="text-value"></div>
                                            <!--<div id="lineChart_des_1">Peso Ultima Evaluacion 19-06-2019</div>-->
                                            <div id="lineChart_des_5"></div>
                                        </div>
                                        <div id="canvas_5" class="chart-wrapper mt-3 mx-3" style="height:80px;">
<!--                                            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                                                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0">

                                                    </div>

                                                </div>
                                                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                                    <div style="position:absolute;width:200%;height:200%;left:0; top:0">                                                    
                                                    </div>                                                    
                                                </div>                                                
                                            </div>-->
<!--                                            <span id="canvas_5">-->
                                            <canvas class="chart chartjs-render-monitor" id="lineChart_5" height="70" width="339" style="display: block; width: 339; height: 70px;"></canvas>
                                            <!--</span>-->
                                        </div>
                                    </div>
                                </div>


                            </div>

                            <div class="space-12"></div>

                            <!-- FIN Evaluaciones DashBoard -->
                        </div><!-- /#feed -->

                        <div id="evaReg" class="eva tab-pane">


                            <!-- Evaluaciones Registros -->
                            <!--registros-->
                            <%--<%@include file="../Farmacos/farmacos.jsp" %>--%>
                            <!--                            <table class="table table-responsive-sm table-hover table-outline mb-0">
                                                            <thead class="thead-light">
                                                                <tr>
                                                                    <th class="text-center" style="width: 20%;">
                                                                        Evaluación
                                                                    </th>
                                                                    <th>Doctor</th>
                                                                    <th class="text-center" style="width:10%;">Fecha</th>
                                                                    <th class="text-center" style="width:10%;">Talla</th>
                                                                    <th class="text-center" style="width:10%;">Peso</th>
                                                                    <th class="text-center" style="width:10%;" >Fatv</th>
                                                                    <th class="text-center" style="width:10%;">Fat</th>
                                                                    <th class="text-center" style="width:10%;">Musc</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <div><i class="fa fa-user-md"></i></i> Yiorgos Avraamu</div>
                                                                        <hr>
                                                                        <div class="small text-muted">
                                                                            <i class="fa fa-commenting"></i>
                                                                            <span><strong>Comentario:</strong></span><br> Primera Evaluacion Primera Evaluacion Primera Evaluacion Primera Evaluacion</div>
                                                                    </td>
                                                                    <td class="text-center">
                                                                        18-06-2019
                                                                    </td>
                                                                    <td class="text-center">
                                                                        178 cm
                                                                    </td>
                                                                    <td class="text-center">
                                                                        75 Kg
                                                                    </td>
                                                                    <td class="text-center">
                                                                        2
                                                                    </td>
                                                                    <td>
                                                                        <div class="clearfix">
                                                                            <div class="float-center text-center">
                                                                                <strong>60%</strong>
                                                                            </div>
                                                                        </div>
                                                                        <div class="progress progress-xs">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="clearfix">
                                                                            <div class="float-center text-center">
                                                                                <strong>60%</strong>
                                                                            </div>
                                                                        </div>
                                                                        <div class="progress progress-xs">
                                                                            <div class="progress-bar bg-success" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>-->










                            <!-- FIN Evaluaciones Registros -->
                        </div>

                        <div class="hr hr10 hr-double"></div>


                    </div>


                </div>
            </div>









        </div>

    </div><!-- /.col -->

</div><!-- /.row -->
