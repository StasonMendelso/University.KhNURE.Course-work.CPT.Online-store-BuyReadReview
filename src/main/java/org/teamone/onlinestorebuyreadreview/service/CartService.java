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

    public Cart getCartByClientId(Long id) {
        //TODO: 07.06.2023 IMPLEMENT
        throw new UnsupportedOperationException();
    }

    public Cart removeItem(Cart cart, Long bookId) {
        cart.removeItemByBookId(bookId);
        return cart;
    }

    public Cart updateItemQuantity(Cart cart, BookIdAndQuantityDto bookIdAndQuantityDto) {
        cart.updateItemQuantityByBookId(Long.parseLong(bookIdAndQuantityDto.getBookId()), Integer.parseInt(bookIdAndQuantityDto.getQuantity()));
        return cart;
    }
}
