angular.module('app', []).controller('indexController', function ($scope, $http) {
   const contextPath = 'http://localhost:4444/market/api/v1';

    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
            'p': pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
            console.log(response);
        });
    };

    $scope.loadCart = function () {
      $http({
                url: contextPath + '/cart',
                method: 'GET'
            }).then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.addToCart = function (productId) {
      $http({
                    url: contextPath + '/cart/add/' + productId,
                    method: 'GET'
                }).then(function (response) {
                    $scope.loadCart();
                });
    }


  $scope.deleteProduct = function (productIndex) {
        $http({
            url: contextPath + '/products/delete/' + productIndex,
            method: 'DELETE'
        }).then(function (response) {
           $scope.loadPage();
        });
    };

    $scope.changeQuantity = function (productId, amount) {
   $http({
            url: contextPath + '/cart/quantity',
            method: 'GET',
            params: {
            'p': productId,
            'a': amount
            }
        }).then(function (response) {
           $scope.loadCart();
        });
    };

     $scope.buyCart = function () {
       $http({
                url: contextPath + '/cart/buy',
                method: 'GET'
            }).then(function (response) {
            console.log(response);

            });
        };

        $scope.deleteFromCart = function (productId) {
       $http({
                url: contextPath + '/cart/delete/' + productId,
                method: 'DELETE'
            }).then(function (response) {
               $scope.loadCart();
            });
        };

$scope.generatePagesIndexes = function (startPage, endPage) {
let arr = [];
for (let i = startPage; i < endPage + 1; i++) {
arr.push(i);
}
return arr;
}

    $scope.loadPage();
    $scope.loadCart();
});