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
	
	$scope.init = function(){
		if($rootScope.prediction.frustration==='Yes'){
			$scope.message = 'frustration';
		}else if($rootScope.prediction.confusion==='Yes'){
			$scope.message = 'confusion';
		}else if($rootScope.prediction.boredom==='Yes'){
			$scope.message = 'boredom';
		}else if($rootScope.prediction.engaged==='Yes'){
			$scope.message = 'engaged';
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