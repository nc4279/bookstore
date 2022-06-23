package project.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.modelmapper.ModelMapper;

import io.quarkus.security.identity.SecurityIdentity;
import project.entity.Book;
import project.model.DAO.BookDAO;
import project.model.DAO.BookstoreDAO;
import project.model.DAO.CopyDAO;
import project.model.DTO.BookForPublicDTO;
import project.model.DTO.NewBookDTO;

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

    @Inject
    JsonWebToken accessToken;
    @Inject
    SecurityIdentity identity;

    ModelMapper modelMapper = new ModelMapper();
    
    @GET
    @Path("mistic/books")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Get all books from bookstore Mistic", description = "Everyone can access this endpoint")
    public Response getAllBooksFromMistic() {
        List<Book> books = bookDAO.getBooksFromBookstore(1);
        List<BookForPublicDTO> newBooks = books.stream().map(book -> modelMapper.map(book, BookForPublicDTO.class)).collect(Collectors.toList());
        return Response.ok(newBooks).build();
    }

    @GET
    @Path("bistical/books")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Get all books from bookstore Bistical", description = "Everyone can access this endpoint")
    public Response getAllBooksFromBistical() {
        List<Book> books = bookDAO.getBooksFromBookstore(2);
        List<BookForPublicDTO> newBooks = books.stream().map(book -> modelMapper.map(book, BookForPublicDTO.class)).collect(Collectors.toList());
        return Response.ok(newBooks).build();
    }

    @GET
    @RolesAllowed({ "user", "admin", "superadmin" })
    @Path("books")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Get all books from user's bookstore", description = "Only login users can access this endpoint")
    public Response getUsersBooks() {
        //String bookstore = accessToken.getClaim("poslovniPartner");
        //identity.getRoles().contains("manager") za role
        return Response.ok().build();
    }

    @GET
    @Path("mybooks")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Get all books from author in bookstore", description = "Only login users with group 'writer' can access this endpoint")
    public Response getWritersBooks(@QueryParam("bookstoreID") int bookstoreID, @QueryParam("author") String author) {
        //String bookstore = accessToken.getClaim("poslovniPartner");
        //identity.getRoles().contains("manager") za role
        return Response.ok(bookDAO.getBooksForAuthor(bookstoreID, author)).build();
    }

    @POST
    @RolesAllowed({"admin", "superadmin" })
    @Path("add")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Add book to bookstore", description = "Only login users with roles 'admin' and 'superadmin' can access this endpoint")
    public Response postBook( @NotNull @Valid NewBookDTO newBookDTO) {
        //String bookstore = accessToken.getClaim("poslovniPartner");
        //identity.getRoles().contains("manager") za role
        Book book = modelMapper.map(newBookDTO, Book.class);
        bookDAO.persist(book);
        return Response.ok().build();
    }

    @POST
    @RolesAllowed("superadmin")
    @Path("delete")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Delete book from bookstore", description = "Only login users with roles 'superadmin' can access this endpoint")
    public Response deleteBook(@QueryParam("bookID") int bookID) {
        //String bookstore = accessToken.getClaim("poslovniPartner");
        //identity.getRoles().contains("manager") za role
        bookDAO.delete("id", bookID);
        return Response.ok().build();
    }
}
