app.controller("QuizController", function ($scope,$routeParams,pageService,lessonUtilsService,$rootScope,$uibModal) {
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	$scope.lessons = [];
	$scope.active = 1;
	$scope.result = 0;
	
	 $scope.checkboxModel = {
       a : false,
       b : false,
       c : false,
       d : false
     };
	
	$scope.initLesson = function(){
		var lessonId= $routeParams.lessonId;
		pageService.getQuizLessonById(lessonId).success(function(dane) {	
			if($rootScope.isKeyCollecting === false){
				$rootScope.collectKeystrokes();
			}
			$scope.lesson = dane.lesson;
			$scope.slides = dane.lesson.quiz;
			$scope.lessons = dane.lessons;
			lessonUtilsService.setLessonsInBlock(dane.lessons);
			lessonUtilsService.setCurrentLesson(dane.lesson);
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
	function checkAnswer(goodAnswer){
		var answer = getSelectedAnswer();
		if(answer.length==1){
			if(answer[0] == goodAnswer){
				return true;
			}
		}
		return false;
	}
	
	function getSelectedAnswer(){
		var selectedAnswer = [];
		for (var p in $scope.checkboxModel) {
		    if( $scope.checkboxModel.hasOwnProperty(p) ) {
		    	if($scope.checkboxModel[p]==true)
		    	selectedAnswer.push(p);
		    } 
		 } 
		return selectedAnswer;
	}
	
	function resetModel(){
		 $scope.checkboxModel = {
	       a : false,
	       b : false,
	       c : false,
	       d : false
	     }
	}
	
	function nextQuestion(){
		if($scope.active<$scope.slides.length){
			$scope.active += 1;
		} else if($scope.active==$scope.slides.length){
			var data = {
					lessonId : lessonUtilsService.getCurrentLesson().id,
					passed : true
			}			
			lessonUtilsService.saveLessonProgress(data);
			$scope.toggleNextLessonModal();
		}
	}
	
	$scope.goToNextQuestion = function(goodAnswer){
		if(checkAnswer(goodAnswer)){
			$scope.result +=1;
		}
		nextQuestion();
		resetModel();		
	}
	
	$scope.redirectToNextLesson = function() {
		lessonUtilsService.setNextLessonIndex();
		lessonUtilsService.redirectToNextLesson();
	}	
	
	$scope.toggleNextLessonModal = function(){
		var modalInstance = $uibModal.open({
			templateUrl: 'view/quizResultModal.html',
			backdrop: false,
			controller: 'QuizResultModalInstanceCtrl',
			resolve: {
		        result: function () {
		          return $scope.result;
		        },
		        questionsSize: function () {
			      return $scope.slides.length;
			    }
			}
		})
	};
});