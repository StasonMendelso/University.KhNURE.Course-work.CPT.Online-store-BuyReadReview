package org.teamone.onlinestorebuyreadreview.util.mapper.cart;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.CartItem;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.dto.cart.ReadCartItemDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Component
public class ReadCartItemMapper implements Mapper<CartItem, ReadCartItemDto> {
    @Override
    public ReadCartItemDto map(CartItem cartItem) {
        Book book = cartItem.getBook();
        return ReadCartItemDto.builder()
                .quantity(cartItem.getQuantity())
                .price(book.getPrice().setScale(2))
                .subtotal(book.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())).setScale(2))
                .bookId(book.getId())
                .fileId(book.getFiles()
                        .stream()
                        .findFirst()
                        .map(File::getId)
                        .orElse(0L))
                .article(book.getArticle())
                .name(book.getTitle())
                .build();
    }
}
