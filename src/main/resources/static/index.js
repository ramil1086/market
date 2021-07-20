angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http({
            url: 'http://localhost:4444/market/products',
            method: 'GET',
            params: {
            }
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };

   $scope.loadPage = function (pageIndex = 1) {
   $http({
   url: 'http://localhost:4444/market/products_page',
   method: 'GET',
   params: {
   'p': pageIndex
   }
   }).then(function (response) {
   console.log(response);
   $scope.products = response.data.content;
   });
   };


    $scope.showProductInfo = function (productIndex) {
        $http({
            url: 'http://localhost:4444/market/products/' + productIndex,
            method: 'GET'
        }).then(function (response) {
            alert(response.data.title);
        });
    };

  $scope.deleteProduct = function (productIndex) {
        $http({
            url: 'http://localhost:4444/market/products/delete/' + productIndex,
            method: 'POST'
        }).then(function (response) {
           $scope.loadProducts();
        });
    };

    $scope.loadProducts();
});