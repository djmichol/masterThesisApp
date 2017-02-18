app.service('adminService', function($http, $rootScope) {
	
	this.saveModel = function(userId){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/convertData/saveModel",
			method: "POST",
			params: {userId: userId}
		});
	}
	
	this.getUserModel = function(userId){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/convertData/userModel",
			method: "GET",
			params: {userId: userId}
		});
	}
});