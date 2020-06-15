(function($){
    function init(){
        var myChart = echarts.init(document.getElementById('main'));
        let data=[];
        let legend_data=[];
        let car1=[],car2=[],car3=[];
        let rates1=[],rates2=[],rates3=[];
        for(var i=0;i<60;i++){
            if(i<10){
                data.push("0"+i);
            }else{
                data.push(i);
            }
        }
        $.ajax({
            url:'gps',
            dataType:'json',
            async:!1,
            data:{},
            type:'post',
            success:function(json){
                json.forEach((item,index)=>{
                    //console.log(item,index);
                    var hours=item.hours;
                    var i=hours.indexOf(":");
                    hours=hours.substring(0,i);
                    if(!legend_data.includes(hours)){
                        legend_data.push(hours);
                    }
                    var cars=item.cars;
                    var rates=item.rates;
                    var index=parseInt(item.hours.substr(item.hours.indexOf(":")+1));
                    console.log(hours);
                    switch (hours){
                        case "2010-09-01 00":car1[index]=item.cars;rates1[index]=item.rates;break;
                        case "2010-09-01 01":car2[index]=item.cars;rates2[index]=item.rates;break;
                        case "2010-09-01 02":car3[index]=item.cars;rates3[index]=item.rates;break;
                    }

                });
                console.log(car1);
            },
            error:function(e){
                console.log(e)
            }
        });


        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                },
                formatter:function(p){
                    let info="";
                    for(var i=0;i<p.length;i++){
                        if(p[i].componentSubType=="bar"){
                            info+="<p>独立出租车的载客量"+p[i].value+"</p>";
                        }else if(p[i].componentSubType=="line"){
                            info+="<p>独立出租车的载客比"+p[i].value/10000+"</p>";
                        }
                        //console.log(info);
                    }

                    return info;
                }
            },
            toolbox: {

            },
            legend: {
                data: legend_data
            },
            xAxis: [
                {
                    type: 'category',
                    data: data,
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '独立出租车的数量',
                    min: 0,
                    max: 10000,
                    interval:1000,
                    axisLabel: {
                        formatter: '{value} 俩'
                    }
                },
                {
                    type: 'value',
                    name: '独立出租车的载客比（扩大10000）',
                    min: 0,
                    max: 100,
                    interval: 20,
                    axisLabel: {
                        formatter: '{value} %'
                    }
                }
            ],
            series: [
                {
                    name: legend_data[0],
                    type: 'bar',
                    data: car1
                },
                {
                    name: legend_data[0],
                    type: 'line',
                    data: rates1.map(x=>x*10000)
                },
                {
                    name: legend_data[1],
                    type: 'bar',
                    data: car2
                },
                {
                    name: legend_data[1],
                    type: 'line',
                    data: rates2.map(x=>x*10000)
                },
                {
                    name: legend_data[2],
                    type: 'bar',
                    data: car3
                },
                {
                    name: legend_data[2],
                    type: 'line',
                    data: rates3.map(x=>x*10000)
                }
            ]
        };
        console.log(legend_data);
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
    init();
})(jQuery);
