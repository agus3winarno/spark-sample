package org.nostratech.spark.services;

import org.nostratech.spark.dto.BookDTO;
import org.nostratech.spark.model.Book;
import org.nostratech.spark.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * agus w on 12/14/15.
 */
@Component("bookService")
public class BookService implements CRUDService<BookDTO> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Boolean post(BookDTO bookDTO) {

        Book book = new Book();

        if (bookDTO.getId() == null) book.setSecureId(UUID.randomUUID().toString());

        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(book.getTitle());

        book = bookRepository.save(book);

        return (book.getId() != null);
    }

    @Override
    public Boolean put(BookDTO bookDTO) {

        Book book = bookRepository.findBySecureId(bookDTO.getId());

        if(book != null) {

            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());

            book = bookRepository.save(book);

            return (book.getId() != null);

        }

        return false;
    }

    @Override
    public Boolean delete(String id) {
        Book book = bookRepository.findBySecureId(id);

        if(book != null){
            bookRepository.delete(book);
        }

        return (bookRepository.findBySecureId(id) == null);
    }

    @Override
    public Collection<BookDTO> get() {

        List<BookDTO> bookDTOList = new ArrayList<>();

        for (Book book : bookRepository.findAll()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getSecureId());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setTitle(book.getTitle());

            bookDTOList.add(bookDTO);
        }

        return bookDTOList;
    }

    @Override
    public BookDTO get(String id) {
        BookDTO bookDTO = null;

        Book book = bookRepository.findBySecureId(id);

        if(book != null){
            bookDTO = new BookDTO();
            bookDTO.setId(id);
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setTitle(book.getTitle());
        }

        return bookDTO;
    }

}
