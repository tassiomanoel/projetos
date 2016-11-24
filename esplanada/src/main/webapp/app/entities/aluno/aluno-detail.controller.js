(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('AlunoDetailController', AlunoDetailController);

    AlunoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Aluno', 'Turma', 'Gestor', 'Professor'];

    function AlunoDetailController($scope, $rootScope, $stateParams, previousState, entity, Aluno, Turma, Gestor, Professor) {
        var vm = this;

        vm.aluno = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('colegioEsplanadaApp:alunoUpdate', function(event, result) {
            vm.aluno = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
