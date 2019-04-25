/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


//envio de informacion

    $("#submitButton").click(function (e) {

        $.ajax({
            url: "../../ServletCrearConfiguraciones",
            dataType: "text",
            data: {
                accion: "IC",
                params_grupo: $("#grupo").val(),
                params_subgrupo: $("#subgrupo").val(),
                params_param1: $("#param1").val(),
                params_param2: $("#param2").val(),
                params_param3: $("#param3").val(),
                params_param4: $("#param4").val()
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

                
                $.unblockUI();                
                $("#msgResult").removeAttr('style');
                $("#msgResult").removeClass('fade show-none');
                setTimeout(function () {
                    $("#msgResult").fadeOut(500);
                    $("#msgResult").addClass('fade show-none');
                }, 2000);

            },
            error: function (jqXHR, textStatus, errorThrown) {
                
                var mensaje;

                $.unblockUI();
                $("#msgResultError").removeAttr('style');
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




});




