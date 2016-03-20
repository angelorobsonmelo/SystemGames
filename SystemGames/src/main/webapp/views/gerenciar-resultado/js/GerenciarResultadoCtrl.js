/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('GerenciarResultadoCtrl', ['$scope', '$rootScope', '$location', '$modal','GerenciarResultadoFactory', 'jogo', 'GerenciarJogoFactory', function($scope, $rootScope, $location, $modal,GerenciarResultadoFactory, jogo, GerenciarJogoFactory){
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;


		console.log(jogo);
		
		
		var dataAtual = new Date;
		
		$scope.dataAtual = dataAtual;
		
		console.log(dataAtual);

		var dataJogoConvertida = new Date(jogo.dataJogo);
		
		jogo.dataJogo = dataJogoConvertida;
		
		$scope.jogo = jogo;


		$scope.salvar = function (){

			GerenciarResultadoFactory.salvar($scope.jogo).then(function(resposta) {

				if(resposta == 'OK'){

					alert("salvou")
					listartodos();
				}

			})

		}


		$scope.atualizar = function () {


			GerenciarResultadoFactory.atualizar($scope.jogo).then(function(resposta) {

				if(resposta == 'OK'){

					alert("salvou");
					listartodos();
				}

			})
		}
		
		
		$scope.remover = function () {


			$scope.jogo.resultadoJogoVO.resultadoCasa = null;
			$scope.jogo.resultadoJogoVO.resultadoFora = null;
			
			
			GerenciarResultadoFactory.atualizar($scope.jogo).then(function(resposta) {

				if(resposta == 'OK'){

					alert("Removido com Sucesso!");
					$scope.modalInstance.close();
				}

			})
		}


		$scope.cancel = function(){


			$scope.modalInstance.close();

		};


		function listartodos() { 

			GerenciarJogoFactory.listarTodos().then(function(resposta) {

				var jogosCopy = angular.copy(resposta);

				$rootScope.jogos = jogosCopy;

				console.log($scope.jogos);

			});

		}


	}]);

}());
