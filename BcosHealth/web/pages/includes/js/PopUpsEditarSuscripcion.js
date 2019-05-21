/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $("#modal_editSuscricpion").click(function () {

        var id = ($(this).parent().parent().find("form")).attr("id");
        id = "#" + id;

        if (validationform(id)) {
            $.ajax({
                url: "../../ServletUpdateSuscripcion",
                dataType: "text",
                data: {
                    accion: "UPDATE-SUSCRIPCION",
                    id: $("#modal_suscripcion_id").val(),
                    nombre_empresa: $("#modal_nombre_empresa").val(),
                    contacto_empresa: $("#modal_contacto_empresa").val(),
                    email_contacto: $("#modal_email_contacto").val(),                    
                    numero_telefono: $("#modal_numero_telefono").val(),
                    select_plan_name: $("#select_plan option:selected").text(),
                    select_plan_code: $("#select_plan option:selected").val()
                    
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
                    CargaInicialSuscripciones();
                    removeValidation(id);
                    $('#largeModal_editSuscripcion').modal('hide');
                    //alert('Insert OK');
                    $.unblockUI();
                    SuccesNotify();

                    //$("#contenido").html(data);


                },
                error: function (jqXHR, textStatus, errorThrown) {

                    $.unblockUI();
                    removeValidation(id);
                   
                    
                    $('#largeModal_editSuscripcion').modal('hide');

                    DangerNotify();
                    CargaInicialSuscripciones();
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
function editSuscripcion(empresa, contacto, email,numero,plan,id) {
    cleanEditSuscripcion();
 
    $("#modal_suscripcion_id").val(id);
    $("#modal_nombre_empresa").val(empresa);
    $("#modal_contacto_empresa").val(contacto);
    $("#modal_email_contacto").val(email);
    $("#modal_numero_telefono").val(numero);
    //$.when(cargaPlanes(plan)).then();
    cargaPlanes(plan);
    $('#largeModal_editSuscripcion').modal('toggle');
    
}
function cargaPlanes(plan){
   
     $.ajax({
        url: "../../ServletListarPlanes",
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
            $("#select_plan").html(data);
            $("#select_plan option:contains(" + plan + ")").attr('selected', 'selected')
            
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
    
function  getPopUpLoad(id){
    
    return "<div id='load_"+id+"' class='sk-fading-circle' style='display: none;'><div class='sk-circle1 sk-circle'></div><div class='sk-circle2 sk-circle'></div><div class='sk-circle3 sk-circle'></div>    <div class='sk-circle4 sk-circle'></div>    <div class='sk-circle5 sk-circle'></div>    <div class='sk-circle6 sk-circle'></div>    <div class='sk-circle7 sk-circle'></div>    <div class='sk-circle8 sk-circle'></div>    <div class='sk-circle9 sk-circle'></div>    <div class='sk-circle10 sk-circle'></div>    <div class='sk-circle11 sk-circle'></div>    <div class='sk-circle12 sk-circle'></div></div>";
    
}
function cleanEditSuscripcion() {

    $("#modal_suscripcion_id").val("");
    $("#modal_nombre_empresa").val("");
    $("#modal_contacto_empresa").val("");
    $("#modal_email_contacto").val("");
    $("#modal_numero_telefono").val("");
    $("#select_plan").html("");

}
    






