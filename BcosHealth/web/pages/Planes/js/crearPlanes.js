/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#submitButton").click(function (e) {


        $.ajax({
            url: "../../ServletPlanes",
            dataType: "text",
            data: {
                planName: $("#planName").val(),
                userMax: $("#userMax").val()
            },
            beforeSend: function () {
                //        $("#navData").html("<div  class=\"container centered loader\"></div>");	
                //S $.blockUI({ message: $('#load'),css:{ width:512, height:384, cursor:'default' }});
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
                //$("#navData").html(data);
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




