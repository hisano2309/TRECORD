//グラフ表示
// 体重グラフ 
$(document)
	.ready(
		function() {
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8080/weightBmiJson',
				dataType: 'json'
			})
				.done(

					function(res) {
						const weightArray = res.map(item => item.userWeight);
						const bmiArray = res.map(item => item.bmi);
						const healthyWeightArray = res.map(item => item.healthyWeight);				
						const beautyWeightArray = res.map(item => item.beautyWeight);
						const healthyBmiArray = res.map(item => item.healthyBmi);
						const dateArray = res.map(item => item.date);

						console.log(res);
						console.log(weightArray);
						var ctx = document
							.getElementById("weightBmiChart");
						new Chart(
							ctx,
							{
								// グラフの種類：折れ線グラフを指定
								type: 'line',
								data: {
									// x軸の各メモリ
									labels: dateArray, //constで指定した変数（date）
									datasets: [
										{
											label: '体重',
											data: weightArray, //constで指定した変数（userWeight）
											borderColor: "#ec4343",
											backgroundColor: "#00000000",
											lineTension: 0
										}, {
											label: 'BMI',
											data: bmiArray, //constで指定した変数（bmi）
											borderColor: "rgb(54, 162, 235)",
											backgroundColor: "#00000000",
											lineTension: 0
										}, {
											label: '適正体重',
											data: healthyWeightArray, // constで指定した変数（healthyWeight）
											borderColor: "#FF9900",
											backgroundColor: "#00000000",
											lineTension: 0
										}, {
										 	label: '美容体重',
										 	data: beautyWeightArray, // constで指定した変数（beautyWeight）
										 	borderColor: "#FFF100",
										 	backgroundColor: "#00000000",
										 	lineTension: 0
										},{
										 	label: '標準BMI',
										 	data: healthyBmiArray, // constで指定した変数（healthyBmi）
										 	borderColor: "#F6AA00",
										 	backgroundColor: "#00000000",
										 	lineTension: 0
										},],
								},

								// Y軸の上がり幅、下限（初期登録+10kg）、上限（Max値+5kg）を指定
								options: {
									scales: {
										yAxes: [{
											ticks: {
												suggestedMax: Math.max(...weightArray) + 5,
												suggestedMin: bmiArray[0] - 5,
												stepSize: 5
											},
										}]
									}
								}
							});
					}).fail(function() {
						console.log('fail');
					});
		});




// マシーングラフ（線） 
$(document)
	.ready(
		function() {
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8080/MachineSetCountLineGraph',
				dataType: 'json'
			})
				.done(
					function(res) {
						// List<List<>> [Array(4), Array(0), Array(1)] → [{machineId_1}(SQL4行), {machineId_2(SQL0行)}, {machineId_3(SQL1行)}]
						// {machineId_1{カラム1行目{0(domain)}, カラム2行目{1(domain)}, {2(domain)}, {3(domain)}}, machineId_2{}, {}, {], {}}}
						console.log(res);

						// 0:machineId_1{カラム1行目のdomain}, 1:machineId_2{カラム2行目のdomain}, 2:machineId_3{カラム3行目のdomain}......
						const machineId_1 = res[0];
						console.log(machineId_1);
						// machineId_1のweightBmiドメインの各変数の中身を取得できる
						const machineId_1Array = machineId_1.map(item => item.machineWeight);
						console.log(machineId_1Array);


						const machineId_2 = res[1];
						console.log(machineId_2);
						const machineId_2Array = machineId_2.map(item => item.machineWeight);
						console.log(machineId_2Array)

						const machineId_3 = res[2];
						console.log(machineId_3);
						const machineId_3Array = machineId_3.map(item => item.machineWeight);
						
						const machineId_4 = res[3];
						console.log("machineId_4" + machineId_4);
						const machineId_4Array = machineId_4.map(item => item.machineWeight);

						const machineId_5 = res[4];
						console.log(machineId_5);
						const machineId_5Array = machineId_5.map(item => item.machineWeight);

						const machineId_6 = res[5];
						console.log(machineId_6);
						const machineId_6Array = machineId_6.map(item => item.machineWeight);

						// machineId_1のdateを取得する
						const dateArray = machineId_1.map(item => item.date);
						console.log(dateArray);

						var ctx = document
							.getElementById("machineSetCountLineGraph");
						new Chart(
							ctx,
							{
								// グラフの種類：折れ線グラフを指定
								type: 'line',
								data: {
									// x軸の各メモリ
									labels: dateArray, //constで指定した変数（date）
									datasets: [
										{
											label: 'ベンチプレス',
											data: machineId_1Array, //constで指定した変数（machineId_1Array）
											borderColor: "#FF4B00",
											backgroundColor: "#00000000",
											lineTension: 0
										}, {
											label: 'チェストプレス',
											data: machineId_2Array, //constで指定した変数（machineId_2Array）
											borderColor: "#005AFF",
											backgroundColor: "#00000000",
											lineTension: 0
										}, {
											label: 'アブドミナルクランチ',
											data: machineId_3Array, //constで指定した変数（machineId_3Array）
											borderColor: "#03AF7A",
											backgroundColor: "#00000000",
											lineTension: 0
										},{
											label: 'ラットプルダウン',
											data: machineId_4Array, //constで指定した変数（machineId_4Array）
											borderColor: "#4DC4FF",
											backgroundColor: "#00000000",
											lineTension: 0
										},{
											label: 'レッグプレス',
											data: machineId_5Array, //constで指定した変数（machineId_5Array）
											borderColor: "#F6AA00",
											backgroundColor: "#00000000",
											lineTension: 0
										},{
											label: 'エクササイズバイク',
											data: machineId_6Array, //constで指定した変数（machineId_6Array）
											borderColor: "#FFF100",
											backgroundColor: "#00000000",
											lineTension: 0
										}],
								},

								options: {
									scales: {
										// Y軸の上がり幅、下限（初期登録+10kg）、上限（Max値+5kg）を指定
										yAxes: [{
											ticks: {
												suggestedMax: Math.max(...machineId_1Array) + 5,
												suggestedMin: 0,
												// stepSize: 5
											},
										}]
									}
								}
							});
					}).fail(function() {
						console.log('fail');
					});
		});


// マシーングラフ（丸）
$(document)
	.ready(
		function() {
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8080/MachineSetCountPieGraph',
				dataType: 'json'
			})
				.done(
					function(res) {
						console.log(res);
						const countArray = res.map(item => item.count);
						const machineNameArray = res.map(item => item.machineName);

						const options = {
							title: {
								display: true,
								text: 'マシン使用率'
							},
							plugins: {
								labels: {
									render: 'percentage',
									fontColor: 'white',
									fontSize: 14
								}
							}
						};
						var ctx = document
							.getElementById("machineSetCountPieGraph");
						new Chart(
							ctx,
							{
								// グラフの種類：円グラフを指定
								type: 'pie',
								data: {
									labels: [machineNameArray[0], machineNameArray[1], machineNameArray[2], machineNameArray[3], machineNameArray[4], machineNameArray[5]],
									datasets: [
										{
											backgroundColor: ["#FBBD8E", "#F067A6", "#FDD2B9", "#DE7C6B", "#CD4187", "#F9C1CF"],
											data: countArray, //constで指定した変数（machineId_1Array）
										}],
								},
								options: options,
							});

					}).fail(function() {
						console.log('fail');
					});
		});