/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');
	app.controller('GerenciarApostaCtrl', ['$scope', '$rootScope', '$location', '$modal', 'CampeonatoFactory', 'EsporteFactory', 'GerenciarJogoFactory','GerenciarApostaFactory', function($scope, $rootScope, $location, $modal, CampeonatoFactory, EsporteFactory, GerenciarJogoFactory,GerenciarApostaFactory) {
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;





		$scope.salvar = function() {

			console.log($scope.jogo);
			
			
			GerenciarJogoFactory.salvar($scope.jogo);
			
		};



		GerenciarJogoFactory.listarTodos().then(function(resposta) {

			var jogosCopy = angular.copy(resposta);

			$scope.jogos = jogosCopy;

			console.log($scope.jogos);

		});


		var items = {};
		items.jogos = [];
		$scope.items = items;
		$scope.valorTtotal = 0;
		$scope.valorAposta = 0;

		$scope.deleteItem = function (index) {

			console.log(items.jogos[index]);

			items.jogos.splice(index, 1);
			var teste = 1;
			if(items.jogos.length === 0)
				$scope.valorTtotal = '';
			angular.forEach($scope.items.jogos, function(item, index) {


				teste *= item.aposta;
				$scope.valorTtotal = teste;
				console.log(teste);
			});

			console.log($scope.items.jogos);

		};
		
		$scope.addItem = function (jogo,valor, tipo, index) {
			for(var i=0; i<items.jogos.length; i++) {
				if(items.jogos[i].id === jogo.sequencial) {
					console.log("Achou");
					$scope.deleteItem(index);
				}
			}
			items.jogos.push({
				id: jogo.sequencial,
				jogo: jogo.jogo,
				dataJogo: jogo.dataJogoFormatadaBasica,
				aposta: valor,
				tipo: tipo

			});
			for(var i=0; i<items.jogos.length; i++) {
				if(items.jogos[i].id === jogo.sequencial) {
					console.log("Achou");
				}
			}
			
			var teste = 1;
			angular.forEach($scope.items.jogos, function(item, index) {


                  teste *= item.aposta;
				$scope.valorTtotal = teste;
				console.log(teste);
			});

			console.log($scope.items.jogos);
		};

		$scope.addItem = function (jogo,valor, tipo, index) {
			for(var i=0; i<items.jogos.length; i++) {
				if(items.jogos[i].id === jogo.sequencial) {
					console.log("Achou");
					console.log(i);
					$scope.deleteItem(i);
				}
			}
			items.jogos.push({
				id: jogo.sequencial,
				jogo: jogo.jogo,
				dataJogo: jogo.dataJogoFormatadaBasica,
				aposta: valor,
				tipo: tipo

			});


			var teste = 1;
			angular.forEach($scope.items.jogos, function(item, index) {


				teste *= item.aposta;
				$scope.valorTtotal = teste;
				console.log(teste);
			});

			console.log($scope.items.jogos);
		};





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

		$scope.listarJogoPorCampeonato = function(){

			GerenciarApostaFactory.buscarJogoPorParams($scope.jogo).then(function(dado){
				var jogoPorCampeonatoCopy = angular.copy(dado);
				$scope.jogos = jogoPorCampeonatoCopy;
				console.log($scope.jogos);


			});
		};
		




	}]);

}());
