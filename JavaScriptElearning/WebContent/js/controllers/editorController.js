app.controller("EditorController", function ($scope,$routeParams, pageService,$rootScope) {
	$scope.editor;
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	$scope.lessons = [];
	
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
	
	$scope.validateContent = function(tab) {
		var content = $scope.editor.getValue();
		var pass = testFunction(content,tab.contentSubmit);
		if(pass){
			$scope.saveEditor();
		}
	}
	
	function testFunction(content,test) {

		var functionResult;
		var oldLog = console.log;
		
		var errors = [];
		console.log=function(){
			var a="";
			for(i=0;i<arguments.length;i++)
				a+=arguments[i]+" ";
			document.getElementById("result").innerHTML = a;
		};
		window.onerror=function(a,b,c){errors.push(a);};
		
		var ee = eval(content+test);
		
		if(errors.length>0){
			var a = '';
			for(i=0;i<errors.length;i++)
				a+=errors[i]+" ";
			document.write(a)
		}
		
		console.log = oldLog;
		console.log(functionResult);
		
		return functionResult;
	}
	
	
	$scope.saveEditor = function(){
		var value = $scope.editor.getValue();
		$rootScope.toggleUserFormModal();
	}
	
	$scope.initLesson = function(){
		$rootScope.collectKeystrokes();
		var lessonId= $routeParams.lessonId;
		pageService.getEditorLessonById(lessonId).success(function(dane) {			
			$scope.lesson = dane.lesson;
			$scope.lessons = dane.lessons;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
});