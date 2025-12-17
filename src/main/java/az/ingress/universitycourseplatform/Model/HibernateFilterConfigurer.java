package az.ingress.universitycourseplatform.Model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class HibernateFilterConfigurer {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void enableFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("deletedFilter")
                .setParameter("isDeleted", false);
    }
}
