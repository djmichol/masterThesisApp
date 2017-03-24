app.controller("EditorController", function ($scope,$routeParams, pageService,$rootScope,$uibModal,$sce,lessonUtilsService) {
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
		$scope.editor.on("paste", function(e){
			e.preventDefault();
		});
		$scope.editor.setOption("showPrintMargin", false);
		$scope.editor.setValue(val);
	};	
	
	$scope.clearEditor = function(val){
		$scope.editor.setValue(val);
		document.getElementById("result").innerHTML = "";
	}
	
	$scope.validateContent = function(tab) {
		var content = $scope.editor.getValue();
		var pass = testFunction(content,tab.contentSubmit,tab.editorResult);		
		var data = {
				lessonId : lessonUtilsService.getCurrentLesson().id,
				passed : pass
		}	
		
		if(pass){
			$scope.saveEditor(data);
		}else{			
			lessonUtilsService.saveLessonProgress(data);
		}
	}
	
	function makePrediction(){
		var predictionData = {
				keyStroke : $rootScope.keystrokes,
				mauseMove :  $rootScope.mauseMove,
				mauseClick : $rootScope.mauseClick,
				lessonId : lessonUtilsService.getCurrentLesson().id
		}
		lessonUtilsService.makePrediction(predictionData);
	}
	
	function testFunction(content,test,errorInfo) {

		var functionResult;
		var oldLog = console.log;
		
		var errors = [];
		document.getElementById("result").innerHTML = "";
		console.log=function(){
			var a="";
			for(var ii=0;ii<arguments.length;ii++)
				a+=arguments[ii]+" ";
			document.getElementById("result").innerHTML += a;
		};
		window.onerror=function(a,b,c){errors.push(a);};
		
		try {
			eval(content+test); 
		} catch (e) {
			console.log(e.message);
		}
		
		if(errors.length>0){
			var a = '';
			for(var ii=0;ii<errors.length;ii++)
				a+=errors[ii]+" ";
			console.log(a);
		}
		if(functionResult==false){
			console.log(errorInfo);
		}		
		console.log = oldLog;
		console.log(functionResult);
		
		return functionResult;
	}
		
	$scope.saveEditor = function(data){				
		lessonUtilsService.saveLessonProgress(data);
		var value = $scope.editor.getValue();
		$scope.toggleNextLessonModal();		
	}
	
	$scope.initLesson = function(){		
		var lessonId= $routeParams.lessonId;
		pageService.getEditorLessonById(lessonId).success(function(dane) {	
			if($rootScope.isKeyCollecting === false){
				$rootScope.collectKeystrokes();
			}
			$scope.lesson = dane.lesson;
			$scope.lessons = dane.lessons;
			$scope.lesson.article =  $sce.trustAsHtml(dane.lesson.article);
			lessonUtilsService.setLessonsInBlock(dane.lessons);
			lessonUtilsService.setCurrentLesson(dane.lesson);
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
	$scope.toggleNextLessonModal = function(){
		var modalInstance = $uibModal.open({
			templateUrl: 'view/editorSuccessModal.html',
			backdrop: false,
			controller: 'NextLessonModalInstanceCtrl'
		})
	};
});