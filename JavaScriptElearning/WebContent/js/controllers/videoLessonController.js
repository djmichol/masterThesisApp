app.controller("VideoLessonController", function ($scope,$routeParams,$sce, pageService,$rootScope) {
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	
	$scope.initLesson = function(){
		$rootScope.collectKeystrokes();
		var lessonId= $routeParams.lessonId;
		pageService.getVideoLessonById(lessonId).success(function(dane) {			
			$scope.lesson = dane;
			$scope.player = $sce.trustAsHtml(dane.video.source);
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
});