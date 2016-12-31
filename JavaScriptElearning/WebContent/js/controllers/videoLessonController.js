app.controller("VideoLessonController", function ($scope,$routeParams,$sce, pageService,lessonUtilsService,$rootScope) {
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	$scope.lessons = [];
	
	$scope.initLesson = function(){
		var lessonId= $routeParams.lessonId;
		pageService.getVideoLessonById(lessonId).success(function(dane) {	
			if($rootScope.isKeyCollecting === false){
				$rootScope.collectKeystrokes();
			}
			$scope.lesson = dane.lesson;
			$scope.lessons = dane.lessons;
			$scope.player = $sce.trustAsHtml(dane.lesson.video.source);
			lessonUtilsService.setLessonsInBlock(dane.lessons);
			lessonUtilsService.setCurrentLesson(dane.lesson);
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
	$scope.redirectToNextLesson = function() {
		lessonUtilsService.setNextLessonIndex();
		lessonUtilsService.redirectToNextLesson();
	}	
});