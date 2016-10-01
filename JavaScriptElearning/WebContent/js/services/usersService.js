app.service('usersService', function($http, Base64) {
	//login function
	this.login = function(name, password){
		var authToken = 'Basic ' + Base64.encode(name + ':' + password);
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/auth/login",
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
			url: "http://localhost:8080/JavaScriptElearning/elearningService/auth/logOn",
			method: "POST",
			data: newUser,
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}
});