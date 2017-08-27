/**
 * All modals controllers
 */

app.controller('NextLessonModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService) {
	$scope.ok = function() {
		$uibModalInstance.close(this);			
		lessonUtilsService.setNextLessonIndex();
		if(lessonUtilsService.checkIfLastEditorLesson()){
			if($rootScope.isCollectMode=="true"){
				$rootScope.toggleUserFormModal(false);
    		}else{
    			$rootScope.makePrediction(false);
    		}			
		}else{
			lessonUtilsService.redirectToNextLesson();
		}
	};
});

app.controller('QuizResultModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService,result,questionsSize) {
	$scope.result = result;
	$scope.questionsSize = questionsSize;
	
	$scope.ok = function() {
		$uibModalInstance.close(this);
		if($rootScope.isCollectMode=="true"){
			$rootScope.toggleUserFormModal(false);
		}else{
			$rootScope.makePrediction(false);
		}	
		lessonUtilsService.setNextLessonIndex();
	};
});

app.controller('PredictionModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService) {
	$scope.message = '';
	
	var engagedMessage = ['Tak trzymaj!!', 'Super!!'];
	
	$scope.init = function(){
		var currentLesson = lessonUtilsService.getCurrentLesson();
		//$scope.message = "System wykryl zaangazowanie! \r\n" +  engagedMessage[Math.floor(Math.random() * engagedMessage.length)];
		if($rootScope.prediction.frustration==='Yes'){
			$scope.message = "System wykryl frustracje! \r\n" + currentLesson.helpMessage;
		}else if($rootScope.prediction.confusion==='Yes'){
			$scope.message = "System wykryl zmieszanie! \r\n" + currentLesson.helpMessage;
		}else if($rootScope.prediction.boredom==='Yes'){
			$scope.message = "System wykryl znudzenie! \r\n" + currentLesson.boredMessage;
		}else if($rootScope.prediction.engaged==='Yes'){
			$scope.message = "System wykryl zaangazowanie! \r\n" +  engagedMessage[Math.floor(Math.random() * arr.length)];
		}else{
			$scope.ok();
		}
	}
	
	$scope.ok = function() {
		$rootScope.collectKeystrokes();
		$uibModalInstance.close(this);
		
		if(!$rootScope.inProgress){
			lessonUtilsService.redirectToNextLesson();
		}
		
	};
});

app.controller('UserFormModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService) {

	$scope.ok = function() {
		if($scope.boredom==='YesRather'){
			$scope.boredom="Yes";
		}else if($scope.boredom==='NoRather'){
			$scope.boredom="No";
		}
		
		if($scope.confusion==='YesRather'){
			$scope.confusion="Yes";
		}else if($scope.confusion==='NoRather'){
			$scope.confusion="No";
		}
		
		if($scope.engaged==='YesRather'){
			$scope.engaged="Yes";
		}else if($scope.engaged==='NoRather'){
			$scope.engaged="No";
		}
		
		if($scope.frustration==='YesRather'){
			$scope.frustration="Yes";
		}else if($scope.frustration==='NoRather'){
			$scope.frustration="No";
		}
		
		var ankieta = {
			boredom : $scope.boredom,
			confusion : $scope.confusion,
			engaged : $scope.engaged, 
			frustration :$scope.frustration
		}
		$rootScope.userForm = ankieta;
		$rootScope.saveUserInput();
		$rootScope.collectKeystrokes();
		$uibModalInstance.close(this);
		
		if(!$rootScope.inProgress){
			lessonUtilsService.redirectToNextLesson();
		}
	};

	//default settings
	$scope.boredom = 'YesRather';
	$scope.confusion = 'YesRather';
	$scope.engaged = 'YesRather';
	$scope.frustration = 'YesRather';

});