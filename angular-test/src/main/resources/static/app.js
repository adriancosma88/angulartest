var myApp = angular.module('myApp', []);

myApp.controller('mainController', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {

    $scope.handle = '';

    $scope.lowercasehandle = function () {
        return $filter('lowercase')($scope.handle);
    };

    $scope.characters = 5;

    $http.get('/rules')
        .success(function(result){
        
        $scope.rules = result._embedded.rules;
        
    })
        .error(function(data, status){
        
        console.log(data);
        
    })
    
    $scope.newRule = '';
    $scope.addRule = function () {
        $http.post('/rules', {name: $scope.newRule})
            .success(function(result) {
            $scope.rules.push(result);
            $scope.newRule = '';
        })
            .error(function(data, status) {
            console.log(data);
        })
    }

}]);