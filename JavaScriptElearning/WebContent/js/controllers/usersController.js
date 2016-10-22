app.controller("UsersController", function ($scope,usersService,$window, $rootScope) {
	
	$scope.alerts = $rootScope.alerts;
	
	//login
	$scope.login = function() {	
		usersService.login($scope.name, $scope.password).success(function(dane) {
			if(dane.token){
				$rootScope.addAlert('success','Pomyślnie zalogowano');
				$window.localStorage.setItem("token",dane.token);
			}
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });
	};
	
	//singUp
	$scope.singUp = function() {	
		if($scope.name!=null && $scope.password!=null && $scope.mail!=null){
			usersService.singUp($scope.name, $scope.password, $scope.mail).success(function(dane) {
				if(dane.token){
					$rootScope.addAlert('success','Pomyślnie dodano użytkownika');
					$window.localStorage.setItem("token",dane.token);
				}
	        }).error(function(error) {
	        	$rootScope.addAlert('danger',error);
	        });	
		}
	};	
});