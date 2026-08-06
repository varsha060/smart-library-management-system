package com.varsha.smartlibrary.service;

import com.varsha.smartlibrary.dto.BookCopyRequestDTO;
import com.varsha.smartlibrary.dto.BookCopyResponseDTO;
import com.varsha.smartlibrary.entity.*;
import com.varsha.smartlibrary.enums.BookCopyStatus;
import com.varsha.smartlibrary.exception.ResourceNotFoundException;
import com.varsha.smartlibrary.repository.BookCopyRepository;
import com.varsha.smartlibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository,
                           BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    public BookCopyResponseDTO createBookCopy(BookCopyRequestDTO request){

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found."));

        BookCopy copy = new BookCopy();

        copy.setBook(book);

        if(request.getStatus()==null){
            copy.setStatus(BookCopyStatus.AVAILABLE);
        }else{
            copy.setStatus(BookCopyStatus.valueOf(request.getStatus().toUpperCase()));
        }

        BookCopy saved = bookCopyRepository.save(copy);

        return new BookCopyResponseDTO(
                saved.getId(),
                saved.getBook().getTitle(),
                saved.getStatus().name()
        );
    }

    public List<BookCopyResponseDTO> getAllCopies(){

        return bookCopyRepository.findAll()
                .stream()
                .map(copy ->
                        new BookCopyResponseDTO(
                                copy.getId(),
                                copy.getBook().getTitle(),
                                copy.getStatus().name()))
                .toList();
    }

    public BookCopyResponseDTO getCopy(Long id){

        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book Copy not found."));

        return new BookCopyResponseDTO(
                copy.getId(),
                copy.getBook().getTitle(),
                copy.getStatus().name());
    }

    public BookCopyResponseDTO updateCopy(Long id,
                                          BookCopyRequestDTO request){

        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book Copy not found."));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found."));

        copy.setBook(book);

        copy.setStatus(
                BookCopyStatus.valueOf(
                        request.getStatus().toUpperCase()
                ));

        BookCopy updated = bookCopyRepository.save(copy);

        return new BookCopyResponseDTO(
                updated.getId(),
                updated.getBook().getTitle(),
                updated.getStatus().name());
    }

    public void deleteCopy(Long id){

        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book Copy not found."));

        bookCopyRepository.delete(copy);
    }

}