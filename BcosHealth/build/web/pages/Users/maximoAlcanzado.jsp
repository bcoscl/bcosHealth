<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../includes/header.jsp" %>


<title>Bcos Health - Suscripción Agotada</title>

<!--<script src="./js/crearUsers.js"></script>-->

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">

<link rel="stylesheet" href="./css/maximoAlcanzado.css">

<%@include file="../includes/body.jsp" %>


<!-- contenido -->


<body class="app flex-row align-items-center" >

    <div class="container">



        <div class="row justify-content-center">

            <img src="./img/sorry.jpg" class="brand_logo" alt="Logo">               


            <div class="col-md-6">
                <div class="clearfix">
                    <h2 class="float-left display-3 mr-4">Suscripción Agotada</h2>
                    <h4 class="pt-3">ya no puedes crear mas usuarios para tu plan!</h4>
                    <!--<h5 class="text-muted">Por favor ingresa nuevamente</h5>-->
                </div>


            </div>
            <div class="clearfix">
                <!--<p class="text-muted">Redireccionando en...</p>-->
            </div>
            <div>
                <div class="float-center display-3 mr-4">
                    <!--<label id="display">-</label>-->

                </div>

            </div>

        </div>

    </div>
    <div class="card hr-800"></div>
    
    <div class="row justify-content-center">
        <div class="col-xs-12 col-sm-8">
            <div class="widget-box transparent">
                
                <div class="widget-body">
                    <div class="widget-main" id="input_aboutMe">
                        <p>Puedes contactar a tu administrador para aumentar la capacidad de
                            tu Suscripción. <a href="mailto:contacto@bcos.cl"  target="_top">contacto@bcos.cl</a>
                        </p> 
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>




<%@include file="../includes/message/message.jsp" %>
<!-- foofter -->
<%@include file="../includes/footer.jsp" %>