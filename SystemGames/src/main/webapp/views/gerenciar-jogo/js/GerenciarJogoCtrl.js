/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('GerenciarJogosCtrl', ['$scope', '$rootScope', '$location', '$modal', 'CampeonatoFactory', 'EsporteFactory', 'GerenciarJogoFactory', function($scope, $rootScope, $location, $modal, CampeonatoFactory, EsporteFactory, GerenciarJogoFactory){
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;



		$scope.salvar = function() {

			console.log($scope.jogo);

			GerenciarJogoFactory.salvar($scope.jogo).then(function(resposta){


				if(resposta == "OK"){


					listartodos();
					$scope.modalInstance.dismiss();
					alert('Cadastrado com Sucesso!');
				}


			});
		} 



		listartodos();

		function listartodos() { 

			GerenciarJogoFactory.listarTodos().then(function(resposta) {

				var jogosCopy = angular.copy(resposta);

				$rootScope.jogos = jogosCopy;

				console.log($scope.jogos);

			});

		}

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


		$scope.openModalPossoAjudarPrincipal = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-jogo/modals/modal-gerenciar-jogo.html',
				controller: 'GerenciarJogosCtrl',
				scope: modalScope
			});

		};

		$scope.openModalGerenciarEsporte = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-jogo/modals/modal-gerenciar-esporte.html',
				controller: 'GerenciarJogosCtrl',
				scope: modalScope
			});

		};

		$scope.openModalGerenciarCampeonato = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-jogo/modals/modal-gerenciar-campeonato.html',
				controller: 'GerenciarJogosCtrl',
				scope: modalScope
			});

		};
		
		$scope.openModalGerenciarResultadoJogo = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-jogo/modals/modal-gerenciar-resultado-jogo.html',
				controller: 'GerenciarJogosCtrl',
				scope: modalScope
			});

		};

		$scope.cancel = function(){

			$scope.modalInstance.dismiss();

		};




	}]);

}());
