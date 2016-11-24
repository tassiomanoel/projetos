'use strict';

describe('Controller Tests', function() {

    describe('Gestor Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockGestor, MockTurma, MockProfessor, MockAluno;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockGestor = jasmine.createSpy('MockGestor');
            MockTurma = jasmine.createSpy('MockTurma');
            MockProfessor = jasmine.createSpy('MockProfessor');
            MockAluno = jasmine.createSpy('MockAluno');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Gestor': MockGestor,
                'Turma': MockTurma,
                'Professor': MockProfessor,
                'Aluno': MockAluno
            };
            createController = function() {
                $injector.get('$controller')("GestorDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'colegioEsplanadaApp:gestorUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
