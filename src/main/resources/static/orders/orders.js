angular.module('app').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:4444/market';

       $scope.loadOrders = function () {
         $http({
                  url: contextPath + '/api/v1/orders',
                  method: 'GET'
              }).then(function (response) {
              $scope.orders = response.data;

              });
          };


          $scope.loadOrders();
});