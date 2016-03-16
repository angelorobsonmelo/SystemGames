/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('GerenciarApostaCtrl', ['$scope', '$rootScope', '$location', '$modal', 'CampeonatoFactory', 'EsporteFactory', 'GerenciarJogoFactory', function($scope, $rootScope, $location, $modal, CampeonatoFactory, EsporteFactory, GerenciarJogoFactory){
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;



		$scope.salvar = function() {

			console.log($scope.jogo);
			
			
			GerenciarJogoFactory.salvar($scope.jogo);
			
		}
		
		
		
		GerenciarJogoFactory.listarTodos().then(function(resposta) {

			var jogosCopy = angular.copy(resposta);

			$scope.jogos = jogosCopy;

			console.log($scope.jogos);

		})
		

		CampeonatoFactory.listarTodos().then(function(resposta) {

			var capeonatosCopy = angular.copy(resposta);

			$scope.campeonatos = capeonatosCopy;

			console.log($scope.campeonatos);

		});


		EsporteFactory.listarTodos().then(function(resposta) {

			var esportesCopy = angular.copy(resposta);

			$scope.esportes = esportesCopy;

			console.log($scope.esportes);

		});
		
		

		$scope.openModalInserirAposta = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-aposta/modals/modal-inserir-aposta.html',
				controller: 'GerenciarApostaCtrl',
				scope: modalScope
			});

		};

		$scope.cancel = function(){

			$scope.modalInstance.cancel();

		};
		
		


	}]);

}());
