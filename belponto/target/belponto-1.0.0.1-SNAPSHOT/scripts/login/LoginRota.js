'use strict';

angular.module('belponto')
	.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state('login', {
				url: '/login',
				views: {
		            'login@': {
		                templateUrl: 'scripts/login/login.html',
		                controller: 'LoginController'
		            }
			    }
			});
  
		//$urlRouterProvider.otherwise("/state1");
	});