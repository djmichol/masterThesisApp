app.controller("MenuController", function ($scope, $window) {
	$scope.menuElements = [{label:"Ścieżki nauki", value: "#/paths"}]; 
	$scope.isUserLoged = function(){
		 if ($window.localStorage.getItem('token') && (typeof $window.localStorage.getItem('token')!= 'undefined')) {
			 return true;
		 }
		 return false;
	};	
	$scope.logout = function() {	
		$window.localStorage.removeItem("token");
	};
});

app.controller("FooterController", function ($scope, $interval, $rootScope) {
	$scope.theTime = new Date().toLocaleTimeString();
	
	$scope.prediction = $rootScope.prediction;
	
    $interval(function () {
        $scope.theTime = new Date().toLocaleTimeString();
    }, 1000);
});

