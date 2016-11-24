(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('AlunoDialogController', AlunoDialogController);

    AlunoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Aluno', 'Turma', 'Gestor', 'Professor'];

    function AlunoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Aluno, Turma, Gestor, Professor) {
        var vm = this;

        vm.aluno = entity;
        vm.clear = clear;
        vm.save = save;
        vm.turmas = Turma.query();
        vm.gestors = Gestor.query();
        vm.professors = Professor.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.aluno.id !== null) {
                Aluno.update(vm.aluno, onSaveSuccess, onSaveError);
            } else {
                Aluno.save(vm.aluno, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('colegioEsplanadaApp:alunoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
