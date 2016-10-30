app.service('inputService', function($http, Base64) {
	
	this.saveUserInput = function(data){
	
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/userInputData/insertData",
			method: "POST",
			data: data,
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}
});