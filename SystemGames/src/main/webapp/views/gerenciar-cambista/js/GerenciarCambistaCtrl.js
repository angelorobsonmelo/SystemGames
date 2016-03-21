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
			listarUsuariosCambistas();

		};

		function AtualizarCambista($scope, $modalInstance, cambista, gerenciarCambistaFactory) {


			$scope.usuario = cambista;


			$scope.salvar = function() {

				console.log();

				gerenciarCambistaFactory.salvarUsuarioCambista($scope.usuario).then(function(resposta){


					if(resposta == "OK"){


						listartodos();
						$scope.modalInstance.dismiss();
						swal("Aviso!", "Cadastrado com Sucesso.", "success");
					}


				});
			}


			function listartodos() {

				gerenciarCambistaFactory.buscarUsuariosCambistas().then(function(resposta) {

					var cambistaCopy = angular.copy(resposta);

					$rootScope.cambista = cambistaCopy;

					console.log($scope.cambista);

				});

			}


			$scope.close = function () {
				$modalInstance.dismiss('cancel');
			};
		};

		$scope.editarCambista = function(cambista){

			var modalScope = $rootScope.$new();
			modalScope.modalInstance = $modal.open({
				templateUrl: 'views/gerenciar-cambista/modals/modal-editar-cambista.html',
				controller: AtualizarCambista,
				resolve: {
					cambista: function () {
						return cambista;
					}
				}
			});

		}

		$scope.cadastrarCambista = function() {

			gerenciarCambistaFactory.salvarUsuarioCambista($scope.usuario).then(function(data) {


				if(data) {

					alert("salvo com sucesso!");
					$scope.usuario = '';
					listarUsuariosCambistas();
				}

			});


		};

		$scope.cadastrarUsuario = function() {

			gerenciarCambistaFactory.salvarUsuarioCambista($scope.usuario).then(function(data) {


				if(data) {

					alert("salvo com sucesso!");
					$scope.usuario = '';
					/* carregaruUsuarios();*/
				}

			})

		};

		listarUsuariosCambistas();
		function listarUsuariosCambistas() {

			gerenciarCambistaFactory.buscarUsuariosCambistas().then(function(dado) {

				$scope.cambistas = dado;
				console.log(dado);

			})

		}


	}]);

}());
