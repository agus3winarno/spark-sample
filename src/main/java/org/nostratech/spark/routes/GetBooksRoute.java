package org.nostratech.spark.routes;

import io.swagger.annotations.*;
import org.nostratech.spark.services.BookService;
import org.nostratech.spark.util.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Api
@Path("/book")
@Produces("application/json")
public class GetBooksRoute implements Route {

	@Autowired
	BookService bookService;

	@GET
    @ApiOperation(value = "Get All Book")
	@ApiImplicitParams({
			@ApiImplicitParam(required = true, dataType="string", name="Authentication", paramType = "header"),
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response=String.class),
			@ApiResponse(code = 401, message = "Unauthorized", response=String.class)
	})
	public String handle(@ApiParam(hidden=true) Request request, @ApiParam(hidden=true) Response response) throws Exception {

		RequestHandler requestHandler = new RequestHandler() {
			@Override
			public Object processRequest() {
				return bookService.get();
			}
		};

		response.status(200);
		response.type("application/json");
		return requestHandler.getResult("OK");
	}

}