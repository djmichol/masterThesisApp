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

app.factory('LoadingInterceptor', function ($q, $rootScope) {

    var numLoadings = 0;

    return {
        request: function (config) {
            numLoadings++;
            $rootScope.$broadcast("loader_show");
            return config || $q.when(config)

        },
        response: function (response) {
            if ((--numLoadings) === 0) {
                $rootScope.$broadcast("loader_hide");
            }
            return response || $q.when(response);

        },
        responseError: function (response) {
            if (!(--numLoadings)) {
                $rootScope.$broadcast("loader_hide");
            }
            return $q.reject(response);
        }
    };
})

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('BearerAuthInterceptor');
    $httpProvider.interceptors.push('LoadingInterceptor');
});