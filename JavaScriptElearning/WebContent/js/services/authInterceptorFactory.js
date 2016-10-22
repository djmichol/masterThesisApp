app.factory('BearerAuthInterceptor', function ($window, $q,$rootScope) {
    return {
        request: function(config) {
            config.headers = config.headers || {};
            var token = $window.localStorage.getItem('token');
            if (token && !(token === undefined)) {
              config.headers.Authorization = 'Bearer ' + $window.localStorage.getItem('token');
            }
            return config || $q.when(config);
        },
        responseError: function(response) {
            if (response.status === 401) {
            	$rootScope.addAlert('danger','Dostęp dla zalogowanych użytkowników.');
            }
            if (response.status === 403) {
            	$rootScope.addAlert('danger','Brak dostępu.');
            }
            return $q.reject(response);
        }
    };
});

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('BearerAuthInterceptor');
});