package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.market.utils.Cart;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CART_PREFIX = "MRKT_";

    public String generateCart() {
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(CART_PREFIX + uuid, new Cart());
        return uuid;
    }

    public Cart getCurrentCart(String uuid) {
        if (!redisTemplate.hasKey(CART_PREFIX + uuid)) {
            redisTemplate.opsForValue().set(CART_PREFIX + uuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(CART_PREFIX + uuid);
    }

    public void updateCart(Cart cart, String uuid) {
        redisTemplate.opsForValue().set(CART_PREFIX + uuid, cart);
    }

    public void deleteCart(String uuid) {
        redisTemplate.opsForValue().set(CART_PREFIX + uuid, null);
    }
}
