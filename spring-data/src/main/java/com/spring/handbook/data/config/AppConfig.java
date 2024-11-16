package com.spring.handbook.data.config;

import com.spring.handbook.data.listener.UserListener;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = emf.unwrap(SessionFactoryImpl.class)
                .getServiceRegistry()
                .getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_COMMIT_UPDATE).appendListener(UserListener.INSTANCE);
    }

}
