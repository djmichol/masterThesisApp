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