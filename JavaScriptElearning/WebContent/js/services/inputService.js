app.service('inputService', function($http, Base64,$rootScope) {
	
	this.saveUserInput = function(data){
	
		return $http({
			url: $rootScope.baseUrl+"/elearningService/userInputData/insertData",
			method: "POST",
			data: data,
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}
	
	this.saveUserPassLesson = function(data){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/userInputData/insertPassedLesson",
			method: "POST",
			data: data,
			headers: {
				'Content-Type': 'application/json'
			}
		});
	}

});