(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('GerenciarJogoFactory', ['$http', '$q', function($http, $q){


		var urlRaiz = '/SystemGames/rest/jogo/'; 

		function listarTodos(){

			var retorno = $q.defer();

			$http.get(urlRaiz + 'listarTodos')
			.success(function(data) {

				retorno.resolve(data);
			})
			.error(function(data, status) {

				console.log(data);
				console.log(status);
			})

			return retorno.promise;


		}

		function salvar(jogo) {


			$http.post(urlRaiz + 'salvar', jogo)
			.success(function(resposta) {

				if(resposta == 'OK'){

					alert("ok");

				}

			})
			.error(function(resposta) {

				console.log(resposta)
			})

		}


		return {

			listarTodos: listarTodos,
			salvar: salvar


		}

	}]);

}());