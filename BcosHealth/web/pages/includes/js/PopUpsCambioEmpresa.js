/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $.ajax({
        url: "../../ServletListarSuscripciones",
        dataType: "text",
        data: {
            accion: "LS-SELECT"
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
            $("#select_changeEmpresa").html(data);
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



    $("#modal_cambioEmpresa").click(function () {

        $.ajax({
            url: "../../ServletChangeEmpresa",
            dataType: "text",
            data: {
                accion: "CHANGE_EMPRESA",
                empresa: $("#select_changeEmpresa option:selected").text(),
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
                SuccesNotify();
                $("#empresa_header2").html("("+$("#select_changeEmpresa option:selected").text()+")");
                $("#empresa_header").html("("+$("#select_changeEmpresa option:selected").text()+")");
                window.location.reload();
                
                $.unblockUI();
                setTimeout(function () {
                    $("#modalCambioEmpresa").modal('hide');
                }, 500);

            },
            error: function (jqXHR, textStatus, errorThrown) {
                $.unblockUI();
                DangerNotify();
                setTimeout(function () {
                    $("#modalCambioEmpresa").modal('hide');
                }, 500);
            }
        });
    });





});
