package com.jpa;

import com.jpa.repository.BookRepository;
import com.jpa.repository.BookRepositoryEM;
import com.jpa.repository.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String UPDATED_TITLE = "updater #1";

    @Autowired
    private BookRepositoryEM bookRepositoryEM;

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        new Timer().scheduleAtFixedRate(insertBookByEntityManagerTask(), 100, 2000);
        new Timer().scheduleAtFixedRate(insertBook(), 100, 2000);
        new Timer().scheduleAtFixedRate(updateBookByEntityManagerTask(), 1000, 2000);
        new Timer().scheduleAtFixedRate(printBookCountTaskByEM(), 1000, 2000);
        new Timer().scheduleAtFixedRate(printBookCountTaskByAI(), 1000, 2000);
    }

    private TimerTask insertBookByEntityManagerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                Book book = new Book(UUID.randomUUID().toString(), LocalDateTime.now());
                bookRepositoryEM.save(book);
                LOGGER.info("Saved: {}", book);
            }
        };
    }

    private TimerTask insertBook() {
        return new TimerTask() {
            @Override
            public void run() {
                Book book = new Book(UUID.randomUUID().toString(), LocalDateTime.now());
                bookRepository.save(book);
                LOGGER.info("Saved by some magic: {}", book);
            }
        };
    }

    private TimerTask updateBookByEntityManagerTask() {
        return new TimerTask() {
            long id = 1;

            @Override
            public void run() {
                Book book = bookRepositoryEM.findById(id);
                book.setTitle(UPDATED_TITLE);
                bookRepositoryEM.update(book);
                LOGGER.info("Updated: {}", book);
                id++;
            }
        };
    }

    private TimerTask printBookCountTaskByEM() {
        return new TimerTask() {
            @Override
            public void run() {
                LOGGER.info("Count: {}", bookRepositoryEM.findByTitle(UPDATED_TITLE).size());
            }
        };
    }

    private TimerTask printBookCountTaskByAI() {
        return new TimerTask() {
            @Override
            public void run() {
                LOGGER.info("Count by magic: {}", bookRepository.findByTitle(UPDATED_TITLE).size());
            }
        };
    }
}
