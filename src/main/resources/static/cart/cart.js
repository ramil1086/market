angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:4444/market';


    $scope.loadCart = function () {
      $http({
                url: contextPath + '/api/v1/cart',
                method: 'GET'
            }).then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.addToCart = function (productId) {
      $http({
                    url: contextPath + '/api/v1/cart/add/' + productId,
                    method: 'GET'
                }).then(function (response) {
                    $scope.loadCart();
                });
    }

        $scope.changeQuantity = function (productId, amount) {
       $http({
                url: contextPath + '/api/v1/cart/quantity',
                method: 'GET',
                params: {
                'p': productId,
                'a': amount
                }
            }).then(function (response) {
               $scope.loadCart();
            });
        };

//   $scope.createOrder = function () {
//       $http.post(contextPath + '/api/v1/orders', $scope.user)
//       .then(function (response) {
//            alert('Заказ создан');
//            console.log(response);
//            $scope.loadCart();
//
//            });
//        };

   $scope.createOrder = function () {
       $http({
       url: contextPath + '/api/v1/orders',
       method : 'POST',
       params: {
       address: $scope.order_info.address,
       phone: $scope.order_info.phone
       }
       })
       .then(function (response) {
            alert('Заказ создан');
            console.log(response);
            $scope.loadCart();
            });
        };

           $scope.clearCart = function () {
                       $http({
                                url: contextPath + '/api/v1/cart/clear',
                                method: 'GET'
                            }).then(function (response) {
                            $scope.cart = null;

                            });
                        };

                $scope.deleteFromCart = function (productId) {
               $http({
                        url: contextPath + '/api/v1/cart/delete/' + productId,
                        method: 'DELETE'
                    }).then(function (response) {
                       $scope.loadCart();
                    });
                };
                $scope.loadCart();





});