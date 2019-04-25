/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {


        $.ajax({
            url: "../../ServletListarMenu",
            dataType: "text",
            data: {
                accion: "MENU"
            },
            beforeSend: function () {

                $.blockUI({message: $('#load'), css: {
                        padding: 0,
                        margin: 0,
                        width: '35%',
                        top: '35%',
                        left: '35%',
                        textAlign: 'center',
                        color: '#c8ced300',
                        border: '0px',
                        backgroundColor: '#c8ced300',
                        cursor: 'wait'
                    }});
            },

            success: function (data) {
                var valor = data.split("|");
                $.unblockUI();
                $("#usuarioSession_header").html(valor[1]);
                $("#usuarioSession_header2").html(valor[1]);
                $("#menuContent").html(valor[0]);
               //$("#menuContent").addClass("sidebar");

            },
            error: function (jqXHR, textStatus, errorThrown) {

                $.unblockUI();
               
                //$("#contenido").removeAttr('style');
                $("#msgResultError").removeClass('fade show-none');
                setTimeout(function () {
                    $("#msgResult").fadeOut(1000);
                    $("#msgResultError").addClass('fade show-none');
                }, 2000);


                if (jqXHR.status == 500) {
                    // Server side error
                    mensaje = " Error server side - status : " + jqXHR.status;
                } else if (jqXHR.status == 404) {
                    mensaje = " Sitio not found - status : " + jqXHR.status;
                } else if (jqXHR.status == 401) {
                    location.href = "../../pages/base/sorry.html";
                } else {
                    mensaje = " - status : " + jqXHR.status;

                }


            }
        });
        
        
    });



