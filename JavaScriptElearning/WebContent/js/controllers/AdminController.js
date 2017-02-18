app.controller("AdminController", function ($scope,adminService) {
	$scope.userId;
	
	$scope.prepareModel = function(){
		adminService.saveModel($scope.userId).success(function(dane,response) {
			alert('zapisono model do bazy');
	    }).error(function(error) {
	    	alert('blad zapisu model do bazy');
	    });
	}
	
	$scope.getUserModel = function(){
		adminService.getUserModel($scope.userId).success(function(dane,response) {
			alert('pobrano model');
	    }).error(function(error) {
	    	alert('blad pobrania modelu');
	    });
	}
});