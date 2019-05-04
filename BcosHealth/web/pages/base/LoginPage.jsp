

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">

        <title>GCM - Bcos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../../comun/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../comun/css/LoginPage.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script src="../../comun/js/LoginPage.js"></script>
        <link rel="stylesheet" href="../../comun/css/all.css" >
        <script src="../../comun/js/jquery.min.js"></script>
        <script src="../../comun/js/bootstrap.min.js"></script>
        <script src="../../comun/js/formValidation.js"></script>
        <script src="../../comun/js/bootstrap-notify.min.js"></script>
        <link rel="stylesheet" href="../../comun/css/animate.css">
        <script src="../../comun/js/jquery.blockUI.js"></script>



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
                        <div class="d-flex justify-content-center">
                            <div class="brand_logo_container">
                                <img src="../../comun/img/201392014219666.jpg" class="brand_logo" alt="Logo">
                            </div>
                        </div>

                        <div class="d-flex justify-content-center form_container">
                            <form action="../../ServletLogin?accion=Auth" method="post" id="form1">

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
                                <!--<div class="form-group">
                                        <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customControlInline">
                                                <label class="custom-control-label" for="customControlInline">Remember me</label>
                                        </div>
                                </div>-->
                            </form>
                        </div>
                        <div class="d-flex justify-content-center mt-3 login_container">
                            <button form="form1" id="LoginButton" type="submit" name="button" class="btn login_btn">Login</button>
                        </div>
                        <div class="mt-4">
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
        </body>
    </html>
    <%@include file="../includes/popups/PopUpsOlvido.jsp" %>
    <%@include file="../includes/message/message.jsp" %>
</body>

</html>
