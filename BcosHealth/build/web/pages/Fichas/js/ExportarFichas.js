/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {



    $.ajax({
        url: "../../ServletListarPacientes",
        dataType: "text",
        data: {
            accion: "LU-SELECT-MULTIPLE"
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
            $("#pacientes_selectmultiple_content").html(data);
            $("#pacientes_selectmultiple_content select").selectpicker({
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {

            $.unblockUI();
            //$("#contenido").removeAttr('style');
//            $("#msgResultError").removeClass('fade show-none');
//            setTimeout(function () {
//                $("#msgResult").fadeOut(1000);
//                $("#msgResultError").addClass('fade show-none');
//            }, 2000);
            DangerNotify();

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



    $("#BuscarButton").click(function (e) {


        // var id = ($(this).parent().parent().find("form")).attr("id");
        var value = $("#select_export_pacientes option:selected").text();
        //alert(value);
        if (value != "") {
            $.ajax({
                url: "../../ServletExportarFichas",
                dataType: "text",
                data: {
                    accion: "FICHAS_EXPORT",
                    fichas: value
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
                    $("#contenido").html(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {


                    $.unblockUI();
                    DangerNotify();


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
        } else {
            DangerNotify('debes ingresar los valores');

        }
    });


});



function Export() {
    var headstr = "<html><head><title></title></head><body>";
    var footstr = "</body>";
    $(".collapse").addClass("show");
    $(".hr").html("<div class=\"card hr-800\"></div>");
    var newstr = $("#accordion").html();
    var oldstr = document.body.innerHTML;
    document.body.innerHTML = headstr + newstr + footstr;
    window.print();
    document.body.innerHTML = oldstr;

    $(".hr").html("");
    $(".collapse").removeClass("show");

}





