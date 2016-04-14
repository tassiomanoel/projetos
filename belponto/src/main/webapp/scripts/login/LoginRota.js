'use strict';

angular.module('belponto')
	.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state('login', {
				url: '/login',
				data: {
                    //roles: ['ROLE_ADMIN','ROLE_PERITO'],
                    pageTitle: 'Login'
                },
				views: {
		            'login@': {
		                templateUrl: 'scripts/login/login.html',
		                controller: 'LoginController'
		            }
			    }
			});
  
		//$urlRouterProvider.otherwise("/login");
	});