package com.vipul_acciojob.bookManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService = new BookService();
    @PostMapping("/add-new-book")
    public ResponseEntity<String> addBook(@RequestBody Book book){
//        bookData.put(book.getId(), book);
        String str = bookService.addBook(book);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }
    @GetMapping("/get-book")
    public ResponseEntity <?> getBook(@RequestParam Integer id){
        try {
            Book book = bookService.getBook(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (BookNotFoundException ex){
            return new ResponseEntity<>("book with id not found : " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/get-book-by-name/{name}")
    public ResponseEntity<Book> getBookByName(@PathVariable String name){
        try {
            return new ResponseEntity<>(bookService.getBook(name), HttpStatus.OK);
        } catch(BookNotFoundException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update-book-page/{id}") //update-book-page/1?pages=500
    public ResponseEntity<Book> updateBookPages(@PathVariable Integer id, @RequestParam Integer pages){
       try {
           Book book = bookService.updateBookPages(id, pages);
           return new ResponseEntity<>(book, HttpStatus.CREATED);
       } catch(BookNotFoundException ex){
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }
//
//    @PutMapping("/update-book-author") //update-book-page/1?pages=500
//    public Book updateBookAuthor(@RequestParam Integer id,@RequestParam String author){
//        Book book = bookData.get(id);
//        book.setAuthor(author);
//        bookData.put(id,book);
//        return book;
//    }
//
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        bookService.removeBookById(id);
        return new ResponseEntity<>("Book deleted with id:" + id, HttpStatus.OK);
    }
}

