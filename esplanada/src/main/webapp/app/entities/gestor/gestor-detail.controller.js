(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('GestorDetailController', GestorDetailController);

    GestorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Gestor', 'Turma', 'Professor', 'Aluno'];

    function GestorDetailController($scope, $rootScope, $stateParams, previousState, entity, Gestor, Turma, Professor, Aluno) {
        var vm = this;

        vm.gestor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('colegioEsplanadaApp:gestorUpdate', function(event, result) {
            vm.gestor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
