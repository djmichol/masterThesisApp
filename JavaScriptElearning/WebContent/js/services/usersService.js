app.service('usersService', function($http, $rootScope, Base64) {
	//login function
	this.login = function(name, password){
		var authToken = 'Basic ' + Base64.encode(name + ':' + password);
		return $http({
			url: $rootScope.baseUrl+"/elearningService/auth/login",
			method: "GET",
			headers: {
				'Log-On-User': authToken,
				'Content-Type': 'text/html'
			}
		});
	}
	
	//singUp function
	this.singUp = function(name, password, mail){
		var newUser = {
			"name" : name,
			"password" : password,
			"mail" : mail
		};		
		return $http({
			url: $rootScope.baseUrl+"/elearningService/auth/logOn",
			method: "POST",
			data: newUser,
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}
});