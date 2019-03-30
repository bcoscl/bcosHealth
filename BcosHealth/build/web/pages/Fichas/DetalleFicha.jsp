<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<!-- profile -->



<div id="user-profile-2" class="user-profile">
    <div class="tabbable">
        <ul class="nav nav-tabs padding-18">
            <li class="active">
                <a data-toggle="tab" href="#home">
                    <i class="blue ace-icon fa fa-user bigger-120"></i>
                    Profile
                </a>
            </li>

            <li>
                <a data-toggle="tab" href="#feed">
                    <i class="ace-icon fa fas fa-heartbeat bigger-120" style="color: #ff2600"></i>
                    Enfermedades Cronicas
                </a>
            </li>

            <li>
                <a data-toggle="tab" href="#friends">
                    <i class="ace-icon fa  fa-flask bigger-120" style="color: #00b50e"></i>
                    Farmacos
                </a>

            </li>


        </ul>

        <div class="tab-content no-border padding-24">

            <!-- profile -->
            <%@include file="../Perfil/profile.jsp" %>

            <!-- fin profile -->



            <div id="feed" class="tab-pane">
                <div class="profile-feed row">
                    <div class="col-sm-12">
                        <div class="profile-activity clearfix">
                            <div>
                                <!--<i class="ace-icon fa fa-heartbeat bigger-120" style="color: #ff2600"></i>-->

                                <img class="pull-left" alt="Alex Doe's avatar" src="../Consultas/img/enfermedad-cronica.png" with="40px" height="40px">
                                <a class="user" href="#"> Hipertension </a>


                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>

                            <!--                            <div class="tools action-buttons">
                                                            <a href="#" class="blue">
                                                                <i class="ace-icon fa fa-pencil bigger-125"></i>
                                                            </a>
                            
                                                            <a href="#" class="red">
                                                                <i class="ace-icon fa fa-times bigger-125"></i>
                                                            </a>
                                                        </div>-->
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/enfermedad-cronica.png" with="40px" height="40px">
                                <a class="user" href="#"> Cáncer </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/enfermedad-cronica.png" with="40px" height="40px">
                                <a class="user" href="#"> Diabetes </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>
                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/enfermedad-cronica.png" with="40px" height="40px">
                                <a class="user" href="#"> Parkinson </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>
                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/enfermedad-cronica.png" with="40px" height="40px">
                                <a class="user" href="#"> Alzheimer </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>


                    </div><!-- /.col -->

                </div><!-- /.row -->

                <div class="space-12"></div>


            </div><!-- /#feed -->

            <div id="friends" class="tab-pane">


                <!-- FARMACOS -->


                <div class="profile-feed row">
                    <div class="col-sm-12">
                        <div class="profile-activity clearfix">
                            <div>
                                <!--<i class="ace-icon fa fa-heartbeat bigger-120" style="color: #ff2600"></i>-->

                                <img class="pull-left" alt="Alex Doe's avatar" src="../Consultas/img/farmaco.png" with="40px" height="40px">
                                <a class="user" href="#"> Simvastatina - para controlar el colesterol </a>


                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>

                            <!--                            <div class="tools action-buttons">
                                                            <a href="#" class="blue">
                                                                <i class="ace-icon fa fa-pencil bigger-125"></i>
                                                            </a>
                            
                                                            <a href="#" class="red">
                                                                <i class="ace-icon fa fa-times bigger-125"></i>
                                                            </a>
                                                        </div>-->
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/farmaco.png" with="40px" height="40px">
                                <a class="user" href="#"> Aspirina </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/farmaco.png" with="40px" height="40px">
                                <a class="user" href="#"> Omeprazol - para la acidez de estómago </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>
                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/farmaco.png" with="40px" height="40px">
                                <a class="user" href="#"> Lexotiroxina sódica - para reemplazar la tiroxina </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>
                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" src="../Consultas/img/farmaco.png" with="40px" height="40px">
                                <a class="user" href="#"> Ramipril - para la hipertensión </a>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    registro : 2019/03/29
                                </div>
                            </div>


                        </div>


                    </div><!-- /.col -->

                </div><!-- /.row -->





                <!-- FIN FARMACOS -->
            </div>

            <div class="hr hr10 hr-double"></div>


        </div><!-- /#friends -->


    </div>
</div>





<!-- fin Profile -->


