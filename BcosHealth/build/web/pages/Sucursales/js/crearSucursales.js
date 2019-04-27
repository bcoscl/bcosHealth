/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {



    $("#checkbox_activo").attr('checked', true);

    $.ajax({
        url: "../../ServletListarSuscripciones",
        dataType: "text",
        data: {
            accion: "LS-SELECT-BY-EMPRESA"
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
            $("#select_empresa").html(data);
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
        //alert(id);
        if (validationform(id)) {

            $.ajax({
                url: "../../ServletCrearSucursales",
                dataType: "text",
                data: {
                    accion: "IS",
                    nombre_sucursal: $("#nombre_sucursal").val(),
                    comuna_sucursal: $("#comuna_sucursal").val(),
                    numero_telefono: $("#numero_telefono").val(),
                    correo_sucursal: $("#correo_sucursal").val(),
                    contacto_sucursal: $("#contacto_sucursal").val(),
                    select_empresa: $("#select_empresa option:selected").val(),
                    select_empresa_name: $("#select_empresa option:selected").text(),
                    checkbox_activo: $("#checkbox_activo").is(":checked"),

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




