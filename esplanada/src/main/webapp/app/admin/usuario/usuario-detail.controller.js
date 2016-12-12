(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('UserManagementDetailController', UserManagementDetailController);

    UserManagementDetailController.$inject = ['$stateParams', 'User'];

    function UserManagementDetailController ($stateParams, User) {
        var vm = this;

        vm.load = load;
        vm.user = {};

        vm.load($stateParams.login);

        function load (login) {
            User.get({login: login}, function(result) {
                angular.forEach(result.authorities, function(role){
                	if(role == "ROLE_ADMIN"){
                		result.authorities[0] = "GESTOR"
                	} else if(role == "ROLE_USER"){
                		result.authorities[0] = "ALUNO"
                	} else {
                		result.authorities[0] = "PROFESSOR"
                	}
                })
            	vm.user = result;
            });
        }
    }
})();
