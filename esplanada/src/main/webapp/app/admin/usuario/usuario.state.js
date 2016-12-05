(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('usuario', {
            parent: 'admin',
            url: '/usuario?page&sort',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'userManagement.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/usuario/usuario.html',
                    controller: 'UserManagementController',
                    controllerAs: 'vm'
                }
            },            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                }
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort)
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('usuario');
                    return $translate.refresh();
                }]

            }        })
        .state('usuario-detail', {
            parent: 'admin',
            url: '/usuario/:login',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'usuario.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/usuario/usuario-detail.html',
                    controller: 'UserManagementDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('usuario');
                    return $translate.refresh();
                }]
            }
        })
        .state('usuario.new', {
            parent: 'usuario',
            url: '/novo',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views : {
				'content@' : {
					templateUrl : 'app/admin/usuario/usuario-dialog.html',
					controller : 'UserManagementDialogController',
					controllerAs: 'vm'
				}
			},
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('usuario');
                    return $translate.refresh();
                }]
            }
        })
        .state('usuario.edit', {
            parent: 'usuario',
            url: '/{login}/editar',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views : {
				'content@' : {
					templateUrl : 'app/admin/usuario/usuario-dialog.html',
					controller : 'UserManagementDialogController',
					controllerAs: 'vm'
				}
			},
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('usuario');
                    return $translate.refresh();
                }]
            }
        })
        .state('usuario.delete', {
            parent: 'usuario',
            url: '/{login}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/admin/usuario/usuario-delete-dialog.html',
                    controller: 'UserManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['User', function(User) {
                            return User.get({login : $stateParams.login});
                        }]
                    }
                }).result.then(function() {
                    $state.go('usuario', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
