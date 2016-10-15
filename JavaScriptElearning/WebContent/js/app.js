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
	$routeProvider.when("/editor", {
        templateUrl: "view/editor.html",
		controller: "EditorController"
    });
	$routeProvider.when("/editor/:lessonId", {
        templateUrl: "view/editor.html",
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


app.factory('BearerAuthInterceptor', function ($window, $q,$rootScope) {
    return {
        request: function(config) {
            config.headers = config.headers || {};
            var token = $window.localStorage.getItem('token');
            if (token && !(token === undefined)) {
              config.headers.Authorization = 'Bearer ' + $window.localStorage.getItem('token');
            }
            return config || $q.when(config);
        },
        responseError: function(response) {
            if (response.status === 401) {
            	$rootScope.addAlert('danger','Dostęp dla zalogowanych użytkowników.');
            }
            if (response.status === 403) {
            	$rootScope.addAlert('danger','Brak dostępu.');
            }
            return $q.reject(response);
        }
    };
});

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('BearerAuthInterceptor');
});

app.run(function ($rootScope, $location, inputService,$uibModal) {

    var history = [];
    $rootScope.alerts = [];
    $rootScope.keystrokes = [];
    $rootScope.mauseMove = [];
    $rootScope.mauseClick = [];
    $rootScope.userForm = {};

    $rootScope.$on('$routeChangeSuccess', function() {
        history.push($location.$$path);
        $rootScope.alerts = [];
    });

    $rootScope.back = function () {
        var prevUrl = history.length > 1 ? history.splice(-2)[0] : "/";
        $location.path(prevUrl);
    };    

    $rootScope.addAlert = function(alertType,alertMsg) {
    	$rootScope.alerts.push({type: alertType, msg: alertMsg});
	};
	
	$rootScope.closeAlert = function(index) {
		$rootScope.alerts.splice(index, 1);
	};
	
	$rootScope.toggleUserFormModal = function(){
			var modalInstance = $uibModal.open({
				templateUrl: 'view/modalFormContent.html',
				backdrop: false,
				controller: 'ModalInstanceCtrl'
			})
		};
	
	$rootScope.saveUserInput = function(){
		var data = {
				keyStroke : $rootScope.keystrokes,
				mauseMove :  $rootScope.mauseMove,
				mauseClick : $rootScope.mauseClick
		}
		/*inputService.saveUserInput(data).success(function(dane) {
			//nic nie rob
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });	*/		
	}
	
	$rootScope.collectKeystrokes = function(){
		document.onkeydown = function (event) {
			event = event || window.event;
			$rootScope.keystrokes.push({'code':event.which, 'timeStamp':event.timeStamp,'type':event.type});
		}
		document.onkeyup = function (event) {
			event = event || window.event;
			$rootScope.keystrokes.push({'code':event.which, 'timeStamp':event.timeStamp,'type':event.type});
		}
		document.onclick = function (event) {
			event = event || window.event;
			$rootScope.mauseClick.push({'timeStamp':event.timeStamp,'type':event.type});
		}
		document.onmousemove = function(event) {
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
	        $rootScope.mauseMove.push({'timeStamp':event.timeStamp,'type':event.type,'posX':event.pageX,'posY':event.pageY});
	    }
	}
});