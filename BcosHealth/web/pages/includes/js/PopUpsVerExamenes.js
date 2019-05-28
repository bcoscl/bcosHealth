/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {





});

function loadModal(url, formato) {
    if (formato == "IMG") {

        $("#viewExamenImg").attr("src", url);
        $("#viewExamenEmbed").attr("src", "");
        $("#viewExamenImg").css({display: "block"});
        $("#viewExamenEmbed").css({display: "none"});
    }
    if (formato == "PDF") {
        $("#viewExamenEmbed").attr("src", url);
        $("#viewExamenImg").attr("src", "");
        $("#viewExamenEmbed").css({display: "block"});
        $("#viewExamenImg").css({display: "none"});
    }
    $('#largeModalVerExamen').modal('toggle');

}

function DetalleExamen(id, Rutafile) {

    if (Rutafile != "null" && Rutafile != null && Rutafile != "") {

        $.ajax({
            url: "../../ServletGetUrlExamen",
            dataType: "text",
            data: {
                accion: "URLEXAMEN",
                id: id,
                file: Rutafile
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
                //window.open('./verExamenes.jsp','Continue_to_Application','width=800,height=600');
                if (data.indexOf("jpg") >= 0 || data.indexOf("gif") >= 0 || data.indexOf("png") >= 0 ||
                        data.indexOf("jpg") >= 0) {
                    loadModal(data, 'IMG');
                } else if (data.indexOf("pdf") >= 0) {
                    loadModal(data, 'PDF');
                }


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
    } else {
        DangerNotifyMsg("No existe un Archivo vinculado");

    }
}


