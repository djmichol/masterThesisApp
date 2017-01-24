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
});