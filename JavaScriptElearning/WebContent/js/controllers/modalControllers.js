/**
 * All modals controllers
 */

app.controller('NextLessonModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService) {
	$scope.ok = function() {
		$uibModalInstance.close(this);
		$rootScope.toggleUserFormModal();
		lessonUtilsService.setNextLessonIndex();
		lessonUtilsService.redirectToNextLesson();
	};
});

app.controller('QuizResultModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance, lessonUtilsService,result,questionsSize) {
	$scope.result = result;
	$scope.questionsSize = questionsSize;
	
	$scope.ok = function() {
		$uibModalInstance.close(this);
		lessonUtilsService.setNextLessonIndex();
		lessonUtilsService.redirectToNextLesson();
	};
});

app.controller('UserFormModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance) {

	$scope.ok = function() {
		var ankieta = {
			zlosc : $scope.zlosc,
			znudzenie : $scope.znudzenie
		}
		$rootScope.userForm = ankieta;
		$rootScope.saveUserInput();
		$uibModalInstance.close(this);
	};

	$scope.zlosc = 'Neutral';
	$scope.znudzenie = 'Neutral';

});