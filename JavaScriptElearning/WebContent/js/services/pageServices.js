//serwis
app.service('pageService', function($http,$window) {
	
	this.getAllLearningPaths = function(){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/learningPath/allPaths",
			method: "GET",
			headers: {
				'Content-Type': 'text/html'
			}
		});
	}
	
	this.getLessonBlockForPath = function(pathId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/learningLessonBlock/lessonBlock",
			method: "GET",
			params: {pathId: pathId}
		});
	}
	
	this.getLessonsForLessonsBlock = function(blockId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadBlockLessons",
			method: "GET",
			params: {blockId: blockId}
		});
	}
	
	this.getLessonById = function(lessonId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadLessonById",
			method: "GET",
			params: {lessonId: lessonId}
		});
	}
});