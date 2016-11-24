(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaDeleteController',TurmaDeleteController);

    TurmaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Turma'];

    function TurmaDeleteController($uibModalInstance, entity, Turma) {
        var vm = this;

        vm.turma = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Turma.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
