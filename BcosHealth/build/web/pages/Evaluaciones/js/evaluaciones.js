$(document).ready(function () {

    CargaInicialeva();



    $("#evaClick").click(function (e) {

        $(".eva").removeClass("in active");
        $(".eva").removeClass("active");

        $("#evaDash").addClass("in active");
        $("#hrefevadash").addClass("active");

    });

});

function inicializaDashBoard(gson) {
    //alert(gson);
    if (gson.indexOf("Sin Registros...")>=0) {

        $("#viewDashBoard").hide();
        $("#infoReturn").show();
        $("#infoReturn").html(gson);

    } else {
        
        $("#viewDashBoard").show();
        $("#infoReturn").hide();

        var obj = JSON.parse(gson);
        //alert("ok :"+obj.chart.length);
        //console.log(obj);
        for (var i = 0; i <= 4; i++) {

            //$("#lineChart_"+ obj.chart[i].id).html("");
            var canvas = "<canvas class=\"chart chartjs-render-monitor\" id=\"lineChart_" + obj.chart[i].id + "\" height=\"70\" width=\"39\" style=\"display: block; width: 339; height: 70px;\"></canvas>";
            $("#canvas_" + obj.chart[i].id).html(canvas);

            $("#lineChart_title_" + obj.chart[i].id).html(obj.chart[i].title);
            $("#lineChart_des_" + obj.chart[i].id).html(obj.chart[i].descripcion);


            var ctxL = document.getElementById("lineChart_" + obj.chart[i].id).getContext('2d');

            var var_min = parseInt(obj.chart[i].minValue);
            var var_max = parseInt(obj.chart[i].maxValue);

            var myLineChart = new Chart(ctxL, {
                type: 'line',
                data: {
                    //labels: ['17-06-2019', '18-06-2019', '19-06-2019', '20-06-2019', '21-06-2019', '22-06-2019', '23-06-2019'],
                    labels: obj.chart[i].labelsPoints.split(',').reverse(),

                    datasets: [{
                            //label: 'Peso (Kg) ',
                            label: obj.chart[i].descriptionPoints,
                            //data: [65, 59, 80, 81, 56, 55, 89],
                            data: obj.chart[i].dataPoints.split(',').reverse(),
                            backgroundColor: [
                                'transparent',
                            ],
                            //lineColor: "transparent",
                            borderColor: [
                                'red',
                            ],
                            //fill:false,
                            pointHoverBackgroundColor: '#fff',
                            pointHoverBorderColor: '#fff',
                            borderWidth: 2
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    legend: {
                        display: false
                    },
                    scales: {
                        xAxes: [{
                                gridLines: {
                                    color: '#fff',
                                    zeroLineColor: '#fff',
                                    backgroundColor: '#fff'
                                },
                                ticks: {
                                    display: true,
                                    fontSize: 7,
                                    fontColor: 'transparent',
                                    backgroundColor: '#fff'
                                }
                            }],
                        yAxes: [{
                                display: true,
                                ticks: {
                                    display: true,
                                    backgroundColor: '#fff',
                                    //fontColor: '#fff',
                                    zeroLineColor: '#fff',
                                    //min: obj.chart[i].minValue,
                                    min: var_min,
                                    //max: obj.chart[i].maxValue
                                    max: var_max
                                }
                            }]
                    },
                    elements: {
                        line: {
                            tension: 0.00001,
                            borderWidth: 1,
                            zeroLineColor: '#fff'
                        },
                        point: {
                            radius: 2,
                            hitRadius: 10,
                            hoverRadius: 4
                        }
                    }
                }
            });


        }
    }
}


function CargaInicialeva() {
    $.ajax({
        url: "../../ServletListarEvaluacion",
        dataType: "text",
        data: {
            accion: "LE-FULL"

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
            var str = data.split("|");
            $("#evaReg").html(str[0]);
            inicializaDashBoard(str[1]);
            //$("#evaDash").html(str[1]);
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
    // carga
}