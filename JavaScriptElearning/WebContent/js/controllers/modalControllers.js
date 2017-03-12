/**
 * All modals controllers
 */

app.controller('NextLessonModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService) {
	$scope.ok = function() {
		$uibModalInstance.close(this);			
		lessonUtilsService.setNextLessonIndex();
		if(lessonUtilsService.checkIfLastEditorLesson()){
			$rootScope.toggleUserFormModal();
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
		$rootScope.toggleUserFormModal();
		lessonUtilsService.setNextLessonIndex();
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
		$uibModalInstance.close(this);
		lessonUtilsService.redirectToNextLesson();
	};

	$scope.boredom = 'Neutral';
	$scope.confusion = 'Neutral';
	$scope.engaged = 'Neutral';
	$scope.frustration = 'Neutral';

});