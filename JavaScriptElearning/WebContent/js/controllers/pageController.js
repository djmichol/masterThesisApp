app.controller("MenuController", function ($scope, $window) {
	$scope.menuElements = [{label:"Learn", value: "#/paths"}]; 
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

app.controller("FooterController", function ($scope, $interval) {
	$scope.theTime = new Date().toLocaleTimeString();
    $interval(function () {
        $scope.theTime = new Date().toLocaleTimeString();
    }, 1000);
});

app.controller("LessonController", function ($scope,pageService,$rootScope,$location,$routeParams) {
	$scope.pathCards = []; 
	$scope.lessonsBlocks = [];
	$scope.lessonsForBlock = []; 
	$scope.alerts = $rootScope.alerts;
	
	$scope.getAllPaths = function() {
		pageService.getAllLearningPaths().success(function(dane,response) {
			$scope.pathCards = dane.paths;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });
	};	
	$scope.getLessonsBlockForPath = function(){
		var pathId= $routeParams.pathId;
		pageService.getLessonBlockForPath(pathId).success(function(dane,response) {
			$scope.lessonsBlocks = dane.lessonsBlocks;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });
	}
	$scope.getLessonsForBlock = function(){
		var blockId= $routeParams.blockId;
		pageService.getLessonsForLessonsBlock(blockId).success(function(dane) {			
			$scope.lessonsForBlock = dane.lessons;	
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	};	
	$scope.redirectToLessonsBlocks = function(pathId){
		$location.path("/lessonsBlocks/"+pathId);
	}
	$scope.redirectToLessonsForBlock = function(blockId){
		$location.path("/lessonsForBlock/"+blockId);
	};
	$scope.redirectToLesson = function(lesson){
		$location.path("/editor/"+lesson.id);
	};
});

