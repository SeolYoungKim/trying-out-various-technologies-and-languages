package hello.jpa.concurrency;

import hello.jpa.many_to_many.domain.Author;
import hello.jpa.many_to_many.domain.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Transactional
@Service
public class PseudoService {
    private final AuthorRepository authorRepository;

    public PseudoService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createAuthor() {
        authorRepository.save(new Author("kim"));
        log.info("현재 스레드 이름 : {} | isNewTransaction={}", Thread.currentThread().getName(),TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
    }
}
