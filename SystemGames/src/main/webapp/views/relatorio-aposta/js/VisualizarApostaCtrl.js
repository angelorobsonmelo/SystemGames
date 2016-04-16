/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('VisualizarApostaCtrl', ['$scope', '$rootScope', '$location', '$modal', 'CampeonatoFactory', 'EsporteFactory', 'GerenciarJogoFactory','VisualizarApostaFactory','gerenciarCambistaFactory', function($scope, $rootScope, $location, $modal, CampeonatoFactory, EsporteFactory, GerenciarJogoFactory,VisualizarApostaFactory,gerenciarCambistaFactory){
		$rootScope.titulo = "jogos";
		$rootScope.activetab = $location.path();
		$rootScope.esconderHeader = true;

		$scope.ToggleCampeonato = false;

		$scope.aposta = {
			singleSelect: null
		};



		$scope.listarAposta = function(aposta){

			var usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));
			console.log(usuarioLogado);
			if(usuarioLogado.tipoUsuarioVO.sequencial == 2){
				aposta.seq = aposta.sequencial
			}else if(usuarioLogado.tipoUsuarioVO.sequencial == 3){
				aposta.seq = usuarioLogado.sequencial
			}

			VisualizarApostaFactory.buscarAposta(aposta).then(function(dado){

				var myDate = new Date(aposta.dtInicial);

				myDate = new Date(myDate.getUTCFullYear(), myDate.getUTCMonth(), myDate.getUTCDate());

				aposta.dtInicial = myDate;

				var myDateF = new Date(aposta.dtFinal);

				myDateF = new Date(myDateF.getUTCFullYear(), myDateF.getUTCMonth(), myDateF.getUTCDate());

				aposta.dtFinal = myDateF;

				$scope.apostas = dado;
				console.log(dado);
				var somaTotalaposta = 0;
				var somaComissao = 0;
				var somaTotalPago = 0;
				$scope.numAposta = dado.length;
				var somaTotalliquido = 0;
				angular.forEach(dado, function(item, index) {


					somaTotalaposta += dado[index].valApostado;
					somaComissao += dado[index].valComissao;


				});
				$scope.tt = somaTotalaposta;
				$scope.tcomissao = somaComissao;
				$scope.tpago = somaTotalPago;
				somaTotalliquido = somaTotalaposta - (somaComissao + somaTotalPago);
				$scope.tliquido = somaTotalliquido;

			});
		};

		pesquisarPorSeqUsuario();
		function pesquisarPorSeqUsuario() {

			gerenciarCambistaFactory.pesquisarPorSeqUsuario().then(function(dado) {

				$rootScope.cambistas = dado;
				console.log(dado);


			})

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

		function DetalheAposta($scope, $uibModalInstance, aposta, VisualizarApostaFactory) {


			$scope.aposta = aposta;
			
			console.log($scope.aposta);

			listartodos();

			function listartodos() {

				VisualizarApostaFactory.listarPorSequencial($scope.aposta.sequencial).then(function(data) {


					var apostaCopy = angular.copy(data);

					$rootScope.detalhes = apostaCopy;

					console.log($scope.detalhes);
					
				});
				$scope.atualiza = function(aposta){
					console.log(aposta);
				VisualizarApostaFactory.atualizar($scope.aposta).then(function(resposta) {

					if(resposta == 'OK'){

						swal("Aviso!", "Alterado com Sucesso.", "success");

					}

				});
				}

			};



			$scope.cancel = function () {
				$uibModalInstance.dismiss('cancel');
			};
		};

		$scope.openModalVizulizarDadosAposta = function (aposta) {

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/relatorio-aposta/modals/modal-visualizar-dados-aposta.html',
				controller: DetalheAposta,
				scope: modalScope,
				resolve: {
					aposta: function () {
						return aposta;
					}
				}
			});
			$scope.cancel = function () {
				$uibModalInstance.dismiss('cancel');
			};

		};


	}]);

}());
