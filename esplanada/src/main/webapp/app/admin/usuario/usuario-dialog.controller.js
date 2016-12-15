(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('UserManagementDialogController',UserManagementDialogController);

    UserManagementDialogController.$inject = ['$stateParams', 'User', 'JhiLanguageService', '$state', '$scope', 'Principal'];

    function UserManagementDialogController ($stateParams, User, JhiLanguageService, $state, $scope, Principal) {
        var vm = this;
        var usuarioLoagado = Principal.usuarioLogado();
        vm.authorities = ['ALUNO','GESTOR', 'PROFESSOR'];
        vm.languages = null;
        vm.save = save;
        vm.disciplinas = ['Artes', 'Biologia', 'Educação Física', 'Educação Religiosa', 'Espanhol', 'Filosofia', 'Física', 'Geografia', 'História', 'Inglês', 'Língua Portuguesa', 'Literatura', 'Matemática', 'Química', 'Sociologia'];
        vm.perfil = null;
        
        if($state.current.name == 'usuario.edit'){
        	User.get({login: $stateParams.login}, function(result) {
        		angular.forEach(result.authorities, function(role){
        			if(result.authorities.length == 1){
        				if(role == "ROLE_ADMIN"){
                    		result.authorities[0] = "GESTOR"
                    	} else if(role == "ROLE_USER"){
                    		result.authorities[0] = "ALUNO"
                    	}
        			} else {
        				result.authorities[0] = "PROFESSOR"
        				result.authorities.splice(1,1);
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
        	if(role == 'ROLE_PROFESSOR'){
        		vm.user.authorities.push('ROLE_ADMIN');
        	}
            vm.isSaving = true;
            if (vm.user.id != undefined) {
                User.update(vm.user, onSaveSuccess, onSaveError);
            } else {
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }
        
        if($state.current.name == 'usuario.nota' || $state.current.name == 'usuario.anotacao'){
        	User.getAlunosPorTurma({login:usuarioLoagado.login},function(result){
        		$scope.alunosPorTurma = result;
        	});
        }
        
        $scope.calcularMedia = function(alunoSelecionado){
        	angular.forEach(alunoSelecionado, function(aluno){
        		var media = (aluno.nota1 + aluno.nota2 + aluno.nota3 + aluno.nota4) / 4;
        		if(media >= 6 && aluno.faltas <= 50){
        			aluno.situacao = "Aprovado";
        		} else {
        			aluno.situacao = "Reprovado";
        		}
        	});
        }
        
        $scope.salvarMedia = function(){
        	User.salvarMediaFinalAluno($scope.alunosPorTurma, function(result){
        		result;
        	});
        }
        
        $scope.salvarAnotacao = function(){
        	User.salvarAnotacaoAluno($scope.alunosPorTurma, function(result){
        		result;
        	});
        }
        
        if($state.current.name == 'usuario.aluno'){
        	User.get({login:usuarioLoagado.login}, function(result){
        		$scope.areaAluno = result;
        	})
        }
    }
})();
