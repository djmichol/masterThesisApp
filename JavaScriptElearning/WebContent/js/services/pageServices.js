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
	
	this.getEditorLessonById = function(lessonId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadLessonEditorById",
			method: "GET",
			params: {lessonId: lessonId}
		});
	}
	
	this.getVideoLessonById = function(lessonId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadLessonVideoById",
			method: "GET",
			params: {lessonId: lessonId}
		});
	}
	
	this.getQuizLessonById = function(lessonId){
		return $http({
			url: "http://localhost:8080/JavaScriptElearning/elearningService/lessons/loadLessonQuizById",
			method: "GET",
			params: {lessonId: lessonId}
		});
	}
});