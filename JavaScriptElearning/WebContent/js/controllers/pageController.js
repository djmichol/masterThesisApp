//kontroler menu
app.controller("MenuController", function ($scope, $window) {
	$scope.menuElements = [{label:"Learn", value: "#/paths"}]; 
	
	//sprawdza czy user zalogowany
	$scope.isUserLoged = function(){
		 if ($window.localStorage.getItem('token') && (typeof $window.localStorage.getItem('token')!= 'undefined')) {
			 return true;
		 }
		 return false;
	};	
	//log out
	$scope.logout = function() {	
		//informacja o zalogowanym uzytkowniku w localSotrage jako zaszyfrowany token
		$window.localStorage.removeItem("token");
	};
});

//kontoler strony wyswietla aktualny czas w stopce
app.controller("FooterController", function ($scope, $interval) {
	$scope.theTime = new Date().toLocaleTimeString();
    $interval(function () {
        $scope.theTime = new Date().toLocaleTimeString();
    }, 1000);
});

//kontroler kart sciezek
app.controller("LessonController", function ($scope,pageService,$rootScope,$location,$routeParams) {
	$scope.pathCards = []; 
	$scope.lessons = []; 
	$scope.alerts = $rootScope.alerts;
	
	//pobierz sciezki rozwoju
	$scope.getAllPath = function() {
		if(typeof pageService.getPaths() == 'undefined' || pageService.getPaths().length == 0){
			pageService.getAllPath().success(function(dane,response) {
				$scope.pathCards = dane.paths;
				pageService.setPaths($scope.pathCards);
	        }).error(function(error) {
	        	$rootScope.addAlert('danger',error);
	        });
		}else{
			$scope.pathCards = pageService.getPaths();
		}
	};	
	//laduje lekcje dla sciezki
	$scope.loadLessons = function(){
		var pathId= $routeParams.pathId;
		pageService.getPathLessons(pathId).success(function(dane) {			
			$scope.lessons = dane.lessons;	
			pageService.setLessons(dane.lessons);
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	};	
	//przekierowanie do lekcji dla sciezki
	$scope.redirectToLessons = function(pathId){
		$location.path("/lessons/"+pathId);
	};
	//przekierowanie do edytora lekcji
	$scope.redirectToLesson = function(lesson){
		pageService.setSelectedLesson(lesson);
		$location.path("/editor/"+lesson.id);
	};
});

