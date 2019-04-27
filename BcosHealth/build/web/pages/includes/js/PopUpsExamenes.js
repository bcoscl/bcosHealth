/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $("#fileuploadExamen").click(function () {
        $("#upload-file-examen").click();
    });


    $("#modal_addAtbuttonExamenes").click(function () {

        var id = ($(this).parent().parent().find("form")).attr("id");
        id = "#" + id;

        if (validationform(id)) {
            var nombre = $("#modal_input_examenes_pacienteName").html();
            nombre = nombre.replace("<span>", "");
            nombre = nombre.replace("</span>", "");
            nombre = nombre.replace("Paciente :", "");
            nombre = $.trim(nombre);

            var ACCION = $('#modal_input_examenes_accion').val();
            var URL = "";
            if (ACCION == "MOD") {
                ACCION = "PUP-UPDATE-EXAMENES";
                URL = "../../ServletUpdateExamenes";
            } else if (ACCION == "NEW") {
                ACCION = "PUP-EXAMENES-CREATE";
                URL = "../../ServletCrearExamenes";
            }

            $.ajax({
                url: URL,
                dataType: "text",
                data: {
                    accion: ACCION,
                    row: $("#modal_input_examenes_id").val(),
                    examen_name: $("#modal_input_examenes_nombre").val(),
                    examen_obs: $("#modal_input_examenes_observacion").val(),
                    examen_pacientename: nombre
                            //url:$("#upload-file-examen").val()
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
                    CargaInicialExamenes();
                    $('#largeModalExamen').modal('hide');
                    //alert('Insert OK');
                    $.unblockUI();
                    cleanExamenes();
                    SuccesNotify();
                    //$("#contenido").html(data);


                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#largeModalExamen').modal('hide');
                    $.unblockUI();
                    cleanExamenes();
                    //$("#contenido").removeAttr('style');
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


function popupAddExamenes() {
    cleanExamenes();

    var numuser = $("#input_rut").html();
    numuser = numuser.replace("<span>", "");
    numuser = numuser.replace("</span>", "");
    numuser = $.trim(numuser);

    $('#modal_examenes_instruccion').html("Ingrese los datos del Examen");
    $('#modal_input_examenes_pacienteName').html("Paciente : " + $("#input_nombre").html());
    $('#modal_input_examenes_accion').val("NEW");
    $('#modal_input_examenes_numuserpaciente').val(numuser);

    $('#largeModalExamen').modal('toggle');



}


function popupEditExamenes(nombre, observacion, id) {

    $('#modal_input_examenes_pacienteName').html("Paciente : " + $("#input_nombre").html());

    $("#modal_input_examenes_nombre").val(nombre);
    $("#modal_input_examenes_observacion").val(observacion);
    $("#modal_input_examenes_id").val(id);

    $('#modal_examenes_instruccion').html("Ingrese las modificaciones");
    $('#largeModalExamen').modal('toggle');
    $('#modal_input_examenes_accion').val("MOD");



}

function popupDeleteExamenes(id) {


    $.ajax({
        url: "../../ServletUpdateExamenes",
        dataType: "text",
        data: {
            accion: "PUP-DELETE-EXAMEN",
            row: id
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
            CargaInicialExamenes();
            //alert('Insert OK');
            cleanExamenes();
            $.unblockUI();
            SuccesNotify();
            //$("#contenido").html(data);


        },
        error: function (jqXHR, textStatus, errorThrown) {

            $.unblockUI();
            cleanExamenes();
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

}

function popupVerExamenes(id) {

    window.open("https://www.uaeh.edu.mx/garzamun/docs/comite/FEM%20(final).pdf");

}

function cleanExamenes() {

    $("#modal_input_examenes_id").val("");
    $("#modal_input_examenes_accion").val("");
    $("#modal_input_examenes_numuserpaciente").val("");
    $("#modal_input_examenes_nombre").val("");
    $("#modal_input_examenes_observacion").val("");
    $("#fileuploadExamen").val("");

}





