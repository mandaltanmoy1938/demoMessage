package com.example.demoMessage;

import org.hibernate.Session;

/**
 * Created by Tanmoy Mandal on 12/29/2016.
 */
public interface SessionManagerAwareValidator {
    void setSessionManager(Session session);
}
