<%-- 
    Document   : encuesta.jsp
    Created on : 18-07-2019, 21:58:50
    Author     : aacantero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <base href="./">
        <!--<meta charset="utf-8">-->
        <!-- CoreUI and necessary plugins-->
        <script src="../../comun/js/jquery.min.js"></script>

    </head>
    <body>



        <script type="text/javascript">var submitted = false;</script>
        <iframe name="hidden_iframe" 
                id="hidden_iframe" 
                style="display:none;" 
                onload="if (submitted) {
                          alert(' Encuesta enviada ...');
                         window.location = 'http://localhost:8080/BcosHealth/pages/Encuesta/encuesta.jsp';
                            //window.location = 'https://www.youtube.com';
                        }">

        </iframe>



        <div class="row" style="padding:40px;">
            <p>Just paste your google form URL into the action attribute and you will have a working custom form that you can style as you'd like!</p>
            <br/>
            <form 
                action="https://docs.google.com/forms/d/e/1FAIpQLSfYzqGriJLvrcGl1fsyNiijTRZZsPOuFfR3miNNh_XYefuGeA/formResponse" 
                method="post" target="hidden_iframe" id="application-form" onsubmit="submitted = true;" >
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group">

                            <input type="text" name="entry.1959246129" value="" class="form-control name" id="entry_2005620554" dir="auto" aria-label="Name  " aria-required="true" placeholder="Name">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group">
                            <input type="text" name="emailAddress" value="" class="form-control email" id="entry_1045781291" dir="auto" aria-label="Email  " aria-required="true" placeholder="Email">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group">
                            <input type="text" name="entry.2145018458" value="" class="form-control phone" id="entry_1166974658" dir="auto" aria-label="Message  " aria-required="true" placeholder="Message">
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="form-group">
                        <!--<input type="button" id="submit" class="btn btn-custom" name="submit" value="Submit Test Form">-->
                        <input  type="submit" value="Submit request" />
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
