(function () {

    'use strict';

    var app = angular.module('materialAdmin');

    app.factory('LoginFactory', ['$http', '$q', '$location', function ($http, $q, $location) {


        var urlRaiz;
        var redirecionamento;


        function autenticar(usuario, url) {


            if (url == '/login-cambista') {


                urlRaiz = '/SystemGames/rest/cambista/';




            }
            else if (url == '/login-usuario') {

                urlRaiz = '/SystemGames/rest/usuario/';



            }

            $http.post(urlRaiz + 'autenticar', usuario)
                .success(function (resposta) {

                    if (resposta != '') {

                        if (resposta.tipoUsuarioVO.sequencial == 2) {

                            localStorage.setItem('usuarioLogado', angular.toJson(resposta));

                            $location.path('/home');

                        } else if (resposta.tipoUsuarioVO.sequencial == 3) {

                            $location.path('/gerenciar/gerenciar-aposta');
                            localStorage.setItem('usuarioLogado', angular.toJson(resposta));

                        }

                    } else {

                        swal("Aviso!", "Usuário Inválido", "warning");

                    }

                })
                .error(function (error, status) {

                    console.log(error);
                })


        }


        return {
            autenticar: autenticar

        }

    }]);

}());