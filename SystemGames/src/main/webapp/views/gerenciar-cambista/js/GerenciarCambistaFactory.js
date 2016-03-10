materialAdmin
        .factory('gerenciarCambistaFactory', ['$http','$q', function($http,$q){


            function salvarConfiguracaoCambista(cambista){
                var retorno = $q.defer();

                var configuracaoCambistaToJson = function () {

                    return angular.toJson({
                        'limiteMaximoVendaDiario': cambista.limiteDiario ,
                        "limiteMaximoVendaIndividual":cambista.limiteIndividual,
                        "observacao":  cambista.justificativa,
                        "codigoUsuario":{"sequencial":  cambista.numSequencial},
                        "comissao1":  cambista.comissaoUm,
                        "comissao2":  cambista.comissaoDois,
                        "comissao3":  cambista.comissaoTres


                    });
                };

                configuracaoCambistaToJson();

                $http.post('rest/cambista/salvarCambista', configuracaoCambistaToJson()).success(function(){
                    retorno.resolve(true)
                })
                    .error(function(data){
                        retorno.resolve(false);
                        console.log(data);
                    });
                return retorno.promise;
            }

            function salvarUsuarioCambista(usuario){
                var retorno = $q.defer();

                var usuarioCambistaToJson = function () {

                    return angular.toJson({
                        "codigoTipoUsuario":{"sequencial": 2} ,
                        "nomeUsuario":  usuario.nomUsuario,
                        "email":  usuario.dscEmail,
                        "login":  usuario.dscLogin,
                        "senha": usuario.dscSenha ,
                        "cpf":  usuario.numCpf,
                        "numeroRg":  usuario.numRg,
                        "contato": usuario.numContato ,
                        "endereco":  usuario.nomEndereco,
                        "numeroEndereco":  usuario.numEndereco,
                        "complemento": usuario.dscComplemento ,
                        "bairro":  usuario.dscBairro,
                        "cidade":  usuario.dscCidade,
                        "cep":  usuario.numCep,
                        "uf":  usuario.dscUf,
                        "configuracaoCambistaVO":{
                            "limiteMaximoVendaDiario": usuario.limiteDiario ,
                            "limiteMaximoVendaIndividual":usuario.limiteIndividual,
                            "observacao":  usuario.jus,
                            "comissao1":  usuario.comissaoUm,
                            "comissao2":  usuario.comissaoDois,
                            "comissao3":  usuario.comissaoTres
                        }

                    });
                };

                usuarioCambistaToJson();

                $http.post('rest/usuario/salvarUsuario', usuarioCambistaToJson()).success(function(){
                    retorno.resolve(true)
                })
                    .error(function(data){
                        retorno.resolve(false);
                        console.log(data);
                    });
                return retorno.promise;
            }

        function buscarUsuariosCambistas() {

            var retorno = $q.defer();

            $http.get('rest/cambista/listarTodosCambista')
                .success(function(resultado) {

                    retorno.resolve(resultado);

                })
                .error(function(data) {
                    alert('Sistema indisponível no momento...');
                    console.log(data);
                });


            return retorno.promise;

        }

          /*  function buscarUf() {

                var retorno = $q.defer();

                $http.get('rest/guia/listaUf')
                    .success(function(resultado) {

                        retorno.resolve(resultado);

                    })
                    .error(function(data) {
                        alert('Sistema indispon�vel no momento...');
                        console.log(data);
                    });


                return retorno.promise;

            }*/


            return {

                salvarConfiguracaoCambista: salvarConfiguracaoCambista,
                salvarUsuarioCambista: salvarUsuarioCambista,
                buscarUsuariosCambistas: buscarUsuariosCambistas

            }



        }]);



/*
 'cabecalhoConsulta':{'registroANS':guiaConsulta.fabrica,'numeroGuiaPrestador': guiaConsulta.numGuiaPrestador},*/
