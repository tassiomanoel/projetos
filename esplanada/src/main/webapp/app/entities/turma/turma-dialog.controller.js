(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .controller('TurmaDialogController', TurmaDialogController);

    TurmaDialogController.$inject = ['$timeout', '$scope', '$stateParams', 'User', 'Turma', '$state', 'TurmaAluno'];

    function TurmaDialogController ($timeout, $scope, $stateParams, User, Turma, $state, TurmaAluno) {
        var vm = this;

        vm.save = save;
        vm.disciplinas = ['Artes', 'Biologia', 'Educação Física', 'Educação Religiosa', 'Espanhol', 'Filosofia', 'Física', 'Geografia', 'História', 'Inglês', 'Língua Portuguesa', 'Literatura', 'Matemática', 'Química', 'Sociologia'];
        vm.usuarios = [];
        User.query(function(usuarios){
        	angular.forEach(usuarios, function(usuario){
        		if(usuario.authorities[0] == 'ROLE_USER'){
        			vm.usuarios.push(usuario);
        		}
        	});
        })
        
        if($state.current.name == 'turma.edit'){
        	Turma.get({id: $stateParams.id}, function(result) {
                vm.turma = result;
            });
        }
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function save () {
            vm.isSaving = true;
            vm.turma.turmaAlunos = {};
            vm.turma.turmaAlunos.usuario = {};
            vm.turma.turmaAlunos.turma = {};
            angular.forEach(vm.turma.alunos, function(user){
            	//vm.turma.turmaAlunos.usuario = user;
            });
            
            if (vm.turma.id !== null) {
                Turma.update(vm.turma, onSaveSuccess, onSaveError);
            } else {
                Turma.save(vm.turma, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('colegioEsplanadaApp:turmaUpdate', result);
            vm.isSaving = false;
            $state.go('turma');
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        $scope.associarAlunos = function(){
        	vm.turma.alunos
        	$stateParams.id
        	vm.turmaAluno = {};
        	vm.turmaAluno.usuario = {};
    		vm.turmaAluno.turma = {};
        	
        	angular.forEach(vm.turma.alunos, function(aluno){
        		vm.turmaAluno.usuario.id = aluno;
        		vm.turmaAluno.turma.id = $stateParams.id
        	});
        	
        	TurmaAluno.save(vm.turmaAluno, onSaveSuccess);
        }
    }
})();
