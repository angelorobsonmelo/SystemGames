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

			$scope.modalInstance.cancel();

		};

		$scope.cadastrarCambista = function() {

			gerenciarCambistaFactory.salvarUsuarioCambista($scope.usuario).then(function(data) {


				if(data) {

					alert("salvo com sucesso!");
					$scope.usuario = '';
					/* carregaruUsuarios();*/
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
