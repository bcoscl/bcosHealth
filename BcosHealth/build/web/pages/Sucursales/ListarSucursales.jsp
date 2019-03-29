<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@include file="../includes/header.jsp" %>


<title>Bcos Health - Listar Planes</title>
<!-- Icons
<link href="./css/coreui-icons.min.css" rel="stylesheet">
<link href="./css/flag-icon.min.css" rel="stylesheet">
<link href="./css/font-awesome.min.css" rel="stylesheet">
<link href="./css/simple-line-icons.css" rel="stylesheet">-->
<!-- Main styles for this application
<link href="css/style.css" rel="stylesheet">
<link href="./css/pace.min.css" rel="stylesheet">-->


<!-- CoreUI and necessary plugins

<script src="./js/jquery.min.js"></script>
<script src="./js/popper.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/pace.min.js"></script>
<script src="./js/perfect-scrollbar.min.js"></script>
<script src="./js/coreui.min.js"></script>-->
<!-- Plugins and scripts required by this view
<script src="./js/Chart.min.js"></script>
<script src="./js/custom-tooltips.min.js"></script>
<script src="./js/main.js"></script>-->
<script>
    $(document).ready(function () {


        $.ajax({
            //url: "http://localhost:8080/SPR/SED/MantenedorIdiomas",
            dataType: "text",
            data: {opcion: "navData"},
            beforeSend: function () {
                $("#navData").html("<div  class=\"container centered loader\"></div>");

            },
            success: function (data) {
                $("#navData").html(data);


            },
            error: function (jqXHR, textStatus, errorThrown) {
                var mensaje;
                if (jqXHR.status == 500) {
                    // Server side error
                    mensaje = " Error server side - status : " + jqXHR.status;
                } else if (jqXHR.status == 404) {
                    mensaje = " Sitio not found - status : " + jqXHR.status;
                } else {
                    mensaje = " - status : " + jqXHR.status;
                }
                $("#navData").html("<div  class=\"container\"><div class=\"alert alert-warning\"><strong>Warning!</strong> Ups ! no pudimos obtener la informacion, intentalo mas nuevamente mas tarde.." + mensaje + "</div></div>");

            }
        });


        $(".actualizar").click(function (e) {

            var codigo = $(this).parent().parent().find("td:eq(0)").text();
            var texto = $(this).parent().parent().find("td:eq(1)").text();
            var traduccion = $(this).parent().parent().find("td:eq(2)").text();
            var referencia = $(this).parent().parent().find("td:eq(3)").text();

            // 1 idioma
            // 2 codigo
            // 3 referencia
            // #original
            $("#1").html("Ingles");
            $("#2").html(codigo);
            $("#3").html(referencia);
            $("#original").val(texto);


        });


        $(".Export").click(function (e) {
            window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#tableData').html()));

            //window.open('data:application/pdf;base64,' + Base64.encode(buffer) + encodeURIComponent($('#tableData').html()));
            e.preventDefault();
        });

        $('#entradafilter').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.contenidobusqueda tr').hide();
            $('.contenidobusqueda tr').filter(function () {
                return rex.test($(this).text());
            }).show();

        })


        $('th').click(function () {
            var table = $(this).parents('table').eq(0)
            var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
            this.asc = !this.asc
            if (!this.asc) {
                rows = rows.reverse()
            }
            for (var i = 0; i < rows.length; i++) {
                table.append(rows[i])
            }
            setIcon($(this), this.asc);
        })

        function comparer(index) {
            return function (a, b) {
                var valA = getCellValue(a, index),
                        valB = getCellValue(b, index)
                return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB)
            }
        }

        function getCellValue(row, index) {
            return $(row).children('td').eq(index).html()
        }

        function setIcon(element, asc) {
            $("th").each(function (index) {
                $(this).removeClass("sorting");
                $(this).removeClass("asc");
                $(this).removeClass("desc");
            });
            element.addClass("sorting");
            if (asc)
                element.addClass("asc");
            else
                element.addClass("desc");
        }

    });

    function showIdioma(val) {


        $.ajax({

            url: "",
            dataType: "html",
            data: {token: val,
                opcion: "tableData"},
            beforeSend: function () {
                $("#tableData").html("<div  class=\"container centered loader\"></div>");

            },
            success: function (data) {
                $("#tableData").html(data);
                var rex = new RegExp(val, 'i');

                $('.contenidobusqueda tr').hide();
                // $('.contenidobusqueda tr .'+val).show();
                $('.contenidobusqueda tr').filter(function (val) {
                    return rex.test($(this).attr("class"));
                }).show();

            },
            error: function (jqXHR, textStatus, errorThrown) {
                var mensaje;
                if (jqXHR.status == 500) {
                    // Server side error
                    mensaje = " Error server side - status : " + jqXHR.status;
                } else if (jqXHR.status == 404) {
                    mensaje = " Sitio not found - status : " + jqXHR.status;
                } else {
                    mensaje = " - status : " + jqXHR.status;
                }
                $("#tableData").html("<div  class=\"container\"><div class=\"alert alert-warning\"><strong>Warning!</strong> Ups ! no pudimos obtener la informacion, intentalo mas nuevamente mas tarde.." + mensaje + "</div></div>");

            }
        });



    }
</script>

<link href="../../comun/css/dataTables.bootstrap4.css" rel="stylesheet">
<%@include file="../includes/body.jsp" %>


<!-- contenido -->



<div id="ui-view"><div>

        <span >	Listar suscripciones</span>

        <div class="animated fadeIn">
            <div class="card">

                <div class="card-body">
                    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                        <div class="row"><div class="col-sm-12 col-md-6">
                                <div class="dataTables_length" id="DataTables_Table_0_length">
                                    <!--<label>Show <select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="custom-select custom-select-sm form-control form-control-sm">
                                    <option value="10">10</option><option value="25">25</option>
                                    <option value="50">50</option><option value="100">100</option>
                                    </select> entries</label>-->
                                </div>
                            </div>
                            <div class="col-sm-12 col-md-6">
                                <div id="DataTables_Table_0_filter" class="dataTables_filter">
                                    <label>Search:<input type="search" id="entradafilter" class="form-control form-control-sm" placeholder="" aria-controls="DataTables_Table_0">
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-striped table-bordered datatable dataTable no-footer" id="DataTables_Table_0" role="grid" aria-describedby="DataTables_Table_0_info" style="border-collapse: collapse !important">
                                    <thead>
                                        <tr role="row">
                                            <th class="sorting_asc" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Username: activate to sort column descending" style="width: 20%;">Nombre del Plan</th>
                                            <th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 20%;">Maximo de usuarios</th>
                                            <th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 20%;">Fecha Creacion</th>				
                                            <th class="sorting d-none d-sm-table-cell" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 20%;">Creado por</th>			
                                            <th class="sorting" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-label="Actions: activate to sort column ascending" style="width: 2%;">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody class="contenidobusqueda">
                                        <tr role="row" class="odd">
                                            <td class="sorting_1 ">Plan 50</td>
                                            <td class="d-none d-sm-table-cell">50</td>
                                            <td class="d-none d-sm-table-cell">17-03-2019</td>
                                            <td class="d-none d-sm-table-cell">Alexis Cantero</td>

                                            <td>
                                                <!--<a class="btn btn-success" href="#">
                                                <i class="fa fa-search-plus"></i>
                                                </a>-->
                                                <a class="btn btn-info" href="#">
                                                    <i class="fa fa-edit"></i>
                                                </a>
                                                <!--<a class="btn btn-danger" href="#">
                                                <i class="fa fa-trash-o"></i>
                                                </a>-->
                                            </td>
                                        </tr>
                                        <tr role="row" class="odd">
                                            <td class="sorting_1 ">Plan 12</td>
                                            <td class="d-none d-sm-table-cell">12</td>
                                            <td class="d-none d-sm-table-cell">17-03-2019</td>
                                            <td class="d-none d-sm-table-cell">Alexis Cantero</td>

                                            <td>
                                                <!--<a class="btn btn-success" href="#">
                                                <i class="fa fa-search-plus"></i>
                                                </a>-->
                                                <a class="btn btn-info" href="#">
                                                    <i class="fa fa-edit"></i>
                                                </a>
                                                <!--<a class="btn btn-danger" href="#">
                                                <i class="fa fa-trash-o"></i>
                                                </a>-->
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div></div>
                        <div class="row"><div class="col-sm-12 col-md-5">
                                <div class="dataTables_info" id="DataTables_Table_0_info" role="status" aria-live="polite">Showing 1 to 10 of 32 entries</div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate"><ul class="pagination"><li class="paginate_button page-item previous disabled" id="DataTables_Table_0_previous"><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li><li class="paginate_button page-item active"><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="1" tabindex="0" class="page-link">1</a></li><li class="paginate_button page-item "><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" class="page-link">2</a></li><li class="paginate_button page-item "><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="3" tabindex="0" class="page-link">3</a></li><li class="paginate_button page-item "><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="4" tabindex="0" class="page-link">4</a></li><li class="paginate_button page-item next" id="DataTables_Table_0_next"><a href="#" aria-controls="DataTables_Table_0" data-dt-idx="5" tabindex="0" class="page-link">Next</a></li></ul></div></div></div></div>
                </div>
            </div>
        </div></div></div>
</div>



<!-- fin contenido -->		  
<%@include file="../includes/footer.jsp" %>