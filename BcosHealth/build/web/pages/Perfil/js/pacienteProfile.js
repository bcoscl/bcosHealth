/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    CargaProfileInicial();



$("#fileupload").click(function(){
    $("#upload-file").click();
})


});

function CargaProfileInicial(){
    
       $.ajax({
        url: "../../ServletPacienteProfile",
        dataType: "text",
        data: {
            accion: "CP-PERFIL"
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

            var valor = data.split("|");
            //alert(valor);
            //alert('Insert OK');
            $.unblockUI();
            //$("#contenido").html(data);
            
            
            $("#input_nombre").html(valor[0]);
            $("#input_rut").html(valor[1]);
            $("#input_direccion").html(valor[2]);
            $("#input_edad").html(valor[3]);
            $("#input_fechaNacimiento").html(valor[4]);
            $("#input_prevision").html(valor[5]);
            $("#input_telefono").html(valor[6]);
            $("#input_email").html(valor[7]);
            $("#input_profesion").html(valor[8]);
            $("#input_estado_civil").html(valor[9]);
            $("#input_aboutMe").html(valor[10]);
            $("#input_sexo").html(valor[11]);

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
    
}






