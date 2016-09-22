app.controller("EditorController", function ($scope) {
	$scope.editor;
	$scope.isCollapsedHorizontal = true;
	
	$scope.lesson = { path_title: "first lesson", lesson_title: "for", instructions: ['aa','bb'], 
			tabs: [{title:'script.js',editor_content: "test", editor_content_edited: ""}],
			article: "You have some programming skills. Time to make something you can show people! We're going to show you how to program a 'code your own adventure' game. It'll have a basic story line, have the user make some choices, and have a happy ending. Then you can modify it as you wish and show off"
	};
	
	$scope.lessons = [{path_title: "first lesson", lesson_title: "for"},{path_title: "second lesson", lesson_title: "foreach"}] 
	
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
	
	$scope.saveEditor = function(val){
		var value = $scope.editor.getValue();
	}
	
});