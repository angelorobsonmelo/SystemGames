materialAdmin
.factory('VisualizarApostaFactory', ['$http','$q', 'GerenciarApostaFactory', function($http,$q, GerenciarApostaFactory){


	var urlRaiz = '/SystemGames/rest/aposta/';



	function buscarAposta(aposta2) {

		var retorno = $q.defer();
		var dataInicial = new Date();
		console.log(aposta2.dtInicial);
		if(typeof aposta2.dtInicial == 'undefined'){
			aposta2.dtInicial = dataInicial;
		}

		if(aposta2.dtFinal == null){
			aposta2.dtFinal = aposta2.dtInicial;
		}

		var apostaToJson = function () {
			return angular.toJson({

				"cambistaVO":{"sequencial": aposta2.seq},
				"dataInicial": aposta2.dtInicial,
				"dataFinal": aposta2.dtFinal,
				"sequencial": aposta2.seqAposta

			});
		};

		apostaToJson();

		$http.post(urlRaiz + 'apostaPorParams',apostaToJson()).success(function(resultado) {

			retorno.resolve(resultado);
			console.log(resultado);
			if(resultado == null){
				var dlg = dialogs.error('Atenção','Nenhum resultado encotrado, selecione outro filtro');
			}

		})
		.error(function(data) {
			alert('Sistema indisponível no momento...');
			console.log(data);
		});


		return retorno.promise;

	}

	function listarPorSequencial(aposta) {
		console.log(aposta);
		var retorno = $q.defer();

		var apostaToJson = function () {
			return angular.toJson({

				"sequencial": aposta

			});
		};

		apostaToJson();

		$http.post(urlRaiz + 'apostaPorSeq',apostaToJson()).success(function(resultado) {



			console.log(resultado);

			var resultadoBilhete = [];
			var verificadorVitoria = [];

			angular.forEach(resultado, function(item, index) {


				if(item.jogoApostadoVO2.tipoAposta == 'Casa') {


					if(item.resultadoJogoVO.resultadoCasa >  item.resultadoJogoVO.resultadoFora){

						item.status = 'SIM';

						resultadoBilhete.push(item);

						verificadorVitoria.push(item);


					}else if(item.resultadoJogoVO.resultadoCasa <  item.resultadoJogoVO.resultadoFora){


						item.status = 'NÃO';

						resultadoBilhete.push(item);

					}

				}

				if(item.jogoApostadoVO2.tipoAposta == 'Fora') {


					if(item.resultadoJogoVO.resultadoCasa <  item.resultadoJogoVO.resultadoFora){

						item.status = 'SIM';

						resultadoBilhete.push(item);

						verificadorVitoria.push(item);


					}else if(item.resultadoJogoVO.resultadoCasa >  item.resultadoJogoVO.resultadoFora){


						item.status = 'NÃO';

						resultadoBilhete.push(item);

					}

				}

				if(item.jogoApostadoVO2.tipoAposta == 'Empate') {


					if(item.resultadoJogoVO.resultadoCasa ==  item.resultadoJogoVO.resultadoFora){

						item.status = 'SIM';

						resultadoBilhete.push(item);

						verificadorVitoria.push(item);


					}else if(item.resultadoJogoVO.resultadoCasa !=  item.resultadoJogoVO.resultadoFora){


						item.status = 'NÃO';

						resultadoBilhete.push(item);

					}

				}

				if(item.jogoApostadoVO2.tipoAposta == 'Ambos') {


					if(item.resultadoJogoVO.resultadoCasa > 0 &&  item.resultadoJogoVO.resultadoFora > 0){

						item.status = 'SIM';

						resultadoBilhete.push(item);

						verificadorVitoria.push(item);


					}else if(item.resultadoJogoVO.resultadoCasa == 0 ||  item.resultadoJogoVO.resultadoFora == 0){


						item.status = 'NÃO';

						resultadoBilhete.push(item);

					}

				}

				if(item.jogoApostadoVO2.tipoAposta == 'Dupla Chance') {


					if(item.resultadoJogoVO.resultadoCasa !=  item.resultadoJogoVO.resultadoFora){

						item.status = 'SIM';

						resultadoBilhete.push(item);

						verificadorVitoria.push(item);


					}else if(item.resultadoJogoVO.resultadoCasa == item.resultadoJogoVO.resultadoFora){


						item.status = 'NÃO';

						resultadoBilhete.push(item);



					}

				}

				if(item.jogoApostadoVO2.tipoAposta == 'Gol e Meio') {


					var resultadoMenos = item.resultadoJogoVO.resultadoCasa -  item.resultadoJogoVO.resultadoFora;

					var resultadoMenos2 = item.resultadoJogoVO.resultadoFora - item.resultadoJogoVO.resultadoCasa;

					if(resultadoMenos >= 2 || resultadoMenos2 >= 2){

						item.status = 'SIM';

						resultadoBilhete.push(item);

						verificadorVitoria.push(item);


					}else if(resultadoMenos < 2 || resultadoMenos2 < 2){


						item.status = 'NÃO';

						resultadoBilhete.push(item);

					}

				}



			});

			console.log(verificadorVitoria);

			if(resultado.length == verificadorVitoria.length){

				GerenciarApostaFactory.inserirResultadoAposta(verificadorVitoria[0]).then(function(resposta) {

					if(resposta == 'OK'){

						console.log("salvo ok");
					}

				})

				console.log("ganhou todos");


			}



			retorno.resolve(resultadoBilhete);
			if(resultado == null){
				var dlg = dialogs.error('Atenção','Nenhum resultado encotrado, selecione outro filtro');
			}

		})
		.error(function(data) {
			alert('Sistema indisponível no momento...');
			console.log(data);
		});


		return retorno.promise;

	}

	function atualizar(aposta){

		var retorno = $q.defer();

		$http.put(urlRaiz + 'atualizar', aposta)
		.success(function(resposta) {

			retorno.resolve(resposta);
		})
		.error(function(resposta, status) {

			console.log(resposta);
			console.log(status);
		})

		return retorno.promise;

	}




	return {


		buscarAposta: buscarAposta,
		listarPorSequencial: listarPorSequencial,
		atualizar: atualizar

	}



}]);
