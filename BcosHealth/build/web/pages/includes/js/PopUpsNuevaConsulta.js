/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $(".slidedown").hide();

    $(".slide").click(function () {
        if ($(".slidedown").is(":hidden")) {
            $(".slidedown").slideDown("slow");
        } else {
            $(".slidedown").hide();
        }
    });


    $("#modal_addAtbutton_nueva_consulta").click(function () {

        var ACCION = $("#modal_input_consulta_accion").val();
        var URL = "";
        if (ACCION == "MOD") {
            ACCION = "PUP-UPDATE-CONSULTA";
            URL = "../../ServletUpdateConsultas";
        } else if (ACCION == "NEW") {
            ACCION = "PUP-CONSULTA-CREATE";
            URL = "../../ServletCrearConsulta";
        }

        var id = ($(this).parent().parent().find("form")).attr("id");
        id = "#" + id;

        if (validationform(id)) {

            $.ajax({
                url: URL,
                dataType: "text",
                data: {
                    accion: ACCION,
                    consulta_titulo: $("#modal_input_consulta_titulo").val(),
                    consulta_fecha: $("#modal_input_consulta_fecha").val(),
                    consulta_hora: $("#modal_input_consulta_hora").val(),
                    consulta_obs: $("#modal_input_consulta_observacion").val(),
                    consult_paciente_name: $("#modal_input_consulta_pacientename").val(),
                    row: $("#modal_input_consulta_id").val()

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
                    CargaConsultas();
                    removeValidation(id);
                    $("#primaryModalConsulta").modal('hide');
                    //alert('Insert OK');
                    $.unblockUI();
                    cleanConsulta();
                    SuccesNotify();
                    //$("#contenido").html(data);


                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $("#primaryModalConsulta").modal('hide');
                    $.unblockUI();
                    cleanConsulta();
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


function nuevaConsulta() {
    cleanConsulta();
    removeValidation("#formnuevaconsulta");
    
    var numuser = $("#input_nombre").html();
    numuser = numuser.replace("<span>", "");
    numuser = numuser.replace("</span>", "");
    numuser = $.trim(numuser);

    $(".slidedown").hide();
    $("#modal_consulta_instruccion").html("Ingrese la Consulta");
    $("#modal_input_consulta_pacienteName").html("Paciente : " + $("#input_nombre").html());
    $("#modal_input_consulta_fecha").val("");
    $("#modal_input_consulta_hora").val("");
    $("#modal_input_consulta_pacientename").val(numuser);
    $("#modal_input_consulta_accion").val("NEW");

    $("#primaryModalConsulta").modal('toggle');

}
function cleanConsulta() {

    $("#modal_input_consulta_fecha").val("");
    $("#modal_input_consulta_hora").val("");
    $("#modal_input_consulta_titulo").val("");
    $("#modal_input_consulta_observacion").val("");


}

function popupEditConsulta(titulo, observacion, id, nombre) {
    cleanConsulta();
    // alert("titulo:"+titulo+" - observacion:"+observacion+" - id:"+id+" - nombre:"+nombre);
    $("#modal_input_consulta_pacienteName").html("Paciente : " + $("#input_nombre").html());

    $("#modal_input_consulta_pacientename").val(nombre);
    $("#modal_input_consulta_titulo").val(titulo);
        
        
        while(observacion.indexOf(" - ") != -1) {
        observacion = observacion.replace(" - ","\n");
        }
        
        
    $("#modal_input_consulta_observacion").val(observacion);
    $("#modal_input_consulta_id").val(id);
    $("#modal_input_consulta_accion").val("MOD");

    $("#modal_consulta_instruccion").html("Ingrese las modificaciones");
    $("#primaryModalConsulta").modal('toggle');

}

function popupDeleteConsulta(id) {


    $.ajax({
        url: "../../ServletUpdateConsultas",
        dataType: "text",
        data: {
            accion: "PUP-DELETE-CONSULTA",
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
            CargaConsultas();
            //alert('Insert OK');
            cleanConsulta();
            $.unblockUI();
            //$("#contenido").html(data);
            SuccesNotify();

        },
        error: function (jqXHR, textStatus, errorThrown) {

            $.unblockUI();
            cleanConsulta();
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



