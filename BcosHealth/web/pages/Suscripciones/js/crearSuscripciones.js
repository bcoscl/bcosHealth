/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#checkbox_activo").attr('checked', true);
// carga Select mod
    $.ajax({
        url: "../../ServletListarPlanes",
        dataType: "text",
        data: {
            accion: "LP-SELECT"
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
            $("#select_plan").html(data);
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

//checkbox
    $("#checkcontroller").click(function (e) {

        if ($("#checkbox_activo").is(":checked")) {
            $("#checkbox_activo").attr('checked', false);
        } else {
            $("#checkbox_activo").attr('checked', true);
        }
        // $("#checkbox_activo :checked").attr('checked', true);

    });

//envio de informacion

    $("#submitButton").click(function (e) {

        var id = ($(this).parent().parent().find("form")).attr("id");
        id = "#" + id;

        if (validationform(id)) {

            $.ajax({
                url: "../../ServletCrearSuscripcion",
                dataType: "text",
                data: {
                    accion: "IS",
                    nombre_empresa: $("#nombre_empresa").val(),
                    contacto_empresa: $("#contacto_empresa").val(),
                    email_contacto: $("#email_contacto").val(),
                    numero_telefono: $("#numero_telefono").val(),
                    fecha_inicio: $("#fecha_inicio").val(),
                    select_plan_code: $("#select_plan option:selected").val(),
                    select_plan_name: $("#select_plan option:selected").text(),
                    checkbox_activo: $("#checkbox_activo").is(":checked")
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
                    $(id)[0].reset();

                    $.unblockUI();
//                    $("#msgResult").removeAttr('style');
//                    $("#msgResult").removeClass('fade show-none');
//                    setTimeout(function () {
//                        $("#msgResult").fadeOut(500);
//                        $("#msgResult").addClass('fade show-none');
//                    }, 2000);
                    SuccesNotify();
                },
                error: function (jqXHR, textStatus, errorThrown) {

                    var mensaje;

                    $.unblockUI();
//                    $("#msgResultError").removeAttr('style');
//                    $("#msgResultError").removeClass('fade show-none');
//                    setTimeout(function () {
//                        $("#msgResult").fadeOut(1000);
//                        $("#msgResultError").addClass('fade show-none');
//                    }, 2000);
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
        }
    });




});




