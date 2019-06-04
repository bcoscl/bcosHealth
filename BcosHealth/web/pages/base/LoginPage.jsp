

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/png" href="../../comun/img/favicon.ico" />

        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="Expires" content="-1" />
        <meta http-equiv="Cache-Control" content="private" />
        <meta http-equiv="Cache-Control" content="no-store" />
        <meta http-equiv="Pragma" content="no-cache" />

        <title>GCM - Bcos</title>
        
        <!-- Icons-->
        <link href="../../comun/css/padding.css" rel="stylesheet">   
        <link href="../../comun/css/coreui-icons.min.css" rel="stylesheet">
        <link href="../../comun/css/flag-icon.min.css" rel="stylesheet">
        <link href="../../comun/css/font-awesome.min.css" rel="stylesheet">
        <link href="../../comun/css/simple-line-icons.css" rel="stylesheet">
        <!-- Main styles for this application-->
        <link href="../../comun/css/style.css" rel="stylesheet">
        <link href="../../comun/css/pace.min.css" rel="stylesheet">  
        <link href="../../comun/css/load.css" rel="stylesheet">  
        <link rel="stylesheet" href="../../comun/css/bootstrap-select.min.css">
        <link rel="stylesheet" href="../../comun/css/animate.css">


        <!-- CoreUI and necessary plugins-->
        <script src="../../comun/js/jquery.min.js"></script>
        <script src="../../comun/js/popper.min.js"></script>
        <script src="../../comun/js/bootstrap.min.js"></script>
        <script src="../../comun/js/bootstrap-select.min.js"></script>

        <script src="../../comun/js/pace.min.js"></script>
        <script src="../../comun/js/perfect-scrollbar.min.js"></script>
        <script src="../../comun/js/coreui.min.js"></script>
        <!-- Plugins and scripts required by this view-->
        <script src="../../comun/js/Chart.min.js"></script>
        <script src="../../comun/js/custom-tooltips.min.js"></script>
        <script src="../../comun/js/main.js"></script>
        <script src="../../comun/js/jquery.blockUI.js"></script>
        <script src="../../comun/js/formValidation.js"></script>
        <script src="../../comun/js/bootstrap-notify.min.js"></script>


        <link rel="stylesheet" href="../../comun/css/all.css" >
        <link rel="stylesheet" href="../../comun/css/LoginPage.css">

        <script src="../../comun/js/LoginPage.js"></script>





    </head>
    <body>
        <!DOCTYPE html>
    <html>

        <head>
            <title>Login Page</title>

        </head>
        <!--Coded with love by Mutiullah Samim-->
        <body>
            <div class="container h-100">
                <div class="d-flex justify-content-center h-100">
                    <div class="user_card">
                        <div class="card-body">
                            <div class="d-flex justify-content-center">
                                <div class="brand_logo_container">
                                    <img src="../../comun/img/201392014219666.jpg" class="brand_logo" alt="Logo">
                                </div>
                            </div>

                            <div class="form_container">
                                <form action="../../ServletLogin?accion=Auth" method="post" id="form1">
                                    <div class="row justify-content-center">
                                        <div class="col-11 mt-2 text-center">
                                            <div class="input-group mb-3">
                                                <div class="input-group-append">
                                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                                </div>
                                                <input type="text" id="username" name="username" maxlength="10" class="form-control input_user" value="" placeholder="username" required="true">
                                            </div>

                                            <div class="input-group mb-2">
                                                <div class="input-group-append">
                                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                                </div>
                                                <input type="password" id="password" name="password" maxlength="10" class="form-control input_pass" value="" placeholder="password" required="true">
                                            </div>
                                        </div>
                                    </div>

                                    <!--<div class="form-group">
                                            <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customControlInline">
                                                    <label class="custom-control-label" for="customControlInline">Remember me</label>
                                            </div>
                                    </div>-->
                                </form>
                                <div class="row justify-content-center">
                                    <div class="col-11 mt-2 text-center">
                                        <div class="justify-content-center mt-3">
                                            <button form="form1" id="LoginButton" type="submit" name="button" class="btn login_btn">Login</button>
                                        </div>
                                    </div>

                                    <div class="col-12 mt-4">
                                        <div class="d-flex justify-content-center links">
                                            <!--                                No tienes una cuenta ! contacta a tu Administrador<a href="#" class="ml-2">contactate a tu Administrador</a>-->
                                        </div>
                                        <div class="d-flex justify-content-center links">
                                            <a href="javascript:Olvido();">¿Olvidaste tu Password?</a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </html>


    <%@include file="../includes/popups/PopUpsOlvido.jsp" %>
    <%@include file="../includes/message/message.jsp" %>
</body>

</html>
