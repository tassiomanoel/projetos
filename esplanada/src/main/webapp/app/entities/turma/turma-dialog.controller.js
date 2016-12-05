(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaDialogController', TurmaDialogController);

    TurmaDialogController.$inject = ['$timeout', '$scope', '$stateParams', 'Turma', 'Gestor'];

    function TurmaDialogController ($timeout, $scope, $stateParams, Turma, Gestor) {
        var vm = this;

//        vm.turma = entity;
        vm.clear = clear;
        vm.save = save;
        vm.gestors = Gestor.query();
        vm.disciplinas = ['Artes', 'Biologia', 'Educação Física', 'Educação Religiosa', 'Espanhol', 'Filosofia', 'Física', 'Geografia', 'História', 'Inglês', 'Língua Portuguesa', 'Literatura', 'Matemática', 'Química', 'Sociologia'];

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
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
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
