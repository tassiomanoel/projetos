(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaDetailController', TurmaDetailController);

    TurmaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Turma', 'Gestor', 'User'];

    function TurmaDetailController($scope, $rootScope, $stateParams, previousState, entity, Turma, Gestor, User) {
        var vm = this;

        vm.turma = entity;
        vm.previousState = previousState.name;
        
        $scope.gridUsuarios = {};
		
		$scope.gridUsuarios.columnDefs = [
										 {name:'Nome', field : 'firstName'}
										];

        var unsubscribe = $rootScope.$on('colegioEsplanadaApp:turmaUpdate', function(event, result) {
            vm.turma = result;
        });
        var usuarios = [];
        User.query(function(usuario){
        	angular.forEach(usuario, function(user){
        		if(user.turma != undefined && $stateParams.id == user.turma.id){
        			usuarios.push(user);
           	 	}
        	});
        });
        $scope.gridUsuarios.data = usuarios;
        $scope.$on('$destroy', unsubscribe);
    }
})();
