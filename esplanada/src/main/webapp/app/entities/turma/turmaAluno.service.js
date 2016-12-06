(function() {
    'use strict';
    angular
        .module('colegioEsplanadaApp')
        .factory('TurmaAluno', TurmaAluno);

    TurmaAluno.$inject = ['$resource'];

    function TurmaAluno ($resource) {
        var resourceUrl =  'api/turmaAluno/:id';

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
