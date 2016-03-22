materialAdmin
        .factory('gerenciarCambistaFactory', ['$http','$q', function($http,$q){


        var urlRaiz = '/SystemGames/rest/cambista/';



        function salvarUsuarioCambista(usuario) {

            var retorno = $q.defer();

            $http.post(urlRaiz + 'salvarCambista', usuario)
                .success(function(resposta) {



                    retorno.resolve(resposta);



                })
                .error(function(resposta, status) {

                    alert("Erro Status: " + status);
                    retorno.resolve(resposta);
                })

            return retorno.promise;

        }

        function alterarUsuarioCambista(usuario) {

            var retorno = $q.defer();

            $http.put(urlRaiz + 'alterar', usuario)
                .success(function(resposta) {



                    retorno.resolve(resposta);



                })
                .error(function(resposta, status) {

                    alert("Erro Status: " + status);
                    retorno.resolve(resposta);
                })

            return retorno.promise;

        }

        function remover(usuario) {

            var retorno = $q.defer();

            $http.delete(urlRaiz + 'removerUsuario/'+ usuario.sequencial+'/'+ usuario.usuariosequencial)
                .success(function(resposta) {



                    retorno.resolve(resposta);



                })
                .error(function(resposta, status) {

                    alert("Erro Status: " + status);
                    retorno.resolve(resposta);
                })

            return retorno.promise;

        }



        function buscarUsuariosCambistas() {

            var retorno = $q.defer();

            $http.get(urlRaiz + 'listarTodosCambista')
                .success(function(resultado) {

                    retorno.resolve(resultado);

                })
                .error(function(data) {
                    alert('Sistema indisponível no momento...');
                    console.log(data);
                });


            return retorno.promise;

        }




            return {


                salvarUsuarioCambista: salvarUsuarioCambista,
                buscarUsuariosCambistas: buscarUsuariosCambistas,
                alterarUsuarioCambista: alterarUsuarioCambista,
                remover: remover

            }



        }]);
