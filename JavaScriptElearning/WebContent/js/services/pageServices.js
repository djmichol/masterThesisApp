app.service('pageService', function($http,$window,$location,$rootScope) {
	
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
	
	this.redirectToEditorLesson = function(lesson){
		$location.path("/editor/"+lesson.id);
	};
	this.redirectToVideoLesson = function(lesson){
		$location.path("/videoLesson/"+lesson.id);
	};
	this.redirectToQuizLesson = function(lesson){
		$location.path("/quizLesson/"+lesson.id);
	};
	this.redirectToLesson = function(lesson){
		if(lesson.type=='editor'){
			this.redirectToEditorLesson(lesson);
		}else if(lesson.type=='video'){
			this.redirectToVideoLesson(lesson);
		}else if(lesson.type=='quiz'){
			this.redirectToQuizLesson(lesson);
		}
	};	
	this.redirectToLessonsBlocks = function(pathId){
		$location.path("/lessonsBlocks/"+pathId);
	}
	this.redirectToLessonsForBlock = function(blockId){
		$location.path("/lessonsForBlock/"+blockId);
	};
	this.redirectToHome = function(){
		$location.path("/paths");
	};
});