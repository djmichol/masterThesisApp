//kontroler menu
app.controller("MenuController", function ($scope, $window) {
	$scope.menuElements = [{label:"Learn", value: "#/paths"},{label:"Support", value:"#/support"},
	{label:"Editor", value:"#/editor"}]; 
	
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
app.controller("CardController", function ($scope,pageService) {
	$scope.pathCards = []; 
	
	//login
	$scope.getAllPath = function() {	
		pageService.getAllPath().success(function(dane,response) {
			$scope.pathCards = dane.paths;
        });
	};
});

