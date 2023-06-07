package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;
import org.teamone.onlinestorebuyreadreview.database.entity.User;
import org.teamone.onlinestorebuyreadreview.dto.cart.BookIdAndQuantityDto;
import org.teamone.onlinestorebuyreadreview.dto.cart.BookIdDto;
import org.teamone.onlinestorebuyreadreview.http.handler.api.exception.ApiErrorException;
import org.teamone.onlinestorebuyreadreview.http.handler.api.exception.ApiSubErrorException;
import org.teamone.onlinestorebuyreadreview.http.handler.api.exception.ApiValidationExceptionMapper;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;
import org.teamone.onlinestorebuyreadreview.service.CartService;
import org.teamone.onlinestorebuyreadreview.util.constant.SessionConstant;
import org.teamone.onlinestorebuyreadreview.util.mapper.cart.CartMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.cart.CartTotalAndQuantityMapper;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/cart")
@SessionAttributes(SessionConstant.CART)
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ApiValidationExceptionMapper apiValidationExceptionMapper;
    private final CartTotalAndQuantityMapper cartTotalAndQuantityMapper;
    private final CartMapper cartMapper;

    @GetMapping
    public String showCart(Model model,
                           Cart cart,
                           Authentication authentication) {
        User user = ((AuthUserDetails) authentication.getPrincipal()).getUser();
        if (user.getRole().equals(Role.CLIENT)) {
            cart = cartService.getCartByClientId(user.getId());
        }
        model.addAttribute("cart", cart);

        return "cart/cart";
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> addCartItem(Cart cart,
                                         @RequestBody @Validated BookIdDto bookIdDto,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Validation errors", apiValidationExceptionMapper.map(bindingResult)
                    .stream()
                    .map(exception -> (ApiSubErrorException) exception)
                    .toList());
        }
        cart = cartService.putItem(cart, Long.valueOf(bookIdDto.getBookId()));

        return ResponseEntity.ok(cartTotalAndQuantityMapper.map(cart));
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity<?> deleteCartItem(Cart cart,
                                            @RequestBody @Validated BookIdDto bookIdDto,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Validation errors", apiValidationExceptionMapper.map(bindingResult)
                    .stream()
                    .map(exception -> (ApiSubErrorException) exception)
                    .toList());
        }
        cart = cartService.removeItem(cart, Long.valueOf(bookIdDto.getBookId()));

        return ResponseEntity.ok(cartMapper.map(cart));
    }

    @ResponseBody
    @PutMapping
    public ResponseEntity<?> updateCartItemQuantity(Cart cart,
                                                    @RequestBody @Validated BookIdAndQuantityDto bookIdAndQuantityDto,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Validation errors", apiValidationExceptionMapper.map(bindingResult)
                    .stream()
                    .map(exception -> (ApiSubErrorException) exception)
                    .toList());
        }
        cart = cartService.updateItemQuantity(cart, bookIdAndQuantityDto);

        return ResponseEntity.ok(cartMapper.map(cart));
    }
}
