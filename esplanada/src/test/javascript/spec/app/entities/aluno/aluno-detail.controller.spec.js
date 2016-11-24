'use strict';

describe('Controller Tests', function() {

    describe('Aluno Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAluno, MockTurma, MockGestor, MockProfessor;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAluno = jasmine.createSpy('MockAluno');
            MockTurma = jasmine.createSpy('MockTurma');
            MockGestor = jasmine.createSpy('MockGestor');
            MockProfessor = jasmine.createSpy('MockProfessor');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Aluno': MockAluno,
                'Turma': MockTurma,
                'Gestor': MockGestor,
                'Professor': MockProfessor
            };
            createController = function() {
                $injector.get('$controller')("AlunoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'colegioEsplanadaApp:alunoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
