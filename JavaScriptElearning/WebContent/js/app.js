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
	$routeProvider.when("/admin", {
        templateUrl: "view/admin.html",
		controller: "AdminController"
    });
    $routeProvider.otherwise({
        redirectTo: "/home"
    });
}); 

app.run(function ($rootScope, inputService,lessonUtilsService, adminService, $uibModal) {
	$rootScope.baseUrl = "http://localhost:8080/JavaScriptElearning";
	//$rootScope.baseUrl = "http://ec2-35-157-187-252.eu-central-1.compute.amazonaws.com:8080/JavaScriptElearning-0.0.2-SNAPSHOT";
    $rootScope.alerts = [];
    $rootScope.keystrokes = [];
    $rootScope.mauseMove = [];
    $rootScope.mauseClick = [];
    $rootScope.userForm = {};
    $rootScope.isKeyCollecting = false;
    $rootScope.isCollectMode = true;
    $rootScope.inProgress = true;
    
    $rootScope.prediction = {};
    
    $rootScope.loadModel =  function(){
		adminService.getMode().success(function(dane,response) {
			$rootScope.isCollectMode = dane;
			adminService.isCollectMode = $rootScope.isCollectMode;
	    }).error(function(error) {
	    	alert('blad pobrania trybu');
	    });
    }
    $rootScope.loadModel();
    
    $rootScope.$on('$routeChangeSuccess', function() {
        $rootScope.alerts = [];
    });
   
    $rootScope.addAlert = function(alertType,alertMsg) {
    	$rootScope.alerts.push({type: alertType, msg: alertMsg});
	};
	
	$rootScope.closeAlert = function(index) {
		$rootScope.alerts.splice(index, 1);
	};
	
	$rootScope.toggleUserFormModal = function(inProgress){
			$rootScope.inProgress = inProgress;
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
			if($rootScope.isCollectMode=="true"){
				if($rootScope.keystrokes.length>150){
					$rootScope.toggleUserFormModal(true);
				}
			}
		}
		document.onkeyup = function (event) {
			event = event || window.event;
			$rootScope.keystrokes.push({'code':event.which, 'time':event.timeStamp,'type':event.type});
			if($rootScope.isCollectMode=="true"){
				if($rootScope.keystrokes.length>150){
					$rootScope.toggleUserFormModal(true);
				}
			}
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
	        if($rootScope.isCollectMode=="true"){
	        	if($rootScope.roughSizeOfObject($rootScope.mauseMove)>65535){
	        		$rootScope.toggleUserFormModal(true);
	    		}
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
	
	$rootScope.roughSizeOfObject = function(object) {
	    var objectList = [];
	    var stack = [object];
	    var bytes = 0;

	    while ( stack.length ) {
	        var value = stack.pop();

	        if ( typeof value === 'boolean' ) {
	            bytes += 4;
	        }
	        else if ( typeof value === 'string' ) {
	            bytes += value.length * 2;
	        }
	        else if ( typeof value === 'number' ) {
	            bytes += 8;
	        }
	        else if(typeof value === 'object' && objectList.indexOf( value ) === -1){
	            objectList.push(value);
	            for(var i in value) {
	                stack.push(value[i]);
	            }
	        }
	    }
	    return bytes;
	}
});