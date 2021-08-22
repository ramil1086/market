angular.module('app').controller('productsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:4444/market';

    $scope.addToCart = function (productId) {
    { if($scope.isUserLoggedIn())
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
//            $scope.loadCart();
        });
        }
    }

  $scope.loadPage = function (pageIndex = 1) {
          $http({
              url: contextPath + '/api/v1/products',
              method: 'GET',
              params: {
              'p': pageIndex,
              'min_price': $scope.filter != null ? $scope.filter.minPrice : null,
              'max_price': $scope.filter != null ? $scope.filter.maxPrice : null,
              'title': $scope.filter !=null ? $scope.filter.title : null
              }
          }).then(function (response) {
              $scope.productsPage = response.data;
              $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
              console.log(response);
          });
      };

   $scope.generatePagesIndexes = function (startPage, endPage) {
   let arr = [];
   for (let i = startPage; i < endPage + 1; i++) {
   arr.push(i);
   }
   return arr;
   }


  $scope.deleteProduct = function (productIndex) {
        $http({
            url: contextPath + '/api/v1/products/delete/' + productIndex,
            method: 'DELETE'
        }).then(function (response) {
           $scope.loadPage();
        });
    };



    $scope.loadPage();
});