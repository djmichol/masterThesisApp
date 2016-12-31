/**
 * All modals controllers
 */

app.controller('NextLessonModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService) {
	$scope.ok = function() {
		$uibModalInstance.close(this);		
		lessonUtilsService.setNextLessonIndex();
		if(lessonUtilsService.checkIfLastEditorLesson()){
			$rootScope.toggleUserFormModal();
		}
		lessonUtilsService.redirectToNextLesson();
	};
});

app.controller('QuizResultModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService,result,questionsSize) {
	$scope.result = result;
	$scope.questionsSize = questionsSize;
	
	$scope.ok = function() {
		$uibModalInstance.close(this);
		$rootScope.toggleUserFormModal();
		lessonUtilsService.setNextLessonIndex();
		lessonUtilsService.redirectToNextLesson();
	};
});

app.controller('UserFormModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance) {

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
	};

	$scope.boredom = 'Neutral';
	$scope.confusion = 'Neutral';
	$scope.engaged = 'Neutral';
	$scope.frustration = 'Neutral';

});