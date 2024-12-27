package com.gib.service;

import com.gib.domain.model.User;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.logging.Logger;
import java.util.List;

@ApplicationScoped
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    @Inject
    Mutiny.SessionFactory sf;

    public Uni<User> createUser(User user) {
        LOG.info("Kullanıcı oluşturma başladı: " + user.getUsername());
        return sf.withTransaction((session, tx) -> {
            if (user.getId() != null) {
                user.setId(null); // ID'yi null yaparak yeni kayıt olmasını sağlıyoruz
            }
            return session.persist(user)
                    .chain(session::flush)
                    .map(v -> user);
        });
    }

    public Uni<List<User>> getAllUsers() {
        LOG.info("Tüm kullanıcılar getiriliyor...");
        return sf.withSession(session -> 
            session.createQuery("FROM User", User.class)
                .getResultList()
                .onItem().invoke(users -> 
                    LOG.info("Bulunan kullanıcı sayısı: " + users.size())
                )
        );
    }

    public Uni<User> getUserById(Long id) {
        return sf.withSession(session -> session.find(User.class, id));
    }

    public Uni<User> updateUser(Long id, User updatedUser) {
        return sf.withTransaction((session, tx) ->
            session.find(User.class, id)
                .onItem().ifNotNull().transformToUni(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setAdditionalInfo(updatedUser.getAdditionalInfo());
                    return session.persist(user)
                            .chain(session::flush)
                            .map(v -> user);
                })
        );
    }

    public Uni<Boolean> deleteUser(Long id) {
        return sf.withTransaction((session, tx) ->
            session.find(User.class, id)
                .onItem().ifNotNull().transformToUni(user ->
                    session.remove(user)
                        .chain(session::flush)
                        .map(v -> true)
                )
                .onItem().ifNull().continueWith(false)
        );
    }

    public Uni<List<User>> getUsersByAdditionalInfo(String key, String value) {
        return sf.withSession(session ->
                session.createQuery(
                                "FROM User u WHERE JSONB_EXTRACT_PATH_TEXT(u.additionalInfo, :key) = :value", User.class)
                        .setParameter("key", key)
                        .setParameter("value", value)
                        .getResultList()
        );
    }
}