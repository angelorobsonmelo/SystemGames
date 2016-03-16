/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('GerenciarCambistaCtrl', ['$scope', '$rootScope', '$location', '$modal', function($scope, $rootScope, $location, $modal){
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;


		$scope.openModalGerenciarCambista = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-cambista/modals/modal-gerenciar-cambista.html',
				controller: 'GerenciarCambistaCtrl',
				scope: modalScope
			});

		};

		$scope.cancel = function(){

			$scope.modalInstance.dismiss();

		};


	}]);

}());
