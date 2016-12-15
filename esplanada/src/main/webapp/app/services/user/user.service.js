(function () {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .factory('User', User);

    User.$inject = ['$resource'];

    function User ($resource) {
        var service = $resource('api/users/:login/:param', {
        	param : '@param'
        }, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'},
            'getAlunosPorTurma':{ 
            	method:'GET',
            	params: {
            		param: 'getAlunosPorTurma' 
            	},
            	isArray: true
            },
            'salvarMediaFinalAluno':{ 
            	method:'PUT',
            	params: {
            		param: 'salvarMediaFinalAluno' 
            	},
            	transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'salvarAnotacaoAluno':{ 
            	method:'GET',
            	params: {
            		param: 'salvarAnotacaoAluno' 
            	},
            	transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });

        return service;
    }
})();
