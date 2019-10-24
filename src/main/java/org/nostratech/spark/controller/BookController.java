package org.nostratech.spark.controller;

import com.google.gson.Gson;
import org.nostratech.spark.dto.BookDTO;
import org.nostratech.spark.services.BookService;
import org.nostratech.spark.util.RequestHandler;

import static spark.Spark.*;

/**
 * agus w on 12/14/15.
 */
public class BookController {

    Gson gson = new Gson();

    public BookController(final BookService bookService) {

        post("/book", (request, response) -> {

            // process request
            final BookDTO bookDTO = gson.fromJson(request.body(), BookDTO.class);

            RequestHandler requestHandler = new RequestHandler() {
                @Override
                public Object processRequest() {
                    return bookService.post(bookDTO);
                }
            };

            response.status(200);
            response.type("application/json");
            return requestHandler.getResult("OK");
        });

        put("/book", (request, response) -> {

            // process request
            BookDTO bookDTO = gson.fromJson(request.body(), BookDTO.class);

            RequestHandler requestHandler = new RequestHandler() {
                @Override
                public Object processRequest() {
                    return bookService.put(bookDTO);
                }
            };

            response.status(200);
            response.type("application/json");
            return requestHandler.getResult("OK");
        });

//        get("/book", (request, response) -> {
//            RequestHandler requestHandler = new RequestHandler() {
//                @Override
//                public Object processRequest() {
//                    return bookService.get();
//                }
//            };
//
//            response.status(200);
//            response.type("application/json");
//            return requestHandler.getResult("OK");
//        });

        get("/book/:id", (request, response) -> {
            RequestHandler requestHandler = new RequestHandler() {
                @Override
                public Object processRequest() {
                    return bookService.get(request.params(":id"));
                }
            };

            response.status(200);
            response.type("application/json");
            return requestHandler.getResult("OK");
        });

        delete("/book/:id", (request, response) -> {
            RequestHandler requestHandler = new RequestHandler() {
                @Override
                public Object processRequest() {
                    return bookService.delete(request.params(":id"));
                }
            };

            response.status(200);
            response.type("application/json");
            return requestHandler.getResult("OK");
        });
        // more routes
    }
}
