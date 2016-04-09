(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('GerenciarApostaFactory','VisualizarApostaFactory', ['$http', '$q', function($http, $q,VisualizarApostaFactory){


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

		function comparar(aposta){
			var usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));
			VisualizarApostaFactory.buscarAposta(aposta).then(function(data) {


			});
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





		return {

			buscarJogoPorParams: buscarJogoPorParams,
			salvar: salvar


		}

	}]);

}());