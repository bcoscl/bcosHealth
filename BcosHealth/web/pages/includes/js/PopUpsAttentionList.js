/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {


    /*Cargar lista de Medicos*/
    $.ajax({
        url: "../../ServletListarUsuarios",
        dataType: "text",
        data: {
            accion: "LU-SELECT-MED"
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

            //alert('Insert OK');
            $.unblockUI();
            $("#medico_select").html(data);
            //$("#msgResult").removeAttr('style');
            //$("#msgResult").removeClass('fade show-none');
            //setTimeout(function () {
            //    $("#msgResult").fadeOut(500);
            //    $("#msgResult").addClass('fade show-none');
            //}, 2000);

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


    $('.form-check-input').change(function () {
        if ($(this).is(':checked')) {
            var valor = $.trim($(this).parent().find("label").text());
            valor = valor.replace(" ", "");

            if (valor.toLowerCase() == "otro") {
                $("#modal_motivo").removeAttr("disabled");
                $("#modal_motivo").val("");
                $("#modal_motivo").focus();

            } else {
                $("#modal_motivo").attr("disabled", true);
                $("#modal_motivo").val($(this).parent().find("label").text());
            }
        }


    });


    /* Agregar a la lista de atencion */
    $("#modal_addAtbutton").click(function (e) {
        var id = ($(this).parent().parent().find("form")).attr("id");
        id = "#" + id;

        if (validationform(id)) {
            $.ajax({
                url: "../../ServletAddAttentionList",
                dataType: "text",
                data: {
                    accion: "CREATE",
                    modal_nombre: $("#modal_nombre").val(),
                    modal_numuser: $("#modal_numuser").val(),
                    modal_mediconumuser: $("#medico_select option:selected").val(),
                    modal_mediconombre: $("#medico_select option:selected").text(),
                    modal_motivo: $("#modal_motivo").val()
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
                    $("#msgResult").removeAttr('style');
                    $("#msgResult").removeClass('fade show-none');
                    setTimeout(function () {
                        $("#msgResult").fadeOut(500);
                        $("#msgResult").addClass('fade show-none');
                    }, 2000);
                    $('#largeModal').modal('hide');

                },
                error: function (jqXHR, textStatus, errorThrown) {

                    var mensaje;

                    $.unblockUI();
                    $('#largeModal').modal('hide');
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
        }
    });

});


function popupAttentionList(numuser, PacienteName) {

    $("#modal_nombre").val(PacienteName);
    $("#modal_numuser").val(numuser);
    $("#medico_select").val(0);

    $('#largeModal').modal('toggle');

}





