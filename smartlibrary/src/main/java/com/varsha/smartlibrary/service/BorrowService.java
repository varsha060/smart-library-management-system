package com.varsha.smartlibrary.service;

import com.varsha.smartlibrary.dto.BorrowRequestDTO;
import com.varsha.smartlibrary.dto.BorrowResponseDTO;
import com.varsha.smartlibrary.dto.ReturnResponseDTO;
import com.varsha.smartlibrary.entity.*;
import com.varsha.smartlibrary.enums.BookCopyStatus;
import com.varsha.smartlibrary.enums.BorrowStatus;
import com.varsha.smartlibrary.exception.DuplicateResourceException;
import com.varsha.smartlibrary.exception.ResourceNotFoundException;
import com.varsha.smartlibrary.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookCopyRepository bookCopyRepository;
    private final FineRepository fineRepository;


    public BorrowService(
            BorrowRepository borrowRepository,
            MemberRepository memberRepository,
            BookCopyRepository bookCopyRepository,
            FineRepository fineRepository) {

        this.borrowRepository = borrowRepository;
        this.memberRepository = memberRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.fineRepository = fineRepository;
    }
    public BorrowResponseDTO borrowBook(BorrowRequestDTO request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found."));

        BookCopy copy = bookCopyRepository
                .findFirstByBookIdAndStatus(
                        request.getBookId(),
                        BookCopyStatus.AVAILABLE
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException("No available copy found."));

        copy.setStatus(BookCopyStatus.ISSUED);
        bookCopyRepository.save(copy);

        BorrowTransaction transaction = new BorrowTransaction();

        transaction.setMember(member);
        transaction.setCopy(copy);

        transaction.setIssueDate(LocalDate.now());

        transaction.setDueDate(LocalDate.now().plusDays(14));

        transaction.setStatus(BorrowStatus.ISSUED);

        BorrowTransaction saved = borrowRepository.save(transaction);

        return new BorrowResponseDTO(
                saved.getId(),
                member.getFullName(),
                copy.getBook().getTitle(),
                copy.getId(),
                saved.getIssueDate(),
                saved.getDueDate(),
                saved.getStatus().name()
        );
    }
        public ReturnResponseDTO returnBook (Long transactionId){

            BorrowTransaction transaction = borrowRepository.findById(transactionId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Transaction not found."));

            if (transaction.getStatus() == BorrowStatus.RETURNED) {
                throw new DuplicateResourceException("Book already returned.");
            }

            // Update transaction
            transaction.setReturnDate(LocalDate.now());
            transaction.setStatus(BorrowStatus.RETURNED);

            // Make the book copy available again
            BookCopy copy = transaction.getCopy();
            copy.setStatus(BookCopyStatus.AVAILABLE);
            bookCopyRepository.save(copy);

            // Save updated transaction
            BorrowTransaction saved = borrowRepository.save(transaction);

            // Calculate fine
            long lateDays = ChronoUnit.DAYS.between(
                    saved.getDueDate(),
                    saved.getReturnDate()
            );

            if (lateDays > 0) {

                Fine fine = new Fine();
                fine.setTransaction(saved);
                fine.setAmount(BigDecimal.valueOf(lateDays * 10));

                fineRepository.save(fine);
            }

            return new ReturnResponseDTO(
                    saved.getId(),
                    saved.getMember().getFullName(),
                    saved.getCopy().getBook().getTitle(),
                    saved.getIssueDate(),
                    saved.getDueDate(),
                    saved.getReturnDate(),
                    saved.getStatus().name()
            );
        }

}