materialAdmin
        .factory('gerenciarCambistaFactory', ['$http','$q', function($http,$q){


        var urlRaiz = '/SystemGames/rest/cambista/';



        function salvar(usuario) {

            var retorno = $q.defer();

            $http.post(urlRaiz + 'salvar', usuario)
                .success(function(resposta) {

                    retorno.resolve(resposta);

                })
                .error(function(resposta, status) {

                    alert("Erro Status: " + status);
                    retorno.resolve(resposta);
                })

            return retorno.promise;

        }



        function remover(cambista) {

            var retorno = $q.defer();

            $http.delete(urlRaiz + 'remover/'+ cambista.sequencial)
                .success(function(resposta) {



                    retorno.resolve(resposta);



                })
                .error(function(resposta, status) {

                    alert("Erro Status: " + status);
                    retorno.resolve(resposta);
                })

            return retorno.promise;

        }



        function pesquisarPorSeqUsuario() {

            var retorno = $q.defer();

            var usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));

            $http.get(urlRaiz + 'pesquisarPorSeqUsuario/' + usuarioLogado.sequencial)
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


                salvar: salvar,
                pesquisarPorSeqUsuario: pesquisarPorSeqUsuario,
                remover: remover

            }



        }]);
