'use strict';

angular.module('belponto')
	.controller('LoginController', function($scope, $http){
		
		$scope.logar = function(){
			$http({
				method: 'POST',
				url: 'app/autenticar/login',
				data: {
					usuario : $scope.usuario,
					senha : $scope.senha
                },
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(function sucessCallback(response){
				alert("logou com sucesso!")
			}, function errorCallback(response){
				alert("erro ao logar no sistema!")
			});
		};
		
	});
