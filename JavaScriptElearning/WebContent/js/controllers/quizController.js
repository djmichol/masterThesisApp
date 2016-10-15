app.controller("QuizController", function ($scope,$routeParams,pageService,$rootScope) {
	$scope.isCollapsedHorizontal = true;
	$scope.lesson = {};
	
	 $scope.checkboxModel = {
       a : false,
       b : false,
       c : false,
       d : false
     };
	
	$scope.initLesson = function(){
		$rootScope.collectKeystrokes();
		var lessonId= $routeParams.lessonId;
		pageService.getQuizLessonById(lessonId).success(function(dane) {			
			$scope.lesson = dane;
			$scope.slides = dane.quiz;
        }).error(function(error) {
        	$rootScope.addAlert('danger',error);
        });		
	}
	
	$scope.checkAnswer = function(goodAnswer){
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
	
});