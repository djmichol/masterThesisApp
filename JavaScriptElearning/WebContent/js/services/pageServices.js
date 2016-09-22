//serwis
app.service('pageService', function($http) {

	//login function
	this.getAllPath = function(){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/path",
			method: "GET",
			headers: {
				'Content-Type': 'text/html'
			}
		});
	}
	
});