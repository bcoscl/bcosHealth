/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $("#modal_addAtbutton_editInformation").click(function () {


        $.ajax({
            url: "../../ServletUpdatePacienteProfile",
            dataType: "text",
            data: {
                accion: "UPDATE-PACIENTE-PROFILE",
                paciente_nombre: $("#modal_input_paciente_nombre").val(),                
                paciente_numuser: $("#modal_input_paciente_rut").val(),
                paciente_direccion: $("#modal_input_paciente_direccion").val(),
                paciente_edad: $("#modal_input_paciente_edad").val(),
                paciente_fecha_nacimiento: $("#modal_input_paciente_fechaNacimiento").val(),
                paciente_prevision: $("#modal_input_paciente_prevision").val(),
                paciente_telefono: $("#modal_input_paciente_telefono").val(),
                paciente_email: $("#modal_input_paciente_email").val(),
                paciente_profesion: $("#modal_input_paciente_profesion").val(),
                paciente_estado_civil: $("#modal_input_paciente_estado_civil").val(),
                paciente_sexo: $("#modal_sexo_paciente_select option:selected").text(),
                paciente_obs: $("#modal_input_paciente_aboutMe").val()
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
                    CargaProfileInicial();
                $('#largeModalPacienteInformation').modal('hide');
                //alert('Insert OK');
                $.unblockUI();
              
                //$("#contenido").html(data);


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
function popupEditPacienteInformation() {

    var sexo = $.trim($("#input_sexo").text());
    $("#modal_input_paciente_nombre").val($("#input_nombre").text());
    $("#modal_input_paciente_rut").val($("#input_rut").text());
    $("#modal_input_paciente_direccion").val($("#input_direccion").text());
    $("#modal_input_paciente_edad").val($("#input_edad").text());
    $("#modal_input_paciente_fechaNacimiento").val($("#input_fechaNacimiento").text());
    $("#modal_input_paciente_prevision").val($("#input_prevision").text());
    $("#modal_input_paciente_telefono").val($("#input_telefono").text());
    $("#modal_input_paciente_email").val($("#input_email").text());
    $("#modal_input_paciente_profesion").val($("#input_profesion").text());
    $("#modal_input_paciente_estado_civil").val($("#input_estado_civil").text());
    $("#modal_sexo_paciente_select option:contains(" + sexo + ")").attr('selected', 'selected');
    $("#modal_input_paciente_aboutMe").val($("#input_aboutMe").text());
    $('#largeModalPacienteInformation').modal('toggle');
}





