(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaController', TurmaController);

    TurmaController.$inject = ['$scope', '$state', 'Turma'];

    function TurmaController ($scope, $state, Turma) {
        var vm = this;

        vm.turmas = [];

        loadAll();

        function loadAll() {
            Turma.query(function(result) {
                vm.turmas = result;
            });
        }
    }
})();
