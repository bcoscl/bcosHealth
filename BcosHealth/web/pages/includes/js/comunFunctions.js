/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

//    alert($("#menuContent").html());

    $(".OnlyRealNumbers").keyup(function () {
        var val = $(this).val();
        val = val.replace("..", ".");
        var res = val.substring(0, 1);
        if (res == ".") {
            val = "0" + val;
        }
        $(this).val(val);
    });


    $(".OnlyRealNumbers").focusout(function () {
        var val = $(this).val();
        var res = val.substring(val.length-1, val.length);
        if (res == ".") {
            val = val.substring(0, val.length-1);
        }
         $(this).val(val);
    });


});


function validaNumericos(event) {
    if ((event.charCode >= 48 && event.charCode <= 57) || (event.charCode == 46)) {
        return true;
    }
    return false;
}