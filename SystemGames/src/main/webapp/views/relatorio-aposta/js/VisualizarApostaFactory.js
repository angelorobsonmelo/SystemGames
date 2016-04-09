materialAdmin
    .factory('VisualizarApostaFactory', ['$http','$q', function($http,$q){


        var urlRaiz = '/SystemGames/rest/aposta/';



        function buscarAposta(aposta2) {

            var retorno = $q.defer();
            var dataInicial = new Date();
            console.log(aposta2.dtInicial);
            if(typeof aposta2.dtInicial == 'undefined'){
                aposta2.dtInicial = dataInicial;
            }

            if(aposta2.dtFinal == null){
                aposta2.dtFinal = aposta2.dtInicial;
            }

            var apostaToJson = function () {
                return angular.toJson({

                    "cambistaVO":{"sequencial": aposta2.seq},
                    "dataInicial": aposta2.dtInicial,
                    "dataFinal": aposta2.dtFinal,
                    "sequencial": aposta2.seqAposta

                });
            };

            apostaToJson();

            $http.post(urlRaiz + 'apostaPorParams',apostaToJson()).success(function(resultado) {

                retorno.resolve(resultado);
                console.log(resultado);
                if(resultado == null){
                    var dlg = dialogs.error('Atenção','Nenhum resultado encotrado, selecione outro filtro');
                }

            })
                .error(function(data) {
                    alert('Sistema indisponível no momento...');
                    console.log(data);
                });


            return retorno.promise;

        }

        function listarPorSequencial(aposta) {
            console.log(aposta);
            var retorno = $q.defer();

            var apostaToJson = function () {
                return angular.toJson({

                    "sequencial": aposta

                });
            };

            apostaToJson();

            $http.post(urlRaiz + 'apostaPorSeq',apostaToJson()).success(function(resultado) {

                retorno.resolve(resultado);
                if(resultado == null){
                    var dlg = dialogs.error('Atenção','Nenhum resultado encotrado, selecione outro filtro');
                }

            })
                .error(function(data) {
                    alert('Sistema indisponível no momento...');
                    console.log(data);
                });


            return retorno.promise;

        }
        
        function atualizar(aposta){

    		var retorno = $q.defer();

    		$http.put(urlRaiz + 'atualizar', aposta)
    		.success(function(resposta) {

    			retorno.resolve(resposta);
    		})
    		.error(function(resposta, status) {

    			console.log(resposta);
    			console.log(status);
    		})

    		return retorno.promise;

    	}




        return {


            buscarAposta: buscarAposta,
            listarPorSequencial: listarPorSequencial,
            atualizar: atualizar

        }



    }]);
