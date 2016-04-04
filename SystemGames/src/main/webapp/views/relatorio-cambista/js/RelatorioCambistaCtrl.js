/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('RelatorioCambistaCtrl', ['$scope', '$rootScope', '$location', '$modal',  function($scope, $rootScope, $location, $modal){
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;


		$scope.openModalDetalhesConsultaCaixa = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/relatorio-cambista/modals/modal-detalhe-consulta-caixa.html',
				controller: 'RelatorioCambistaCtrl',
				scope: modalScope
			});

		};
		
		$scope.cancel = function(){

			$scope.modalInstance.dismiss();

		};


	}]);

}());
