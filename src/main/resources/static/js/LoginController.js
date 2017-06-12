springChat.controller('LoginController', ['$scope', '$http','$state' ,function ($scope, $http,$state){


$scope.creds ={};

$scope.login = function(params){




$http.post('/Chat/login', params).then(function (response) {
	
		$state.go('chat');
            return response.data;
        }, function (error) {
            return error;
        });

	}

}]);