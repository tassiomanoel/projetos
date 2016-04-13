'use strict';

angular.module('belponto')
	.factory('LoginController', function($scope, $http){
		
		$scope.logar = function(){
			$http({
				method: 'POST',
				url: '/aplicacao/seg/login/logar',
				data: {
					username : $scope.username,
					password : $scope.senha
                },
				headers: {
					'Content-Type': 'application/json',
					"service_key": "3b91cab8-926f-49b6-ba00-920bcf934c2a"
				}
			}).then(function sucessCallback(response){
				alert("logou com sucesso!")
			}, function errorCallback(response){
				alert("erro ao logar no sistema!")
			});
		};
		
	});
