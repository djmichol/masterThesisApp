app.controller('ModalInstanceCtrl', function($scope, $rootScope, $uibModalInstance) {

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