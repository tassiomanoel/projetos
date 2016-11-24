(function() {
    'use strict';
    angular
        .module('colegioEsplanadaApp')
        .factory('Gestor', Gestor);

    Gestor.$inject = ['$resource'];

    function Gestor ($resource) {
        var resourceUrl =  'api/gestors/:id';

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
