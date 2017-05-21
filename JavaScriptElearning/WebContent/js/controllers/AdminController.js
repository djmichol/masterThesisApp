app.controller("AdminController", function ($scope,$rootScope, adminService) {
	$scope.userId;
	$scope.isCollectMode = $rootScope.isCollectMode;
	
	$scope.init = function(){
		$scope.isCollectMode = $rootScope.isCollectMode;
	}
	
	$scope.prepareModel = function(){
		adminService.saveModel($scope.userId).success(function(dane,response) {
			alert('zapisono model do bazy');
	    }).error(function(error) {
	    	alert('blad zapisu model do bazy');
	    });
	}
	$scope.prepareQuizModel = function(){
		adminService.saveQuizModel($scope.userId).success(function(dane,response) {
			alert('stworzono model quiz');
	    }).error(function(error) {
	    	alert('blad');
	    });
	}
	
	$scope.getUserModel = function(){
		adminService.getUserModel($scope.userId, $scope.userData).success(function(dane,response) {
			$scope.isCollectMode = $rootScope.isCollectMode;
			alert('pobrano model');
	    }).error(function(error) {
	    	alert('blad pobrania modelu');
	    });
	}
	
	$scope.setMode = function(){
		adminService.setMode($scope.isCollectMode).success(function(dane,response) {
			alert('zmieniono tryb');
			 $rootScope.loadModel();
	    }).error(function(error) {
	    	alert('blad zmiany trybu');
	    });
	}
});