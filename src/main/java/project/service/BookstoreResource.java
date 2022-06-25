package project.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.modelmapper.ModelMapper;

import io.quarkus.security.identity.SecurityIdentity;
import project.DAO.BookDAO;
import project.DAO.BookstoreDAO;
import project.DAO.CopyDAO;
import project.entity.Book;
import project.entity.Bookstore;
import project.entity.CompositeKey;
import project.entity.Copy;
import project.model.DTO.BookForAdminDTO;
import project.model.DTO.BookForPublicDTO;
import project.model.DTO.BookForUserDTO;
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
        List<BookForPublicDTO> newBooks = books.stream().map(book -> modelMapper.map(book, BookForPublicDTO.class))
                .collect(Collectors.toList());
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
        List<BookForPublicDTO> newBooks = books.stream().map(book -> modelMapper.map(book, BookForPublicDTO.class))
                .collect(Collectors.toList());
        return Response.ok(newBooks).build();
    }

    @GET
    @RolesAllowed({ "user", "admin", "superadmin" })
    @Path("mybooks")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Get all books from user's bookstore", description = "Only login users can access this endpoint, data info depends on user's role")
    public Response getUsersBooks() {
        Integer bookstoreID = Integer.parseInt(accessToken.getClaim("bookstoreID").toString());
        if (identity.getRoles().contains("user")) {
            String author = accessToken.getClaim("author");
            String group = accessToken.getClaim("group").toString().substring(3, 9);
            if (Objects.nonNull(author) && Objects.nonNull(group)) {
                return Response.ok(bookDAO.getBooksForAuthor(author)).build();
            } else if (Objects.nonNull(bookstoreID)) {
                List<Book> books = bookDAO.getBooksFromBookstore(bookstoreID);
                List<BookForUserDTO> myBooks = books.stream().map(book -> modelMapper.map(book, BookForUserDTO.class))
                        .collect(Collectors.toList());
                return Response.ok(myBooks).build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
            // Log.info("group name -> " + group);
        } else if ((identity.getRoles().contains("admin") || identity.getRoles().contains("superadmin"))
                && Objects.nonNull(bookstoreID)) {
            List<Book> books = bookDAO.getBooksFromBookstore(bookstoreID);
            List<BookForAdminDTO> myBooks = books.stream().map(book -> modelMapper.map(book, BookForAdminDTO.class))
                    .collect(Collectors.toList());
            return Response.ok(myBooks).build();
        } else {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }

    }

    // @GET
    // @Path("mybooks")
    // @APIResponses(value = {
    // @APIResponse(responseCode = "200", description = "Success", content =
    // @Content(mediaType = "application/json", schema = @Schema(implementation =
    // Book.class))),
    // @APIResponse(responseCode = "401", description = "Not Authorized", content =
    // @Content(mediaType = "application/json")),
    // @APIResponse(responseCode = "403", description = "Not Allowed", content =
    // @Content(mediaType = "application/json")),
    // @APIResponse(responseCode = "404", description = "Not Found", content =
    // @Content(mediaType = "application/json"))
    // })
    // @Operation(summary = "Get all books from author in bookstore", description =
    // "Only login users with group 'writer' can access this endpoint")
    // public Response getWritersBooks(@QueryParam("bookstoreID") Integer
    // bookstoreID,
    // @QueryParam("author") String author) {
    // //String bookstore = accessToken.getClaim("poslovniPartner");
    // //identity.getRoles().contains("manager") za role
    // return Response.ok(bookDAO.getBooksForAuthor(bookstoreID, author)).build();
    // }

    @POST
    @RolesAllowed({ "admin", "superadmin" })
    @Path("add")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "405", description = "Method Not Allowed", content = @Content(mediaType = "application/json"))

    })
    @Operation(summary = "Add book to bookstore", description = "Only login users with roles 'admin' and 'superadmin' can access this endpoint")
    public Response postBook(@NotNull @Valid NewBookDTO newBookDTO) {
        // String bookstore = accessToken.getClaim("poslovniPartner");
        // identity.getRoles().contains("manager") za role
        Integer bookstoreID = Integer.parseInt(accessToken.getClaim("bookstoreID").toString());
        if ((identity.getRoles().contains("admin") || identity.getRoles().contains("superadmin"))
                && Objects.nonNull(bookstoreID)) {

            // Integer newId =
            // bookDAO.listAll(Sort.by("id")).get(bookDAO.listAll().size()-1).getId();
            Book book = modelMapper.map(newBookDTO, Book.class);
            // Log.info(newId);
            // book.setId(newId+1);
            bookDAO.persist(book);
            // bookDAO.insertBook(book);
            // copyDAO.persist();
            // Log.info(book.toString());

            CompositeKey newKey = new CompositeKey(bookstoreID, book.getId());
            Bookstore bookstore = bookstoreDAO.find("id", bookstoreID).firstResult();
            Copy newCopy = new Copy(newKey, bookstore, book, newBookDTO.getCopies(), 0);
            // Log.info(newCopy);
            copyDAO.merge(newCopy); // zakaj dobim detached entity BOOK?
            return Response.ok(book).build();

        } else {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
    }

    @DELETE
    @RolesAllowed("superadmin")
    @Path("delete")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @APIResponse(responseCode = "401", description = "Not Authorized", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "403", description = "Not Allowed", content = @Content(mediaType = "application/json")),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json"))
    })
    @Operation(summary = "Delete book from bookstore", description = "Only login users with roles 'superadmin' can access this endpoint")
    public Response deleteBook(@NotNull @QueryParam("bookID") Integer bookID) {
        Integer bookstoreID = Integer.parseInt(accessToken.getClaim("bookstoreID").toString());

        if (identity.getRoles().contains("superadmin")
                && Objects.nonNull(bookstoreID)) {

            if (Objects.nonNull(bookDAO.find("id", bookID).firstResult())) {
                Book book = bookDAO.find("id", bookID).firstResult();
                Bookstore bookstore = bookstoreDAO.find("id", bookstoreID).firstResult();
                CompositeKey key = new CompositeKey(bookstore.getId(), book.getId());
                Copy copy = copyDAO.find("id", key).firstResult();
                if (Objects.nonNull(copy)) {

                    copyDAO.delete(key);
                    // ko zelis izbrisati detached entity -> to naredis v DAO z entityManager!!!
                    bookDAO.deleteBook(book);
                    return Response.ok().build();
                } else {
                    return Response.status(Status.CONFLICT).build();
                }
            } else {
                return Response.status(Status.CONFLICT).build();
            }

        } else {
            return Response.status(Status.METHOD_NOT_ALLOWED).build();
        }
    }
}
