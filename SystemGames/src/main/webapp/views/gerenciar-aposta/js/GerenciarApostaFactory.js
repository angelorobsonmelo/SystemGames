(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('GerenciarApostaFactory', ['$http', '$q', function($http, $q){


		var urlRaiz = '/SystemGames/rest/aposta/';


		function buscarJogoPorParams(jogo) {
				if(typeof jogo.campeonatoVO.sequencial == 'undefined'){
					jogo.campeonatoVO.sequencial = 0;
					console.log(jogo.campeonatoVO.sequencial);
				}

			var retorno = $q.defer();
			console.log(jogo.campeonatoVO.sequencial);
			$http.get(urlRaiz + 'listarPorParams/' + jogo.campeonatoVO.sequencial)
				.success(function(data) {



					retorno.resolve(data);



				})
				.error(function(resposta, status) {

					alert("Erro Status: " + status);
					retorno.resolve(data);
				})

			return retorno.promise;

		}


		function salvar(aposta) {

			var retorno = $q.defer();

			$http.post(urlRaiz + 'salvar', aposta)
				.success(function(resposta) {

					retorno.resolve(resposta);

				})
				.error(function(resposta, status) {

					alert("Erro Status: " + status);
					retorno.resolve(resposta);
				})

			return retorno.promise;

		}
		
		
		function inserirResultadoAposta(aposta) {

			var retorno = $q.defer();

			$http.post(urlRaiz + 'inserirResultadoAposta', aposta)
				.success(function(resposta) {

					retorno.resolve(resposta);

				})
				.error(function(resposta, status) {

					alert("Erro Status: " + status);
					retorno.resolve(resposta);
				})

			return retorno.promise;

		}
		
		/*somaValoApostado();*/
		function somaValoApostado(){

			var retorno = $q.defer();
			var dataInicial = new Date();
			var usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));
			console.log(dataInicial + '' + usuarioLogado.sequencial);
			var apostaToJson = function () {
				return angular.toJson({

					"cambistaVO":{"sequencial": usuarioLogado.sequencial},
					"dataInicial": dataInicial

				});
			};

			apostaToJson();
			$http.post(urlRaiz + 'somaValorAposta',apostaToJson()).success(function(resultado) {

				retorno.resolve(resultado);
				console.log(resultado);

					})
					.error(function(data) {
						alert('Sistema indispon�vel no momento...');
						console.log(data);
					});






			return retorno.promise;

		}






		return {

			buscarJogoPorParams: buscarJogoPorParams,
			salvar: salvar,
			inserirResultadoAposta: inserirResultadoAposta,
			somaValoApostado: somaValoApostado


		}

	}]);

}());