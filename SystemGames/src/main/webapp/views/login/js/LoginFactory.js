(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.factory('LoginFactory', ['$http', '$q', function($http, $q){


		var urlRaiz = '/SystemGames/rest/usuario/';



       function teste(){

         alert("fff");

	   }


		return {


			teste: teste

		}

	}]);

}());