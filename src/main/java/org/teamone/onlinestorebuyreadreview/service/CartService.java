package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.CartItem;
import org.teamone.onlinestorebuyreadreview.database.repository.BookRepository;
import org.teamone.onlinestorebuyreadreview.database.repository.CartRepository;
import org.teamone.onlinestorebuyreadreview.dto.cart.BookIdAndQuantityDto;
import org.teamone.onlinestorebuyreadreview.service.exception.BookNotFoundException;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    public Cart putItem(Cart cart, Long bookId) {
        cart.addItem(CartItem.builder()
                .cartId(cart.getId())
                .quantity(1)
                .book(bookRepository.read(bookId).orElseThrow(() -> new BookNotFoundException(bookId)))
                .build());
        return cart;
    }

    @Transactional
    public Cart getCartByClientId(Long id) {
        Optional<Cart> optionalCart = cartRepository.read(id);
        return optionalCart.orElseGet(() -> cartRepository.create(Cart.builder()
                .id(id)
                .build()).get());
    }
    public Cart removeItem(Cart cart, Long bookId) {
        cart.removeItemByBookId(bookId);
        return cart;
    }

    public Cart updateItemQuantity(Cart cart, BookIdAndQuantityDto bookIdAndQuantityDto) {
        cart.updateItemQuantityByBookId(Long.parseLong(bookIdAndQuantityDto.getBookId()), Integer.parseInt(bookIdAndQuantityDto.getQuantity()));
        return cart;
    }

    public void updateCart(Cart cart) {
        cartRepository.update(cart.getId(),cart);
    }

    public Cart getLastDataAboutCartItems(Cart cart) {
        cart.setCartItems(cartRepository.readLastData(cart.getCartItems()));
        return cart;
    }

    public Cart clearCart(Cart cart) {
        cart.clear();
        return cart;
    }
}
