/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $.ajax({
        url: "../../ServletListarPacientes",
        dataType: "text",
        data: {
            accion: "LP-TABLA"
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
            $("#contenido").html(data);
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



    $('#entradafilter').keyup(function () {
        var rex = new RegExp($(this).val(), 'i');
        $('.contenidobusqueda tr').hide();
        $('.contenidobusqueda tr').filter(function () {
            return rex.test($(this).text());
        }).show();

    });


});

//checkbox
//function change(id) {
//
//    var method = "";
//
//    if ($("#CL_" + id).is(":checked")) {
//        $("#CL_" + id).attr('checked', false);
//        //alert("ACTIVAR cuenta");
//        method = "LU-ACTIVAR";
//    } else {
//        $("#CL_" + id).attr('checked', true);
//        //alert("DESACTIVAR cuenta");
//        method = "LU-DESACTIVAR";
//    }
//
//    $.ajax({
//        url: "../../ServletUpdateUsuarios",
//        dataType: "text",
//        data: {
//            accion: method,
//            id: id
//        },
//        beforeSend: function () {
//
//            $.blockUI({message: $('#load'), css: {
//                    padding: 0,
//                    margin: 0,
//                    width: '35%',
//                    top: '35%',
//                    left: '35%',
//                    textAlign: 'center',
//                    color: '#c8ced300',
//                    border: '0px',
//                    backgroundColor: '#c8ced300',
//                    cursor: 'wait'
//                }});
//        },
//
//        success: function (data) {
//
//           $.unblockUI();                
//                $("#msgResult").removeAttr('style');
//                $("#msgResult").removeClass('fade show-none');
//                setTimeout(function () {
//                    $("#msgResult").fadeOut(500);
//                    $("#msgResult").addClass('fade show-none');
//                }, 2000);
//
//        },
//        error: function (jqXHR, textStatus, errorThrown) {
//
//            $.unblockUI();
//            //$("#contenido").removeAttr('style');
//            $("#msgResultError").removeClass('fade show-none');
//            setTimeout(function () {
//                $("#msgResult").fadeOut(1000);
//                $("#msgResultError").addClass('fade show-none');
//            }, 2000);
//
//
//            if (jqXHR.status == 500) {
//                // Server side error
//                mensaje = " Error server side - status : " + jqXHR.status;
//            } else if (jqXHR.status == 404) {
//                mensaje = " Sitio not found - status : " + jqXHR.status;
//            } else if (jqXHR.status == 401) {
//                location.href = "../../pages/base/sorry.html";
//            } else {
//                mensaje = " - status : " + jqXHR.status;
//
//            }
//
//        }
//    });
//}
//    

function Detalle(numuser) {

    $.ajax({
        url: "../../ServletListarPacientes",
        dataType: "text",
        data: {
            accion: "LP-DETALLE",
            user: numuser
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
            //$("#contenido").html(data);
            window.location.replace("../Perfil/pacienteProfile.jsp");
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








