package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.Product;
import ru.gb.market.utils.Cart;

import java.security.Principal;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final RedisTemplate<String, Object> redisTemplate;

    private final ProductService productService;


    @Value("${utils.cart.prefix}")
    private  String cartPrefix;

    public String getCartFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUUid() {
       return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }


    public void clearCart(String cartKey) {
        execute(cartKey, c -> c.clear());
    }

    public void changeQuantity(String cartKey, Long productId, int amount) {
        execute(cartKey, c -> c.changeQuantity(amount, productId));
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.delete(productId));
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void addToCart(String cartKey, Long productId) {
        execute(cartKey, c-> {
            if (!c.add(productId)) {
                c.add(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id:" + productId)));
            }

        });
    }

    public void merge (String userCartKey, String guestCartKey) {
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);

    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
