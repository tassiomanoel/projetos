(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaDialogController', TurmaDialogController);

    TurmaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Turma', 'Gestor'];

    function TurmaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Turma, Gestor) {
        var vm = this;

        vm.turma = entity;
        vm.clear = clear;
        vm.save = save;
        vm.gestors = Gestor.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.turma.id !== null) {
                Turma.update(vm.turma, onSaveSuccess, onSaveError);
            } else {
                Turma.save(vm.turma, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('colegioEsplanadaApp:turmaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
