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
                console.log(usuario.sequencial);

                if(typeof usuario.sequencial != 'undefined'){


                    return angular.toJson({
                        "codigoTipoUsuario":{"sequencial": 2} ,
                        "nomeUsuario":  usuario.nomeUsuario,
                        "email":  usuario.email,
                        "login":  usuario.login,
                        "senha": usuario.senha ,
                        "cpf":  usuario.cpf,
                        "numeroRg":  usuario.numeroRg,
                        "contato": usuario.contato ,
                        "endereco":  usuario.endereco,
                        "numeroEndereco":  usuario.numeroEndereco,
                        "complemento": usuario.complemento ,
                        "bairro":  usuario.bairro,
                        "cidade":  usuario.cidade,
                        "cep":  usuario.cep,
                        "uf":  usuario.uf,
                            "limiteMaximoVendaDiario": usuario.limiteMaximoVendaDiario ,
                            "limiteMaximoVendaIndividual":usuario.limiteMaximoVendaIndividual,
                            "observacao":  usuario.observacao,
                            "comissao1":  usuario.comissao1,
                            "comissao2":  usuario.comissao2,
                            "comissao3":  usuario.comissao3


                    });

                }else{

                var usuarioCambistaToJson = function () {

                    return angular.toJson({
                        "codigoTipoUsuario":{"sequencial": 2} ,
                        "nomeUsuario":  usuario.nomeUsuario,
                        "email":  usuario.email,
                        "login":  usuario.login,
                        "senha": usuario.senha ,
                        "cpf":  usuario.cpf,
                        "numeroRg":  usuario.numeroRg,
                        "contato": usuario.contato ,
                        "endereco":  usuario.endereco,
                        "numeroEndereco":  usuario.numeroEndereco,
                        "complemento": usuario.complemento ,
                        "bairro":  usuario.bairro,
                        "cidade":  usuario.cidade,
                        "cep":  usuario.cep,
                        "uf":  usuario.uf,
                        "configuracaoCambistaVO":{
                            "limiteMaximoVendaDiario": usuario.configuracaoCambistaVO.limiteMaximoVendaDiario ,
                            "limiteMaximoVendaIndividual":usuario.configuracaoCambistaVO.limiteMaximoVendaIndividual,
                            "observacao":  usuario.configuracaoCambistaVO.observacao,
                            "comissao1":  usuario.configuracaoCambistaVO.comissao1,
                            "comissao2":  usuario.configuracaoCambistaVO.comissao2,
                            "comissao3":  usuario.configuracaoCambistaVO.comissao3
                        }

                    });
                };
                }
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
