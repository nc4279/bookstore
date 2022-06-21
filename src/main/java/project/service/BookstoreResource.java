package project.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.quarkus.panache.common.Parameters;
import project.entity.Book;
import project.model.DAO.BookDAO;
import project.model.DAO.BookstoreDAO;
import project.model.DAO.CopyDAO;

@Path("bookstore")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookstoreResource {
    
    @Inject
    BookDAO bookDAO;
    
    @Inject
    BookstoreDAO bookstoreDAO;

    @Inject
    CopyDAO copyDAO;

    @GET
    @Path("mistic/books")
    public Response getAllBooksFromMistic(){
        return Response.ok(bookDAO.find("select distinct b from Book b join Copy c on b.id=c.book join Bookstore bo on c.bookstore.id = :id", Parameters.with("id",1)).list()).build();
    }

    @GET
    @Path("bistical/books")
    public Response getAllBooksFromBistical(){
        return Response.ok(bookDAO.find("select distinct b from Book b join Copy c on b.id=c.book join Bookstore bo on c.bookstore.id = :id", Parameters.with("id",2)).list()).build();
    }

    @GET
    @RolesAllowed({"user","admin", "superadmin"})
    @Path("books")
    public Response getBooks(){
        return Response.ok().build();
    }
}
