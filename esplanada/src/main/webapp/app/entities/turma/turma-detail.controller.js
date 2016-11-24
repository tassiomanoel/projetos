(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaDetailController', TurmaDetailController);

    TurmaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Turma', 'Gestor'];

    function TurmaDetailController($scope, $rootScope, $stateParams, previousState, entity, Turma, Gestor) {
        var vm = this;

        vm.turma = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('colegioEsplanadaApp:turmaUpdate', function(event, result) {
            vm.turma = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
