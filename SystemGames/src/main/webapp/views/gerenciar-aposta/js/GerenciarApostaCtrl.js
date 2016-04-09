/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');
	app.controller('GerenciarApostaCtrl', ['$scope', '$rootScope', '$location', '$modal', 'CampeonatoFactory', 'EsporteFactory', 'TaxaLimiteFactory','GerenciarApostaFactory', function($scope, $rootScope, $location, $modal, CampeonatoFactory, EsporteFactory, TaxaLimiteFactory,GerenciarApostaFactory) {
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;


		var items = {};
		items.jogos = [];
		$scope.items = items;
		$scope.valorTtotal = 0;

		$scope.deleteItem = function (index) {

			console.log(items.jogos[index]);

			items.jogos.splice(index, 1);
			var teste = 1;
			if(items.jogos.length === 0)
				$scope.valorTtotal = '';
			angular.forEach($scope.items.jogos, function(item, index) {


				teste *= item.valTaxa;
				$scope.valorTtotal = teste;
				console.log(teste);
			});

			console.log($scope.items.jogos);

		};

		$scope.addItem = function (jogo,valor, tipo, index) {
			for(var i=0; i<items.jogos.length; i++) {
				if(items.jogos[i].seq === jogo.jogoVO.sequencial) {
					console.log("Achou");
					console.log(i);
					$scope.deleteItem(i);
				}
			}
			items.jogos.push({
				id: items.jogos.length + 1,
				seq: jogo.jogoVO.sequencial,
				jogoApostado: jogo.jogoVO.jogo,
				dataJogo: jogo.jogoVO.dataJogoFormatadaBasica,
				horaJogo: jogo.jogoVO.horaInicialJogo,
				valTaxa: valor,
				tipoAposta: tipo

			});


			var teste = 1;
			angular.forEach($scope.items.jogos, function(item, index) {


				teste *= item.valTaxa;
				$scope.valorTtotal = teste;

			});

			console.log($scope.items.jogos);
		};





		CampeonatoFactory.listarTodos().then(function(resposta) {

			var capeonatosCopy = angular.copy(resposta);

			$scope.campeonatos = capeonatosCopy;

			console.log($scope.campeonatos);

		});

		TaxaLimiteFactory.buscarTodosPorSeqUsuario().then(function(resposta) {

			var jogoCopy = angular.copy(resposta);

			$scope.jogos = jogoCopy;

			console.log($scope.jogos);

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

		$scope.salvarAposta = function(valorTtotal,valorAposta) {
			$scope.aposta.jogoApostadoVO = {};

			var usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));

			console.log($scope.items.jogos.length);
			console.log($scope.aposta.valComissao);
			if($scope.items.jogos.length == 1){

				$scope.aposta.valComissao = $scope.aposta.valApostado * (usuarioLogado.configuracaoCambistaVO.comissao1 / 100);
				console.log($scope.aposta.valComissao);
			}
			else if($scope.items.jogos.length == 2){

				$scope.aposta.valComissao = $scope.aposta.valApostado * (usuarioLogado.configuracaoCambistaVO.comissao2 / 100);
				console.log($scope.aposta.valComissao);
			}
			else if($scope.items.jogos.length == 3){

				$scope.aposta.valComissao = (usuarioLogado.configuracaoCambistaVO.comissao3 * $scope.aposta.valApostado) / 100;
				console.log($scope.aposta.valComissao);
			}

			$scope.aposta.valRetornoPossivel = $scope.aposta.valApostado * $scope.valorTtotal;
			$scope.aposta.jogoApostadoVO = $scope.items.jogos;
			$scope.aposta.qtdJogos = $scope.items.jogos.length;
			$scope.aposta.cambistaVO = {};
			$scope.aposta.cambistaVO.sequencial = usuarioLogado.sequencial;



			console.log($scope.aposta);


			GerenciarApostaFactory.salvar($scope.aposta).then(function(data) {


				if(data == 'OK') {

					swal("Aviso!", "Salvo com Sucesso.", "success");
					$scope.aposta = '';
					$scope.items.jogos = '';

				}

			});



		};






	}]);

}());
