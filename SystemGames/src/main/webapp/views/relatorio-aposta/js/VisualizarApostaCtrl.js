/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('VisualizarApostaCtrl', ['$scope', '$rootScope', '$location', '$modal', 'CampeonatoFactory', 'EsporteFactory', 'GerenciarJogoFactory', function($scope, $rootScope, $location, $modal, CampeonatoFactory, EsporteFactory, GerenciarJogoFactory){
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

		$scope.openModalVizulizarDadosAposta = function () {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/relatorio-aposta/modals/modal-visualizar-dados-aposta.html',
				controller: 'VisualizarApostaCtrl',
				scope: modalScope
			});

		};


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
