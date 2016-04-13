'use strict';

angular.module('belponto',  ['ngAnimate', 'ui.bootstrap', 'ui-router'])

//.run(function($rootScope, $window){
//	$rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
//		$rootScope.toState = toState;
//		$rootScope.toStateParams = toStateParams;
//		if(!$rootScope.toState.name == 'login'){
//			$rootScope.showIndex = true;
//		}
//	});
//	
//	$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
//		$rootScope.previousStateName = fromState.name;
//		$rootScope.previousStateParams = fromParams;
//
//		if (toState.name) {
//			$window.document.title = toState.name;
//		}
//		
//	});
//});
