(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('LoginFactory', ['$http', '$q', function($http, $q){


		var urlRaiz;



       function autenticar(usuario, url){


		   if(url.pathname == '/SystemGames/login_cambista.html') {


               urlRaiz =  '/SystemGames/rest/cambista/';
		   }
		   else if(url.pathname == '/SystemGames/'){

			   urlRaiz =  '/SystemGames/rest/usuario/';

		   }
		   
		   $http.post(urlRaiz + 'autenticar', usuario)
			   .success(function (resposta) {

				   if(resposta.tipoUsuarioVO.sequencial == 2){

					   alert("tela matuto")

				   }else if(resposta.tipoUsuarioVO.sequencial == 3){

					   alert("tela cambista")

				   }
				   
			   })
			   .error(function (error, status) {

				   console.log(error);
			   })


	   }


		return {
			autenticar: autenticar

		}

	}]);

}());