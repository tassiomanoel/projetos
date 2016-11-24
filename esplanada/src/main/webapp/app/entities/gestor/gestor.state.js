(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('gestor', {
            parent: 'entity',
            url: '/gestor',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'colegioEsplanadaApp.gestor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/gestor/gestors.html',
                    controller: 'GestorController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('gestor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('gestor-detail', {
            parent: 'entity',
            url: '/gestor/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'colegioEsplanadaApp.gestor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/gestor/gestor-detail.html',
                    controller: 'GestorDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('gestor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Gestor', function($stateParams, Gestor) {
                    return Gestor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'gestor',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('gestor-detail.edit', {
            parent: 'gestor-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gestor/gestor-dialog.html',
                    controller: 'GestorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Gestor', function(Gestor) {
                            return Gestor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gestor.new', {
            parent: 'gestor',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gestor/gestor-dialog.html',
                    controller: 'GestorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nome: null,
                                email: null,
                                idade: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('gestor', null, { reload: 'gestor' });
                }, function() {
                    $state.go('gestor');
                });
            }]
        })
        .state('gestor.edit', {
            parent: 'gestor',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gestor/gestor-dialog.html',
                    controller: 'GestorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Gestor', function(Gestor) {
                            return Gestor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gestor', null, { reload: 'gestor' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gestor.delete', {
            parent: 'gestor',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gestor/gestor-delete-dialog.html',
                    controller: 'GestorDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Gestor', function(Gestor) {
                            return Gestor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gestor', null, { reload: 'gestor' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
