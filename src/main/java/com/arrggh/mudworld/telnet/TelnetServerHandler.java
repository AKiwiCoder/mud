package com.arrggh.mudworld.telnet;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelnetServerHandler  extends IoHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(TelnetServerHandler.class);

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        logger.info("Session Opened");
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        logger.info("Session Closed");
    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        logger.info("Session Idel");
    }

    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        logger.info("Message Received");
    }
}
