/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

/*Validacion de usuario Creado*/
    $("#numuser_paciente").focusout(function () {
        $.ajax({
            url: "../../ServletCrearPaciente",
            dataType: "text",
            data: {
                accion: "EXISTE_PACIENTE",
                numuser_user: $("#numuser_paciente").val()
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


                if (data != "OK") {
                    $("#user_registrado").html(data);
                    $("#user_registrado").removeAttr('style');
                    $("#user_registrado").removeClass('fade show-none');
                    $("#user_registrado").addClass('alert-danger');
                    $("#numuser_paciente").focus();
                    setTimeout(function () {
                        $("#user_registrado").fadeOut(500);
                        $("#user_registrado").addClass('fade show-none');
                        $("#user_registrado").removeAttr('style="display: none;"');
                    }, 4000);
                }



                $.unblockUI();
                $("#msgResult").removeAttr('style');
                $("#msgResult").removeClass('fade show-none');
                setTimeout(function () {
                    $("#msgResult").fadeOut(500);
                    $("#msgResult").addClass('fade show-none');
                }, 2000);

            },
            error: function (jqXHR, textStatus, errorThrown) {

                var mensaje;
                

                $.unblockUI();
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
                    mensaje="Ups!! No pudimos Validar el Registro, intentalo mas tarde";
                    
                    $("#user_registrado").html(mensaje);
                    $("#user_registrado").removeAttr('style');
                    $("#user_registrado").removeClass('fade show-none');
                    $("#user_registrado").addClass('alert-danger');
                    $("#numuser_paciente").focus();
                    setTimeout(function () {
                        $("#user_registrado").fadeOut(500);
                        $("#user_registrado").addClass('fade show-none');
                        $("#user_registrado").removeAttr('style="display: none;"');
                    }, 4000);
                    
                } else if (jqXHR.status == 401) {
                    location.href = "../../pages/base/sorry.html";
                } else {
                    mensaje = " - status : " + jqXHR.status;

                }
            }
        });
    });


//envio de informacion

    $("#submitButton").click(function (e) {

        $.ajax({
            url: "../../ServletCrearPaciente",
            dataType: "text",
            data: {
                accion: "CREATE",
                numuser_user: $("#numuser_paciente").val(),
                nombre_paciente: $("#nombre_paciente").val(),
                apellido_paciente: $("#apellido_paciente").val(),
                email_contacto_paciente: $("#email_contacto_paciente").val(),
                numero_telefono_paciente: $("#numero_telefono_paciente").val(),
                profesion_paciente: $("#profesion_paciente").val(),
                estado_civil_paciente: $("#estado_civil_paciente").val(),
                fecha_nacimiento_paciente: $("#fecha_nacimiento_paciente").val(),
                edad_paciente: $("#edad_paciente").val(),
                prevision_select: $("#prevision_select option:selected").text(),
                isapre_name_paciente: $("#isapre_name_paciente").val(),
                textarea_obs_paciente: $("#textarea_obs_paciente").val(),
                direccion_paciente: $("#direccion_paciente").val(),
                sexo_paciente: $("#sexo_select option:selected").text()
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
                $("#msgResult").removeAttr('style');
                $("#msgResult").removeClass('fade show-none');
                setTimeout(function () {
                    $("#msgResult").fadeOut(500);
                    $("#msgResult").addClass('fade show-none');
                }, 2000);

            },
            error: function (jqXHR, textStatus, errorThrown) {

                var mensaje;

                $.unblockUI();
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

    });


});




