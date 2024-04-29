package es.cesguiro.daw1bookstore.domain.service.impl;

import es.cesguiro.daw1bookstore.common.container.BookIoc;
import es.cesguiro.daw1bookstore.common.container.UserIoc;
import es.cesguiro.daw1bookstore.common.exception.ResourceNotFoundException;
import es.cesguiro.daw1bookstore.domain.model.Book;
import es.cesguiro.daw1bookstore.domain.model.Cart;
import es.cesguiro.daw1bookstore.domain.model.CartDetail;
import es.cesguiro.daw1bookstore.domain.model.User;
import es.cesguiro.daw1bookstore.domain.service.CartService;
import es.cesguiro.daw1bookstore.persistence.repository.CartRepository;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart addCartDetail(Cart cart, CartDetail cartDetail) {
        Book book = getBook(cartDetail.getBook().getId());
        cartDetail.setBook(book);
        cartDetail.setPrice(book.getPrice());
        cart.addCartDetail(cartDetail);
        cartRepository.save(cart);
        return cart;
    }

    /*@Override
    public void removeCartDetail(Cart cart, int bookId) {
        Book book = getBook(bookId);
        cart.removeCartDetail(book);
        cartRepository.save(cart);
    }*/

    @Override
    public void removeCartDetail(Cart cart, int cartDetailId) {
        cart.removeCartDetail(cartDetailId);
        cartRepository.save(cart);
    }

    private Book getBook(int bookId) {
        Book book = BookIoc.getBookRepository().findById(bookId);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found");
        }
        return book;
    }
}
