(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('GestorDialogController', GestorDialogController);

    GestorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Gestor', 'Turma', 'Professor', 'Aluno'];

    function GestorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Gestor, Turma, Professor, Aluno) {
        var vm = this;

        vm.gestor = entity;
        vm.clear = clear;
        vm.save = save;
        vm.turmas = Turma.query();
        vm.professors = Professor.query();
        vm.alunos = Aluno.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.gestor.id !== null) {
                Gestor.update(vm.gestor, onSaveSuccess, onSaveError);
            } else {
                Gestor.save(vm.gestor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('colegioEsplanadaApp:gestorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
