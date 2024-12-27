package com.gib.api;

import com.gib.service.UserService;
import com.gib.domain.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User Management", description = "Kullanıcı yönetimi için API endpointleri")
public class UserResource {

    @Inject
    UserService userService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Operation(summary = "Yeni kullanıcı oluştur", 
              description = "Verilen bilgilerle yeni bir kullanıcı oluşturur")
    @APIResponse(
        responseCode = "201",
        description = "Kullanıcı başarıyla oluşturuldu",
        content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                         schema = @Schema(implementation = User.class))
    )
    public Uni<Response> createUser(
        @RequestBody(description = "Oluşturulacak kullanıcı bilgileri", 
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class)))
        User user) {
        return userService.createUser(user)
                .onItem().transform(created -> Response.status(Response.Status.CREATED).entity(created).build());
    }

    @POST
    @Path("/test")
    @Operation(summary = "Test kullanıcısı oluştur", 
              description = "Önceden tanımlanmış bilgilerle test amaçlı bir kullanıcı oluşturur")
    @APIResponse(
        responseCode = "201",
        description = "Test kullanıcısı başarıyla oluşturuldu",
        content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Uni<Response> createTestUser() {
        User user = new User();
        user.setUsername("test_user");
        user.setEmail("test@example.com");

        String jsonStr = """
            {
                "age": 25,
                "address": {
                    "city": "Istanbul",
                    "country": "Turkey"
                },
                "hobbies": ["reading", "swimming"]
            }
            """;

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            user.setAdditionalInfo(jsonNode);
            return createUser(user);
        } catch (Exception e) {
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

    @GET
    @Operation(summary = "Tüm kullanıcıları listele", 
              description = "Sistemdeki tüm kullanıcıları listeler")
    @APIResponse(
        responseCode = "200",
        description = "Kullanıcılar başarıyla listelendi",
        content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Uni<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "ID ile kullanıcı getir", 
              description = "Verilen ID'ye sahip kullanıcıyı getirir")
    @APIResponse(
        responseCode = "200",
        description = "Kullanıcı başarıyla bulundu",
        content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
        responseCode = "404",
        description = "Kullanıcı bulunamadı"
    )
    public Uni<Response> getUserById(
        @Parameter(description = "Kullanıcı ID", required = true)
        @PathParam("id") Long id) {
        return userService.getUserById(id)
                .onItem().transform(user -> user != null
                        ? Response.ok(user).build()
                        : Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Kullanıcı güncelle", 
              description = "Verilen ID'ye sahip kullanıcıyı günceller")
    @APIResponse(
        responseCode = "200",
        description = "Kullanıcı başarıyla güncellendi",
        content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
        responseCode = "404",
        description = "Güncellenecek kullanıcı bulunamadı"
    )
    public Uni<Response> updateUser(
        @Parameter(description = "Kullanıcı ID", required = true)
        @PathParam("id") Long id,
        @RequestBody(description = "Güncellenmiş kullanıcı bilgileri", 
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class)))
        User user) {
        return userService.updateUser(id, user)
                .onItem().transform(updated -> updated != null
                        ? Response.ok(updated).build()
                        : Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Kullanıcı sil", 
              description = "Verilen ID'ye sahip kullanıcıyı siler")
    @APIResponse(
        responseCode = "204",
        description = "Kullanıcı başarıyla silindi"
    )
    @APIResponse(
        responseCode = "404",
        description = "Silinecek kullanıcı bulunamadı"
    )
    public Uni<Response> deleteUser(
        @Parameter(description = "Kullanıcı ID", required = true)
        @PathParam("id") Long id) {
        return userService.deleteUser(id)
                .onItem().transform(deleted -> deleted
                        ? Response.noContent().build()
                        : Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/search")
    @Operation(summary = "JSON alanlarında arama yap", 
              description = "Kullanıcıların JSON alanlarında arama yapar")
    @APIResponse(
        responseCode = "200",
        description = "Arama başarıyla tamamlandı",
        content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Uni<List<User>> getUsersByAdditionalInfo(
        @Parameter(description = "Aranacak JSON anahtarı", required = true)
        @QueryParam("key") String key,
        @Parameter(description = "Aranacak değer", required = true)
        @QueryParam("value") String value) {
        return userService.getUsersByAdditionalInfo(key, value);
    }
}