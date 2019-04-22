/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {


    $("#modal_addAtbutton_farmaco").click(function () {

        var ACCION = $('#modal_input_farmaco_accion').val();
        var URL = "";
        if (ACCION == "MOD") {
            ACCION = "PUP-UPDATE-FARMACO";
            URL = "../../ServletUpdateFarmacos";
        } else if (ACCION == "NEW") {
            ACCION = "PUP-FARMACO-CREATE";
            URL = "../../ServletCrearFarmacos";
        }

        $.ajax({
            url: URL,
            dataType: "text",
            data: {
                accion: ACCION,
                row: $("#modal_input_farmaco_id").val(),
                farmaco_name: $("#modal_input_farmaco_nombre").val(),
                farmaco_obs: $("#modal_input_farmaco_observacion").val(),
                Paciente: $("#modal_input_farmaco_numuserpaciente").val()
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
                CargaInicialFarmacos();
                $('#largeModalFarmaco').modal('hide');
                //alert('Insert OK');
                $.unblockUI();
                cleanFarmacos();
                //$("#contenido").html(data);


            },
            error: function (jqXHR, textStatus, errorThrown) {

                $.unblockUI();
                cleanFarmacos();
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


});


function popupAddFarmacos() {
    cleanFarmacos();


    var numuser = $("#input_rut").html();
    numuser = numuser.replace("<span>", "");
    numuser = numuser.replace("</span>", "");
    numuser = $.trim(numuser);

    $('#modal_farmaco_instruccion').html("Ingrese los datos de los Farmacos");
    $('#modal_input_farmaco_pacienteName').html("Paciente : " + $("#input_nombre").html());
    $('#modal_input_farmaco_accion').val("NEW");
    $('#modal_input_farmaco_numuserpaciente').val(numuser);

    $('#largeModalFarmaco').modal('toggle');

}


function popupEditFarmacos(nombre, observacion, id) {

    $('#modal_input_farmaco_pacienteName').html("Paciente : " + $("#input_nombre").html());

    $("#modal_input_farmaco_nombre").val(nombre);
    $("#modal_input_farmaco_observacion").val(observacion);
    $("#modal_input_farmaco_id").val(id);
    $('#modal_input_farmaco_accion').val("MOD");

    $('#modal_farmaco_instruccion').html("Ingrese las modificaciones");
    $('#largeModalFarmaco').modal('toggle');

}

function popupDeleteFarmacos(id) {


    $.ajax({
        url: "../../ServletUpdateFarmacos",
        dataType: "text",
        data: {
            accion: "PUP-DELETE-FARMACO",
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
            CargaInicialFarmacos();
            //alert('Insert OK');
            cleanFarmacos();
            $.unblockUI();
            //$("#contenido").html(data);


        },
        error: function (jqXHR, textStatus, errorThrown) {

            $.unblockUI();
            cleanFarmacos();
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

}

function cleanFarmacos() {

    $("#modal_input_farmaco_id").val("");
    $("#modal_input_farmaco_accion").val("");
    $("#modal_input_farmaco_numuserpaciente").val("");
    $("#modal_input_farmaco_nombre").val("");
    $("#modal_input_farmaco_observacion").val("");

}





