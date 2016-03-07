(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('CampeonatoFactory', ['$http', '$q', function($http, $q){


		var urlRaiz = '/SystemGames/rest/campeonato/'; 

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


		return {

			listarTodos: listarTodos


		}

	}]);

}());