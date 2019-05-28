/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $("#fileuploadArchivo").click(function () {
        $("#upload-file").click();
    });

    $("#formArchivoPopUp").submit(function (event) {
        //salert("Handler for .submit() called.");

//        var id = ($(this).parent().parent().find("form")).attr("id");
        

        if ($("#upload-file").val()!="") {
           
            return;
        } else {
            
            DangerNotifyMsg("Seleccione un archivo", "#formArchivoPopUp", 'center');
            event.preventDefault();
        }



    });

//    $("#modal_subirArchivo").click(function () {
//
//        var id = ($(this).parent().parent().find("form")).attr("id");
//        id = "#" + id;
//
//        if (validationform(id)) {
//
//            
////            $.ajax({
////                url: "../../ServletUploadArchivo",
////                dataType: "text",
////                data: {
////                    accion: "UPLOAD_ARCHIVO",                   
////                    url:$("#upload-file").val()
////                },
////                beforeSend: function () {
////
////                    $.blockUI({message: $('#load'), css: {
////                            padding: 0,
////                            margin: 0,
////                            width: '35%',
////                            top: '35%',
////                            left: '35%',
////                            textAlign: 'center',
////                            color: '#c8ced300',
////                            border: '0px',
////                            backgroundColor: '#c8ced300',
////                            cursor: 'wait'
////                        }});
////                },
////
////                success: function (data) {
////                   
////                    $('#largeModalsubirArchivos').modal('hide');
////                    //alert('Insert OK');
////                    $.unblockUI();
////                   SuccesNotify();
////                    //$("#contenido").html(data);
////
////
////                },
////                error: function (jqXHR, textStatus, errorThrown) {
////                    $('#largeModalsubirArchivos').modal('hide');
////                    $.unblockUI();
////                   DangerNotify();
////
////
////                    if (jqXHR.status == 500) {
////                        // Server side error
////                        mensaje = " Error server side - status : " + jqXHR.status;
////                    } else if (jqXHR.status == 404) {
////                        mensaje = " Sitio not found - status : " + jqXHR.status;
////                    } else if (jqXHR.status == 401) {
////                        location.href = "../../pages/base/sorry.html";
////                    } else {
////                        mensaje = " - status : " + jqXHR.status;
////
////                    }
////
////
////                }
////            });
//        } else {
//
//            DangerNotifyMsg("Seleccione un archivo", id, 'center');
//
//        }
//    });

});


function popupAddfile() {

    $('#input_userid').val($('#input_id').val());
    $('#largeModalsubirArchivos').modal('toggle');

}




