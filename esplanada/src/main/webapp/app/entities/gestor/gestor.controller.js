(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('GestorController', GestorController);

    GestorController.$inject = ['$scope', '$state', 'Gestor'];

    function GestorController ($scope, $state, Gestor) {
        var vm = this;

        vm.gestors = [];

        loadAll();

        function loadAll() {
            Gestor.query(function(result) {
                vm.gestors = result;
            });
        }
    }
})();
