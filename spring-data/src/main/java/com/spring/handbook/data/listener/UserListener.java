package com.spring.handbook.data.listener;

import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class UserListener implements PostUpdateEventListener {

    public static UserListener INSTANCE = new UserListener();


    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        System.out.println();
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}
