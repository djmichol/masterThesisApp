app.controller("EditorController", function ($scope,$routeParams, pageService,$rootScope) {
	$scope.editor;
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	
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
	
	$scope.clearEditor = function(val){
		$scope.editor.setValue(val);
	}
	
	$scope.saveEditor = function(){
		var value = $scope.editor.getValue();
		var keyTab = $rootScope.keystrokes;
		var mauseMove = $rootScope.mauseMove;
		var mauseClick = $rootScope.mauseClick;
	}
	
	$scope.initLesson = function(){
		$rootScope.collectKeystrokes();
		var lessonId= $routeParams.lessonId;
		pageService.getEditorLessonById(lessonId).success(function(dane) {			
			$scope.lesson = dane;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
});