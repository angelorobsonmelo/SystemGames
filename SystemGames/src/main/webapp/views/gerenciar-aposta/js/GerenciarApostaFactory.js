(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('GerenciarApostaFactory', ['$http', '$q', function($http, $q){


		var urlRaiz = '/SystemGames/rest/jogo/';

		function buscarJogoCampeonato(jogo) {

			var retorno = $q.defer();

			var entradaToJson = function () {
				return angular.toJson({

					'jogoVO':{
						"campeonatoVO": {"sequencial": jogo.campeonatoVO.sequencial}
					}

				});
			};

			entradaToJson();

			$http.get(urlRaiz + 'listarPorParams',entradaToJson()).success(function(resultado) {

				retorno.resolve(resultado);
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

			buscarJogoCampeonato: buscarJogoCampeonato,
			buscarJogoPorParams: buscarJogoPorParams


		}

	}]);

}());