var app = angular.module("ElearningApp", ["ngRoute","ngStorage",'ui.bootstrap']).config(function ($routeProvider) {
	//routing do strony domowej
	$routeProvider.when("/home", {
        templateUrl: "view/home.html"
    });
	//routing do login
	$routeProvider.when("/login", {
        templateUrl: "view/login.html",
		controller: "UsersController"
    });
	//routing do rejestracji
	$routeProvider.when("/singUp", {
        templateUrl: "view/singUp.html",
		controller: "UsersController"
    });
	//routing do sciezek nauki
	$routeProvider.when("/paths", {
        templateUrl: "view/paths.html",
		controller: "CardController"
    });
	//routing do edytore 
	$routeProvider.when("/editor", {
        templateUrl: "view/editor.html",
		controller: "EditorController"
    });
	//gdy nie wybrano routingu przekieruj do domowej
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
            	$rootScope.addAlert('danger','401');
            }
            if (response.status === 403) {
                alert("403");
            }
            return response || $q.when(response);
        }
    };
});

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('BearerAuthInterceptor');
});

app.run(function ($rootScope, $location) {

    var history = [];
    $rootScope.alerts = [];

    $rootScope.$on('$routeChangeSuccess', function() {
        history.push($location.$$path);
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
});