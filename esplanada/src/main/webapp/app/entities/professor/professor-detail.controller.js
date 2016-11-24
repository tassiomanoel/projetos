(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('ProfessorDetailController', ProfessorDetailController);

    ProfessorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Professor', 'Gestor', 'Turma', 'Aluno'];

    function ProfessorDetailController($scope, $rootScope, $stateParams, previousState, entity, Professor, Gestor, Turma, Aluno) {
        var vm = this;

        vm.professor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('colegioEsplanadaApp:professorUpdate', function(event, result) {
            vm.professor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
