var app = angular.module('btsApp', []);
app.controller('developerListCtr', function($scope, $http) {

	$scope.url = "AdminServlet";
	var config = 'contenttype';
	$scope.type = 'DEVELOPER_LIST';
	$scope.list = [];
	$scope.typeWrapper = {
		type : $scope.type
	};

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		$scope.list = response.data;
	}, function(response) {

	});
	
	$scope.developerWiseProjectList = [];
	$scope.setProjectList = function(developerObj){
		$scope.developerWiseProjectList = developerObj.projectList;
	}
});