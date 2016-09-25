app.controller("EditorController", function ($scope,$routeParams, pageService,$rootScope) {
	$scope.editor;
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	$scope.lessons = []; 
	//inicjuje edytor
	$scope.initEditor = function(val){
		var useWebWorker = false;
		$scope.editor = ace.edit("editor");
		$scope.editor.setTheme("ace/theme/twilight");
		$scope.editor.session.setMode("ace/mode/javascript");
		$scope.editor.getSession().setUseWrapMode(true);
		$scope.editor.getSession().setWrapLimitRange(80, 80);
		$scope.editor.setOption("showPrintMargin", false);
		$scope.editor.setValue(val);
	};	
	//czysci edytor do wartosci domyslnej
	$scope.clearEditor = function(val){
		$scope.editor.setValue(val);
	}
	//submit
	$scope.saveEditor = function(){
		var value = $scope.editor.getValue();
	}
	//inicjuje lekcje
	$scope.initLesson = function(){
		var lessonId= $routeParams.lessonId;
		$scope.lessons= pageService.getLessons();
		$scope.lesson = pageService.getSelectedLesson();
		pageService.getLessonInfo(lessonId).success(function(dane) {			
			$scope.lesson.instructions = dane.lessonInstructions;
			$scope.lesson.tabs = dane.lessonTabs;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
});