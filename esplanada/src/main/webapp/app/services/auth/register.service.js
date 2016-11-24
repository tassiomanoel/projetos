(function () {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
