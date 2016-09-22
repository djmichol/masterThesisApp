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
	
	//login function
	this.test = function(){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/auth/test",
			method: "GET",
			headers: {
				'Content-Type': 'text/html'
			}
		});
	}
	
	//singUp function
	this.singUp = function(name, password, mail){
		var authToken = 'Basic ' + Base64.encode(name + ':' + password);
		var newUser = {
			"name" : name,
			"password" : password,
			"mail" : mail
		};		
		//TODO url
		/*return $http({
			url: "LoginPath",
			method: "POST",
			headers: {
				'Authorization': authToken,
				'Content-Type': 'text/html'
			}
		});*/
		alert(authToken);
	}
});