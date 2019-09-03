/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {





    $("#modal_addAtbutton_eva").click(function () {

        

        var ACCION = $('#modal_input_eva_accion').val();
        var URL = "";
        if (ACCION == "MOD") {
            ACCION = "PUP-UPDATE-EVA";
            URL = "../../ServletUpdateEvaluacion";
        } else if (ACCION == "NEW") {
            ACCION = "PUP-EVA-CREATE";
            URL = "../../ServletCrearEvaluacion";
        }

        var id = ($(this).parent().parent().find("form")).attr("id");
        id = "#" + id;

        if (validationform(id)) {
            
            $.ajax({
                url: URL,
                dataType: "text",
                data: {
                    accion: ACCION,
                    row: $("#modal_input_eva_id").val(),
                    //eva_paciente: $("#modal_input_eva_numuserpaciente").val(),
                    eva_fecha: $("#modal_input_eva_fecha").val(),
                    eva_talla: $("#modal_input_eva_talla").val(),
                    eva_peso: $("#modal_input_eva_peso").val(),
                    eva_fat: $("#modal_input_eva_fat").val(),
                    eva_fatv: $("#modal_input_eva_fatv").val(),
                    eva_musc: $("#modal_input_eva_musc").val(),
                    eva_obs: $("#modal_input_eva_observacion").val(),
                    eva_imc: $("#modal_input_eva_imc").val()
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
                    CargaInicialeva();
                    removeValidation(id);
                    $('#largeModaleva').modal('hide');
                    
                    //alert('Insert OK');
                    $.unblockUI();
                    cleaneva();
                    SuccesNotify();
                    //window.location.reload();
                    //$("#contenido").html(data);


                },
                error: function (jqXHR, textStatus, errorThrown) {

                    $.unblockUI();
                    cleaneva();
                    $('#largeModaleva').modal('hide');
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





function nuevaeva() {
    cleaneva();
    removeValidation("#formeva");
    $(".fechaRow").prop( "disabled", false );
    $(".fechaRow").show();
    
//    var numuser = $("#input_rut").html();
//    numuser = numuser.replace("<span>", "");
//    numuser = numuser.replace("</span>", "");
//    numuser = $.trim(numuser);

    $('#modal_eva_instruccion').html("Ingrese los datos de la Evaluación");
    $('#modal_input_eva_pacienteName').html("Paciente : " + $("#input_nombre").html());
    $('#modal_input_eva_accion').val("NEW");
    //$('#modal_input_eva_numuserpaciente').val(numuser);

    $('#largeModaleva').modal('toggle');

}


function popupEditeva( observacion, id,fecha,talla,peso,fat,fatv,musc,imc) {

    $('#modal_input_eva_pacienteName').html("Paciente : " + $("#input_nombre").html());
    $('#modal_eva_instruccion').html("Ingrese las modificaciones");

    $("#modal_input_eva_nombre").val($("#input_nombre").html());
    
    $("#modal_input_eva_id").val(id);
    $('#modal_input_eva_accion').val("MOD");
    
    //$("#modal_input_eva_numuserpaciente").val(numuser);    
    //$("#modal_input_eva_fecha").hide();
    $(".fechaRow").prop( "disabled", true );
    $(".fechaRow").hide();
    
        while(observacion.indexOf(" - ") != -1) {
        observacion = observacion.replace(" - ","\n");
        }
    
    $("#modal_input_eva_talla").val(talla);
    $("#modal_input_eva_peso").val(peso);
    $("#modal_input_eva_fat").val(fat);
    $("#modal_input_eva_fatv").val(fatv);
    $("#modal_input_eva_musc").val(musc);
    $("#modal_input_eva_observacion").val(observacion);
    $("#modal_input_eva_imc").val(imc);
    

    $('#largeModaleva').modal('toggle');

}

function popupDeleteEva(id) {

    $.ajax({
        url: "../../ServletUpdateEvaluacion",
        dataType: "text",
        data: {
            accion: "PUP-DELETE-EVA",
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
            CargaInicialeva();
            //alert('Insert OK');
            cleaneva();
            $.unblockUI();
            SuccesNotify();
            //$("#contenido").html(data);


        },
        error: function (jqXHR, textStatus, errorThrown) {

            $.unblockUI();
            cleaneva();
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


function cleaneva() {

    $("#modal_input_eva_id").val("");
    $("#modal_input_eva_accion").val("");
    $("#modal_input_eva_numuserpaciente").val("");
    
    $("#modal_input_eva_fecha").val("");
    $("#modal_input_eva_talla").val("");
    $("#modal_input_eva_peso").val("");
    $("#modal_input_eva_fat").val("");
    $("#modal_input_eva_fatv").val("");
    $("#modal_input_eva_musc").val("");
    $("#modal_input_eva_observacion").val("");
    $("#modal_input_eva_imc").val("");
    

}









