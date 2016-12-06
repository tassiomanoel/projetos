(function() {
    'use strict';

    angular
        .module('colegioEsplanadaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('login', {
            parent: 'account',
            url: '/login',
            data: {
                authorities: []
            },
            views: {
              'login@': {
                  templateUrl: 'app/components/login/login.html',
                  controller: 'LoginController'
              }
            },
            resolve: {
            	translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
            		$translatePartialLoader.addPart('login');
            		return $translate.refresh();
            	}]
            }
        });
    }
})();