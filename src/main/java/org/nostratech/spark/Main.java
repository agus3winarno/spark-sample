package org.nostratech.spark;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.nostratech.spark.controller.BookController;
import org.nostratech.spark.controller.OptionsController;
import org.nostratech.spark.filter.CorsFilter;
import org.nostratech.spark.routes.RouteBuilder;
import org.nostratech.spark.services.BookService;
import org.nostratech.spark.swagger.SwaggerParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static spark.Spark.before;
import static spark.Spark.port;
import static spark.Spark.get;

@SwaggerDefinition(host = "localhost:8080",
        info = @Info(description = "Book API",
                version = "V1.0",
                title = "Book API for testing",
                contact = @Contact(name = "agus winarno", url = "https://agus3winarno.com", email = "agus3winarno@gmail.com") ) ,
        schemes = { SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS },
        consumes = { "application/json" },
        produces = { "application/json" },
        tags = { @Tag(name = "swagger") })
public class Main {

    private final static String REST_API_PACKAGE = "org.nostratech.spark";

    public static void main(String[] args) {

        port(8080);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Beans.xml");

        BookService bookService = (BookService) applicationContext.getBean("bookService");

        new BookController(bookService);

        try {
            before(new CorsFilter());
            new OptionsController();

            RouteBuilder.setupRoutes(REST_API_PACKAGE);

            final String swaggerJson = SwaggerParser.getSwaggerJson(REST_API_PACKAGE);
            get("/swagger", (req, res) -> swaggerJson);

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }

    }
}
