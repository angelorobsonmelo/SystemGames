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


		 listarCampeonatos();

		function listarCampeonatos(){

			CampeonatoFactory.listarTodos().then(function(resposta) {

				var capeonatosCopy = angular.copy(resposta);

				$scope.campeonatos = capeonatosCopy;

				console.log($scope.campeonatos);

			});
		}

		listarEsportes();
		
		function listarEsportes(){

			EsporteFactory.listarTodos().then(function(resposta) {

				var esportesCopy = angular.copy(resposta);

				$scope.esportes = esportesCopy;

				console.log($scope.esportes);

			});
		}

		$scope.openModalInserirJogo = function () {

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
				templateUrl: 'views/esportes/modals/modal-gerenciar-esporte.html',
				controller: 'EsportesCtrl',
				scope: modalScope
			});

		};

		$scope.openModalGerenciarCampeonato = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/campeonato/modals/modal-gerenciar-campeonato.html',
				controller: 'CampeonatoCtrl',
				scope: modalScope
			});

		};

		$scope.openModalGerenciarResultadoJogo = function (jogo) {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-resultado/modals/modal-gerenciar-resultado-jogo.html',
				controller: 'GerenciarResultadoCtrl',
				scope: modalScope,
				resolve: {
					jogo: function () {
						return jogo;
					}
				}
			});

		};

		$scope.editarJogo = function(jogo){

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-jogo/modals/modal-gerenciar-jogo.html',
				controller: AtualizarJogo,
				resolve: {
					jogo: function () {
						return jogo;
					}
				}
			});

		}

		function AtualizarJogo($scope, $modalInstance, jogo, EsporteFactory, CampeonatoFactory) {

			
			$scope.jogo = jogo;
			
			
			$scope.salvar = function() {

				console.log();

				GerenciarJogoFactory.salvar($scope.jogo).then(function(resposta){


					if(resposta == "OK"){


						listartodos();
						$scope.modalInstance.dismiss();
						alert('Cadastrado com Sucesso!');
					}


				});
			} 
			
			
			function listartodos() { 

				GerenciarJogoFactory.listarTodos().then(function(resposta) {

					var jogosCopy = angular.copy(resposta);

					$rootScope.jogos = jogosCopy;

					console.log($scope.jogos);

				});

			}
			
			

			EsporteFactory.listarTodos().then(function(resposta) {

				var esportesCopy = angular.copy(resposta);

				$scope.esportes = esportesCopy;

				console.log($scope.esportes);

			});
			
			
			CampeonatoFactory.listarTodos().then(function(resposta) {

				var capeonatosCopy = angular.copy(resposta);

				$scope.campeonatos = capeonatosCopy;

				console.log($scope.campeonatos);

			});


			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};
		};



		$scope.cancel = function(){

			$scope.modalInstance.dismiss();

		};




	}]);

}());
