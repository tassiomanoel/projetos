(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('GestorDeleteController',GestorDeleteController);

    GestorDeleteController.$inject = ['$uibModalInstance', 'entity', 'Gestor'];

    function GestorDeleteController($uibModalInstance, entity, Gestor) {
        var vm = this;

        vm.gestor = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Gestor.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
