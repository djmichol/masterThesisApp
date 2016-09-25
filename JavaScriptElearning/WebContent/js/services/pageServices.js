//serwis
app.service('pageService', function($http,$window) {
	
	var paths = [];
	
	this.setSelectedLesson = function(object){
		$window.localStorage.setItem("selectedLesson",JSON.stringify(object));
	}
	this.getSelectedLesson = function(){
		return JSON.parse($window.localStorage.getItem('selectedLesson'));
	}
	this.setPaths = function(object){
		this.paths = object;
	}
	this.getPaths = function(){
		return this.paths;
	}
	this.setLessons = function(object){
		$window.localStorage.setItem("lessons",JSON.stringify(object));
	}
	this.getLessons = function(){
		return JSON.parse($window.localStorage.getItem('lessons'));
	}

	//pobiera sciezki rozwoju
	this.getAllPath = function(){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/path",
			method: "GET",
			headers: {
				'Content-Type': 'text/html'
			}
		});
	}
	
	//pobiera lekcje dla sciezki rozwoju
	this.getPathLessons = function(pathIdLocal){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadPathLessons",
			method: "GET",
			params: {pathId: pathIdLocal}
		});
	}
	
	//pobiera informacje o lekcji
	this.getLessonInfo = function(lessonId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadLessonInfo",
			method: "GET",
			params: {lessonId: lessonId}
		});
	}
	
});