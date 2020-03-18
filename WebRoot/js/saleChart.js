let ctx = document.getElementById("chart").getContext('2d');

var gradientStroke = ctx.createLinearGradient(500, 0, 100, 0);
gradientStroke.addColorStop(0, "#ff6c00");
gradientStroke.addColorStop(1, "#ff3b74");

var gradientBkgrd = ctx.createLinearGradient(0, 100, 0, 400);
gradientBkgrd.addColorStop(0, "rgba(244,94,132,0.2)");
gradientBkgrd.addColorStop(1, "rgba(249,135,94,0)");

let draw = Chart.controllers.line.prototype.draw;
Chart.controllers.line = Chart.controllers.line.extend({
    draw: function () {
        draw.apply(this, arguments);
        let ctx = this.chart.chart.ctx;
        let _stroke = ctx.stroke;
        ctx.stroke = function () {
            ctx.save();
            ctx.shadowColor = 'rgba(244,94,132,0.8)';
            ctx.shadowBlur = 8;
            ctx.shadowOffsetX = 0;
            ctx.shadowOffsetY = 6;
            _stroke.apply(this, arguments)
            ctx.restore();
        }
    }
});

var mon_val = Array(12);
mon_val[0] = Number(document.getElementById("mon_1").value);
mon_val[1] = Number(document.getElementById("mon_2").value);
mon_val[2] = Number(document.getElementById("mon_3").value);
mon_val[3] = Number(document.getElementById("mon_4").value);
mon_val[4] = Number(document.getElementById("mon_5").value);
mon_val[5] = Number(document.getElementById("mon_6").value);
mon_val[6] = Number(document.getElementById("mon_7").value);
mon_val[7] = Number(document.getElementById("mon_8").value);
mon_val[8] = Number(document.getElementById("mon_9").value);
mon_val[9] = Number(document.getElementById("mon_10").value);
mon_val[10] = Number(document.getElementById("mon_11").value);
mon_val[11] = Number(document.getElementById("mon_12").value);

var chart = new Chart(ctx, {

    type: 'line',
    data: {
        labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
        datasets: [{
            label: "Sales Volume",
            backgroundColor: gradientBkgrd,
            borderColor: gradientStroke,
            data: mon_val,
            pointBorderColor: "rgba(255,255,255,0)",
            pointBackgroundColor: "rgba(255,255,255,0)",
            pointBorderWidth: 0,
            pointHoverRadius: 8,
            pointHoverBackgroundColor: gradientStroke,
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 4,
            pointRadius: 1,
            borderWidth: 5,
            pointHitRadius: 16,
        }]
    },

    // Configuration options go here
    options: {
        tooltips: {
            backgroundColor: '#fff',
            displayColors: false,
            titleFontColor: '#000',
            bodyFontColor: '#000'

        },
        legend: {
            display: false
        },
        scales: {
            xAxes: [{
                gridLines: {
                    display: false
                }
            }],
            yAxes: [{
                ticks: {
                    callback: function (value, index, values) {
                        return value;
                    }
                }
            }],
        }
    }
});

