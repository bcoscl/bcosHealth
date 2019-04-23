/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#checkbox_activo").attr('checked', true);

    $("#numuser_user").focusout(function () {
        $.ajax({
            url: "../../ServletCrearUsuario",
            dataType: "text",
            data: {
                accion: "EXISTE_REGISTRO",
                numuser_user: $("#numuser_user").val()
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
                    $("#numuser_user").focus();
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
                    mensaje = " Sitio not found - status : " + jqXHR.status;
                } else if (jqXHR.status == 401) {
                    location.href = "../../pages/base/sorry.html";
                } else {
                    mensaje = " - status : " + jqXHR.status;

                }
            }
        });
    });




// carga Select de roles
    $.ajax({
        url: "../../ServletListarRoles",
        dataType: "text",
        data: {
            accion: "LR-SELECT"
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
            $("#roles_select_content").html(data);
            $("#roles_select_content select").selectpicker({
            });
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


// cargar Select de  profesiones

    $.ajax({
        url: "../../ServletListarProfesiones",
        dataType: "text",
        data: {
            accion: "LP-SELECT"
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
            $("#profesiones_select_content").html(data);
            $("#profesiones_select_content select").selectpicker({
            });
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

// carga las Sucursales
    $.ajax({
        url: "../../ServletListarSucursales",
        dataType: "text",
        data: {
            accion: "LS-SELECT-MULT-BY-ACTIVE"
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
            $("#suc_select_content").html(data);
            $("#suc_select_content select").selectpicker({
            });
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


//checkbox
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

        $.ajax({
            url: "../../ServletCrearUsuario",
            dataType: "text",
            data: {
                accion: "CREATE",
                numuser_user: $("#numuser_user").val(),
                nombre_user: $("#nombre_user").val(),
                apellido_user: $("#apellido_user").val(),
                email_contacto_user: $("#email_contacto_user").val(),
                numero_telefono_user: $("#numero_telefono_user").val(),
                textarea_obs: $("#textarea_obs").val(),
                profesion_select: $("#profesion_select option:selected").text(),
                //select_plan_code: $("#select_plan option:selected").val(),
                sucursal_select: $("#sucursal_select option:selected").text(),
                roles_select: $("#roles_select option:selected").text(),
                password: $("#password").val(),
                checkbox_activo: $("#checkbox_activo").is(":checked")
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


    /*Autogeneracion de Pass*/
    $("#btn_generate").click(function (e) {

        $.ajax({
            url: "../../ServletPassGenerate",
            dataType: "text",
            data: {
                accion: "PASS-GENERATE"
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
                $("#password").val(data);
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
    });


});




