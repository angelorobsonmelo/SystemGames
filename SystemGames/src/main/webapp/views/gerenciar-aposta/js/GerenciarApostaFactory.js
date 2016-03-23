(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('GerenciarApostaFactory', ['$http', '$q', function($http, $q){


		var urlRaiz = '/SystemGames/rest/jogo/';


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





		return {

			buscarJogoPorParams: buscarJogoPorParams


		}

	}]);

}());