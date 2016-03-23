/**
 * Created by Ademar on 20/01/2016.
 */
(function() {

	'use strict';

	var app = angular.module('materialAdmin');

	app.controller('GerenciarCambistaCtrl', ['$scope', '$rootScope', '$location', '$modal','gerenciarCambistaFactory', function($scope, $rootScope, $location, $modal,gerenciarCambistaFactory){
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


			$scope.modalInstance.close();


		};

		function AtualizarCambista($scope, $modalInstance, cambista, gerenciarCambistaFactory) {


			$scope.cambista = cambista;

			$scope.salvar = function() {
				gerenciarCambistaFactory.salvar($scope.cambista).then(function(data) {


					if(data == "OK") {

						swal("Aviso!", "Alterado com Sucesso.", "success");

						pesquisarPorSeqUsuario();
					}

				});


			};



			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};
		};

		$scope.editarCambista = function(cambista){

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-cambista/modals/modal-gerenciar-cambista.html',
				controller: AtualizarCambista,
				resolve: {
					cambista: function () {
						return cambista;
					}
				}
			});

		}

		$scope.salvar = function() {
			$scope.cambista.usuarioVO = {};
			$scope.cambista.usuarioVO .sequencial = 1;

			console.log($scope.cambista);


			gerenciarCambistaFactory.salvar($scope.cambista).then(function(data) {


				if(data == 'OK') {

					swal("Aviso!", "Salvo com Sucesso.", "success");
					pesquisarPorSeqUsuario();

				}

			});



		};



		$scope.remover = function (cambista) {

			swal({
				title: "Deseja Realmente Remover?",
				text: "",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Sim",
				closeOnConfirm: false
			}, function(){

				gerenciarCambistaFactory.remover(cambista).then(function(resposta){


					if(resposta == "OK"){

						swal("Aviso!", "Removido com Sucesso.", "success");

						pesquisarPorSeqUsuario();
					}


				});

			});
		}



		pesquisarPorSeqUsuario();
		function pesquisarPorSeqUsuario() {

			gerenciarCambistaFactory.pesquisarPorSeqUsuario().then(function(dado) {

				$rootScope.cambistas = dado;
				console.log(dado);

			})

		}


	}]);

}());
