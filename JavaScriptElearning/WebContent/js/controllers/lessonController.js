app.controller("LessonController", function ($scope,pageService,$rootScope,$routeParams,lessonUtilsService) {
	$scope.pathCards = []; 
	$scope.lessonsBlocks = [];
	$scope.path = {};
	$scope.lessonsForBlock = []; 
	$scope.lessonBlock = {};
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
		lessonUtilsService.setCurrentPath(pathId);
		pageService.getLessonBlockForPath(pathId).success(function(dane,response) {
			$scope.lessonsBlocks = dane.lessonsBlocks;
			$scope.path = dane.pathInfo;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });
	}
	$scope.getLessonsForBlock = function(){
		var blockId= $routeParams.blockId;
		pageService.getLessonsForLessonsBlock(blockId).success(function(dane) {			
			$scope.lessonsForBlock = dane.lessons;
			$scope.lessonBlock = dane.lessonBlock;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	};	
	$scope.redirectToLessonsBlocks = function(pathId){
		pageService.redirectToLessonsBlocks(pathId);
	}
	$scope.redirectToLessonsForBlock = function(blockId){
		pageService.redirectToLessonsForBlock(blockId);
	};
	$scope.redirectToLesson = function(lesson){
		pageService.redirectToLesson(lesson);
	};
});