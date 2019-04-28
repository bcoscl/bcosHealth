/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validationform(formularioName) {
    //alert(formularioName);
    /*Example "#formCrearPlanes"*/
    var formulario = formularioName;
    var form = $(formulario);
    var continuar = false;
    if (form[0].checkValidity() === false) {
        continuar = false;
    } else {
        continuar = true;
    }
    form.addClass('was-validated');

    return continuar;
}
function removeValidation(formularioName) {
    var formulario = formularioName;
    var form = $(formulario);
    form.removeClass('was-validated');

}

