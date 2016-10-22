app.service('lessonUtilsService', function($http,$window,pageService) {
	
	this.lessonInBlock = [];
	this.currentLesson = {};
	this.nextLessonIndex = -1;
	
	this.setLessonsInBlock = function (lessons){
		this.lessonInBlock = lessons;
		$window.localStorage.setItem("lessons",lessons);
	}
	
	this.getLessonsInBlock = function (){
		if(this.lessonInBlock.length>0){
			return this.lessonInBlock;
		} else {
			var lessons = $window.localStorage.getItem('lessons');
            if (lessons && !(lessons === undefined)) {
            	return lessons;
            }
		}
		return this.lessonInBlock;
	}
	
	this.setCurrentLesson = function (lesson){
		this.currentLesson = lesson;
		$window.localStorage.setItem("lesson",lesson);
	}
	
	this.getCurrentLesson = function (){
		if(!angular.equals(this.currentLesson, {})){
			return this.currentLesson;
		} else {
			var lesson = $window.localStorage.getItem('lesson');
            if (lesson && !(lesson === undefined)) {
            	return lesson;
            }
		}
		return this.currentLesson;
	}
	
	this.setNextLessonIndex = function(){
		var index = this.getIndexOf(this.getLessonsInBlock(),this.getCurrentLesson().id,'id');
		if(index>=0){
			this.nextLessonIndex = index+1;
			return;
		}
		this.nextLessonIndex = index;
	}
	
	this.getNextLessonIndex = function (){
		return this.nextLessonIndex;
	}
	
	this.redirectToNextLesson = function(){
		var nextLessonIndex = this.getNextLessonIndex();
		if(nextLessonIndex>=0 && nextLessonIndex<=this.getLessonsInBlock().length){
			var nextLesson = this.getLessonsInBlock()[nextLessonIndex];
			pageService.redirectToLesson(nextLesson);
		}
	}
	
	this.getIndexOf = function(arr, val, prop) {
      var l = arr.length,
      k = 0;
      for (k = 0; k < l; k = k + 1) {
        if (arr[k][prop] === val) {
          return k;
        }
      }
      return false;
    }
	
});