//kontoler uzytkownika
app.controller("UsersController", function ($scope,usersService,$window) {
	
	//login
	$scope.login = function() {	
		usersService.login($scope.name, $scope.password).success(function(dane) {
			//zapisanie tokena do localStorage
			if(dane.token){
				$window.localStorage.setItem("token",dane.token);
			}
        }).error(function(error) {
            alert("LogIn error.");
        });
	};
	
	//singUp
	$scope.singUp = function() {	
		usersService.singUp($scope.name, $scope.password, $scope.mail).success(function(dane) {
			//TODO
        }).error(function(error) {
            alert("SingUp error.");
        });	
	};	
});