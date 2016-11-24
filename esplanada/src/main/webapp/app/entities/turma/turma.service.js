(function() {
    'use strict';
    angular
        .module('colegioEsplanadaApp')
        .factory('Turma', Turma);

    Turma.$inject = ['$resource'];

    function Turma ($resource) {
        var resourceUrl =  'api/turmas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
