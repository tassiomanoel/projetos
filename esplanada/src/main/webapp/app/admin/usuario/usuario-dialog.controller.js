(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('UserManagementDialogController',UserManagementDialogController);

    UserManagementDialogController.$inject = ['$stateParams', 'User', 'JhiLanguageService', '$state', '$scope'];

    function UserManagementDialogController ($stateParams, User, JhiLanguageService, $state, $scope) {
        var vm = this;

        vm.authorities = ['ALUNO','GESTOR', 'PROFESSOR'];
        vm.languages = null;
        vm.save = save;
        vm.disciplinas = ['Artes', 'Biologia', 'Educação Física', 'Educação Religiosa', 'Espanhol', 'Filosofia', 'Física', 'Geografia', 'História', 'Inglês', 'Língua Portuguesa', 'Literatura', 'Matemática', 'Química', 'Sociologia'];
        vm.perfil = null;
        
        if($state.current.name == 'usuario.edit'){
        	User.get({login: $stateParams.login}, function(result) {
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
        
        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });

//        function clear () {
//            $uibModalInstance.dismiss('cancel');
//        }
//
        function onSaveSuccess (result) {
            $state.go('usuario');
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function save () {
        	var role;
        	if(vm.user.authorities == 'GESTOR'){
        		role = 'ROLE_ADMIN'
        	} else if(vm.user.authorities == 'ALUNO'){
        		role = 'ROLE_USER'
        	} else {
        		role = 'ROLE_PROFESSOR'
        	}
        	vm.user.langKey = 'pt-br';
        	vm.user.authorities = [role]
            vm.isSaving = true;
            if (vm.user.id != undefined) {
                User.update(vm.user, onSaveSuccess, onSaveError);
            } else {
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }
    }
})();
