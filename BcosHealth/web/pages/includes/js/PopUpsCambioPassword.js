/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {


//$("#modalCambioPassword").modal('toggle');
    $('#modalCambioPassword').modal({backdrop: 'static', keyboard: false});

    $('#modal_nuevaPass_rep').keyup(function () {
       
        if ($("#modal_nuevaPass").val().length <= $("#modal_nuevaPass_rep").val().length) {
            if ($("#modal_nuevaPass").val() != $("#modal_nuevaPass_rep").val()) {
                DangerNotify('Las contraseñas no son iguales');
            }
        }


    });

    $("#modal_cambioPassword").click(function () {

        id = "#CambioForm";
        if ($("#modal_nuevaPass").val() == $("#modal_nuevaPass_rep").val()) {
            if (validationform(id)) {
               
                    $.ajax({
                        url: "../../ServletChangePassword",
                        dataType: "text",
                        data: {
                            accion: "CHANGE_PASS",
                            pass: $("#modal_nuevaPass").val()

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
                            $(id)[0].reset();
                            removeValidation(id);
                            

                            $.unblockUI();

                           
                            setTimeout(function () {
                                $("#modal_cambioPassword").modal('hide');
                            }, 2000);
                             setTimeout(function () {
                                window.location.href = "../base/LoginPage.jsp"; 
                            }, 2000);
                            


                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $(id)[0].reset();
                            removeValidation(id);
                            //alert(jqXHR.status)
                            $.unblockUI();
//
//                            if (jqXHR.status == 500) {
//                                DangerNotify('Tuvimos un problema de Comunicacion,<br> intentalo mas tarde');
//                            } else if (jqXHR.status == 404) {
//                                DangerNotify('tuvimos un problema de Comunicacion,<br> intentalo mas tarde');
//                            } else if (jqXHR.status == 401) {
//                                DangerNotify('Acceso no Autorizado');
//                            } else if (jqXHR.status == 400) {
//                                DangerNotify('No tenemos registro de tu email.');
//
//                            } else {
//                                mensaje = " - status : " + jqXHR.status;
//
//                            }
//                            setTimeout(function () {
//                                $("#modal_cambioPassword").modal('hide');
//                            }, 3000);


                        }
                    });
                
            }
        } else {

            DangerNotify('Las contraseñas no son iguales');

        }
    });




});


function SuccesNotify() {


    $.notify({
        icon: 'fa fa-check-square-o fa-lg mt-6',
        title: 'Excelente !! ',
        message: ' acción realiza con Éxito.',
        url: null,

        target: '_blank'}, {
        element: '#modal_title',
        position: null,
        type: "success",
        allow_dismiss: true,
        newest_on_top: false,
        showProgressbar: false,
        placement: {
            from: "top",
            align: "center"
        },
        offset: 20,
        spacing: 10,
        z_index: 1031,
        delay: 3000,
        timer: 1000,
        url_target: '_blank',
        mouse_over: null,
        animate: {
            enter: 'animated fadeInDown',
            exit: 'animated fadeOutUp'
        },
        onShow: null,
        onShown: null,
        onClose: null,
        onClosed: null,
        icon_type: 'class',
        template: '<div data-notify="container" class="col-xs-8 col-sm-8 alert alert-{0}" role="alert">' +
                '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
                '<span data-notify="icon"></span> ' +
                '<span data-notify="title">{1}</span> ' +
                '<span data-notify="message">{2}</span>' +
                '<div class="progress" data-notify="progressbar">' +
                '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
                '</div>' +
                '<a href="{3}" target="{4}" data-notify="url"></a>' +
                '</div>'
    });

}

function DangerNotify(mensaje) {


    $.notify({
        icon: 'fa fa-check-square-o fa-lg mt-6',
        title: 'Ups!.. ',
        message: mensaje,
        url: null,
        target: '_blank'}, {
        element: '#modal_title',
        position: null,
        type: "danger",
        allow_dismiss: true,
        newest_on_top: false,
        showProgressbar: false,
        placement: {
            from: "top",
            align: "center"
        },
        offset: 20,
        spacing: 10,
        z_index: 1031,
        delay: 3000,
        timer: 1000,
        url_target: '_blank',
        mouse_over: null,
        animate: {
            enter: 'animated fadeInDown',
            exit: 'animated fadeOutUp'
        },
        onShow: null,
        onShown: null,
        onClose: null,
        onClosed: null,
        icon_type: 'class',
        template: '<div data-notify="container" class="col-xs-8 col-sm-8 alert alert-{0}" role="alert">' +
                '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
                '<span data-notify="icon"></span> ' +
                '<span data-notify="title">{1}</span> ' +
                '<span data-notify="message">{2}</span>' +
                '<div class="progress" data-notify="progressbar">' +
                '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
                '</div>' +
                '<a href="{3}" target="{4}" data-notify="url"></a>' +
                '</div>'
    });

}



