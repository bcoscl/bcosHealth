/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {


    $("#modal_addAtbutton_cronica").click(function () {

        var ACCION = $('#modal_input_cronica_accion').val();
        var URL = "";
        if (ACCION == "MOD") {
            ACCION = "PUP-UPDATE-CRONICA";
            URL = "../../ServletUpdateEnfermedadesCronicas";
        } else if (ACCION == "NEW") {
            ACCION = "PUP-CRONICA-CREATE";
            URL = "../../ServletCrearEnfermedadesCronicas";
        }

        $.ajax({
            url: URL,
            dataType: "text",
            data: {
                accion: ACCION,
                row: $("#modal_input_croncia_id").val(),
                cronica_name: $("#modal_input_croncia_nombre").val(),
                cronica_obs: $("#modal_input_cronica_observacion").val()
//                ,
//                Paciente: $("#modal_input_cronica_numuserpaciente").val()
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
                CargaInicialCronica();
                $('#largeModalCronica').modal('hide');
                //alert('Insert OK');
                $.unblockUI();
                cleanCronica();
                //$("#contenido").html(data);


            },
            error: function (jqXHR, textStatus, errorThrown) {

                $.unblockUI();
                cleanCronica();
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


function popupAddCronica() {
    cleanCronica();

//    var numuser = $("#input_rut").html();
//    numuser = numuser.replace("<span>", "");
//    numuser = numuser.replace("</span>", "");
//    numuser = $.trim(numuser);

    $('#modal_cronica_instruccion').html("Ingrese los datos de la Enfermedad");
    $('#modal_input_cronica_pacienteName').html("Paciente : " + $("#input_nombre").html());
    $('#modal_input_cronica_accion').val("NEW");
    //$('#modal_input_cronica_numuserpaciente').val(numuser);



    $('#largeModalCronica').modal('toggle');

}


function popupEditCronica(nombre, observacion, id) {

    $('#modal_input_cronica_pacienteName').html("Paciente : " + $("#input_nombre").html());
    $('#modal_cronica_instruccion').html("Ingrese las modificaciones");

    $("#modal_input_croncia_nombre").val(nombre);
    $("#modal_input_cronica_observacion").val(observacion);
    $("#modal_input_croncia_id").val(id);
    $('#modal_input_cronica_accion').val("MOD");



    $('#largeModalCronica').modal('toggle');

}
function popupDeleteCronica(id) {

    $.ajax({
        url: "../../ServletUpdateEnfermedadesCronicas",
        dataType: "text",
        data: {
            accion: "PUP-DELETE-CRONICA",
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
            CargaInicialCronica();
            //alert('Insert OK');
            cleanCronica();
            $.unblockUI();
            //$("#contenido").html(data);


        },
        error: function (jqXHR, textStatus, errorThrown) {

            $.unblockUI();
            cleanCronica();
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


function cleanCronica() {

    $("#modal_input_croncia_id").val("");
    $("#modal_input_cronica_accion").val("");
    $("#modal_input_cronica_numuserpaciente").val("");
    $("#modal_input_croncia_nombre").val("");
    $("#modal_input_cronica_observacion").val("");

}









