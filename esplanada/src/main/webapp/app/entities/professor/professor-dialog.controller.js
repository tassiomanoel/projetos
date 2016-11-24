(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('ProfessorDialogController', ProfessorDialogController);

    ProfessorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Professor', 'Gestor', 'Turma', 'Aluno'];

    function ProfessorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Professor, Gestor, Turma, Aluno) {
        var vm = this;

        vm.professor = entity;
        vm.clear = clear;
        vm.save = save;
        vm.gestors = Gestor.query();
        vm.turmas = Turma.query();
        vm.alunos = Aluno.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.professor.id !== null) {
                Professor.update(vm.professor, onSaveSuccess, onSaveError);
            } else {
                Professor.save(vm.professor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('colegioEsplanadaApp:professorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
