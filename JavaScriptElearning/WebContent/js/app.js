var app = angular.module("ElearningApp", ["ngRoute",'ui.bootstrap']).config(function ($routeProvider) {
	$routeProvider.when("/home", {
        templateUrl: "view/home.html"
    });
	$routeProvider.when("/login", {
        templateUrl: "view/login.html",
		controller: "UsersController"
    });
	$routeProvider.when("/singUp", {
        templateUrl: "view/singUp.html",
		controller: "UsersController"
    });
	$routeProvider.when("/paths", {
        templateUrl: "view/paths.html",
		controller: "LessonController"
    });
	$routeProvider.when("/lessonsBlocks/:pathId", {
        templateUrl: "view/lessonsBlockList.html",
		controller: "LessonController"
    });	
	$routeProvider.when("/lessonsForBlock/:blockId", {
        templateUrl: "view/lessonsList.html",
		controller: "LessonController"
    });	 
	$routeProvider.when("/editor/:lessonId", {
        templateUrl: "view/editorTemplate.html",
		controller: "EditorController"
    });
	$routeProvider.when("/videoLesson/:lessonId", {
        templateUrl: "view/videoTemplate.html",
		controller: "VideoLessonController"
    });
	$routeProvider.when("/quizLesson/:lessonId", {
        templateUrl: "view/quizTemplate.html",
		controller: "QuizController"
    });
    $routeProvider.otherwise({
        redirectTo: "/home"
    });
}); 

app.run(function ($rootScope, inputService,lessonUtilsService,$uibModal) {
	//$rootScope.baseUrl = "http://localhost:8080/JavaScriptElearning";
	$rootScope.baseUrl = "https://java-script-elearning.herokuapp.com";
    $rootScope.alerts = [];
    $rootScope.keystrokes = [];
    $rootScope.mauseMove = [];
    $rootScope.mauseClick = [];
    $rootScope.userForm = {};
    $rootScope.isKeyCollecting = false;
    
    $rootScope.$on('$routeChangeSuccess', function() {
        $rootScope.alerts = [];
    });
   
    $rootScope.addAlert = function(alertType,alertMsg) {
    	$rootScope.alerts.push({type: alertType, msg: alertMsg});
	};
	
	$rootScope.closeAlert = function(index) {
		$rootScope.alerts.splice(index, 1);
	};
	
	$rootScope.toggleUserFormModal = function(){
			$rootScope.stopCollecting();
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modalFormContent.html',
				backdrop: false,
				controller: 'UserFormModalInstanceCtrl'
			})
		};
	
	$rootScope.saveUserInput = function(){
		var lessonId = lessonUtilsService.getCurrentLesson().id;
		var data = {
				keyStroke : $rootScope.keystrokes,
				mauseMove :  $rootScope.mauseMove,
				mauseClick : $rootScope.mauseClick,
				form : $rootScope.userForm,
				lessonId : lessonId
		}
		inputService.saveUserInput(data).success(function(dane) {
			$rootScope.keystrokes = [];
		    $rootScope.mauseMove = [];
		    $rootScope.mauseClick = [];
		    $rootScope.userForm = {};
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });			
	}
	var counter = 0;
	$rootScope.collectKeystrokes = function(){
		$rootScope.isKeyCollecting = true;
		document.onkeydown = function (event) {
			event = event || window.event;
			$rootScope.keystrokes.push({'code':event.which, 'time':event.timeStamp,'type':event.type});
		}
		document.onkeyup = function (event) {
			event = event || window.event;
			$rootScope.keystrokes.push({'code':event.which, 'time':event.timeStamp,'type':event.type});
		}
		document.onclick = function (event) {
			event = event || window.event;
			$rootScope.mauseClick.push({'time':event.timeStamp,'type':event.type});
		}		
		document.onmousemove = function(event) {
			counter = counter + 1;
	        var dot, eventDoc, doc, body, pageX, pageY;
	        event = event || window.event;
	        if (event.pageX == null && event.clientX != null) {	        	
	            eventDoc = (event.target && event.target.ownerDocument) || document;
	            doc = eventDoc.documentElement;
	            body = eventDoc.body;
	            event.pageX = event.clientX +
	              (doc && doc.scrollLeft || body && body.scrollLeft || 0) -
	              (doc && doc.clientLeft || body && body.clientLeft || 0);
	            event.pageY = event.clientY +
	              (doc && doc.scrollTop  || body && body.scrollTop  || 0) -
	              (doc && doc.clientTop  || body && body.clientTop  || 0 );
	        }
	        if(counter === 2){
	        	counter=0;
	        	$rootScope.mauseMove.push({'time':event.timeStamp,'X':event.pageX,'Y':event.pageY});
	        }	        
	    }
	}
	
	$rootScope.stopCollecting = function(){
		$rootScope.isKeyCollecting = false;
		document.onkeydown = function (event) {}
		document.onkeyup = function (event) {}
		document.onclick = function (event) {}
		document.onmousemove = function(event) {}
	}
});