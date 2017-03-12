app.service('adminService', function($http, $rootScope) {
	
	this.saveModel = function(userId){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/convertData/saveModel",
			method: "POST",
			params: {userId: userId}
		});
	}
	
	this.getUserModel = function(userId, data){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/convertData/userModel",
			method: "GET",
			data: data,
			params: {userId: userId}
		});
	}
	
	this.getMode = function(){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/mode",
			method: "GET"
		});
	}
	
	this.setMode = function(data){
		return $http({
			url: $rootScope.baseUrl+"/elearningService/mode",
			method: "POST",
			data: data
		});
	}
});