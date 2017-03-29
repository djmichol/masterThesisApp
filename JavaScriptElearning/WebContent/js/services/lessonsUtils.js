app.service('lessonUtilsService', function($http,$window, $rootScope,pageService,inputService) {
	
	this.lessonInBlock = [];
	this.currentLesson = {};
	this.nextLessonIndex = -1;
	this.currentPathId;
	
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
	
	this.checkIfLastEditorLesson = function(){
		var nextLesson = this.lessonInBlock[this.nextLessonIndex];
		if(nextLesson.order == 99){
			return true;
		}
		return false;
	}
	
	this.getNextLessonIndex = function (){
		return this.nextLessonIndex;
	}
	
	this.setCurrentPath = function (currentPathId){
		this.currentPathId = currentPathId;
		$window.localStorage.setItem("currentPathId",currentPathId);
	}
	
	this.getCurrentPath= function (){
		if (this.currentPathId && !(this.currentPathId === undefined)) {
			return this.currentPathId;
		} else {
			var currentPathId = $window.localStorage.getItem('currentPathId');
            if (currentPathId && !(currentPathId === undefined)) {
            	return currentPathId;
            }
		}
		return this.currentPathId;
	}
	
	this.redirectToNextLesson = function(){
		var nextLessonIndex = this.getNextLessonIndex();
		if(nextLessonIndex>=0 && nextLessonIndex<this.getLessonsInBlock().length){
			var nextLesson = this.getLessonsInBlock()[nextLessonIndex];
			pageService.redirectToLesson(nextLesson);
		} else if(nextLessonIndex>=0 && nextLessonIndex===this.getLessonsInBlock().length){
			var currentPath = this.getCurrentPath();
			 if (currentPath && !(currentPath === undefined)) {
				 pageService.redirectToLessonsBlocks(currentPath);
			 }else{
				 pageService.redirectToHome();
			 }
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
	
	this.saveLessonProgress = function(data){
		inputService.saveUserPassLesson(data).success(function(dane) {
			//DO NOTHING
        }).error(function(error) {
        	//DO NOTHING
        });
	}
	
	this.makePredictions = function(data){
		pageService.makePrediction(data).success(function(dane) {
			//alert(dane.predictions.boredom);
			$rootScope.prediction = dane;
        }).error(function(error) {
        	//DO NOTHING
        });
	}
});