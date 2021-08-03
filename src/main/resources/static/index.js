angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
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

     $scope.loadOrders = function () {
     if (!$scope.isUserLoggedIn()) {
     return;
     }
       $http({
                url: contextPath + '/orders',
                method: 'GET'
            }).then(function (response) {
            $scope.orders = response.data;

            });
        };

     $scope.createOrder = function () {
       $http.post(contextPath + '/orders', $scope.user)
       .then(function (response) {
            alert('Заказ создан');
            console.log(response);
            $scope.loadCart();
            $scope.loadOrders();

            });
        };

          $scope.clearCart = function () {
               $http({
                        url: contextPath + '/cart/clear',
                        method: 'GET'
                    }).then(function (response) {
                    $scope.cart = null;

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

$scope.tryToAuth = function() {
$http.post(contextPath + '/auth', $scope.user)
.then(function successCallback(response) {
if (response.data.token) {
$http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
$localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

$scope.user.username = null;
$scope.user.password = null;

$scope.loadOrders();
}
}, function errorCallback(response)
{});
};

$scope.clearUser = function() {
delete $localStorage.marketUser;
$http.defaults.headers.common.Authorization = '';
};

$scope.tryToLogout = function() {
$scope.clearUser();
if ($scope.user.username) {
$scope.user.username = null;
}
if ($scope.user.password){
$scope.user.password = null;
}
};


$scope.isUserLoggedIn = function() {
if ($localStorage.marketUser) {
return true;
} else {
return false;
}
};

if ($localStorage.marketUser) {
$http.defaults.headers.common.Authorization ='Bearer ' + $localStorage.marketUser.token;
$scope.loadOrders();
}

    $scope.loadPage();
    $scope.loadCart();
    $scope.loadOrders();
});