package com.mprojection.db.tx;

import com.mprojection.annotation.EnableTransaction;
import com.mprojection.db.connection.ConnectionHolder;
import com.mprojection.db.connection.ConnectionManager;
import com.mprojection.exception.DAOException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TransactionHandler implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);
    private static final String CANNOT_INVOKE = "Cannot invoke";
    private static final String CANNOT_COMMIT = "Cannot commit";
    private static final String CANNOT_CLOSE = "Cannot close connection";
    private static final String CANNOT_ROLLBACK = "Cannot rollback";
    @Autowired
    private ConnectionManager connectionManager;
    @Autowired
    private ConnectionHolder connectionHolder;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (methodInvocation.getMethod().isAnnotationPresent(EnableTransaction.class)) {
            return invokeWithTransaction(methodInvocation);
        }
        return invokeWithoutTransaction(methodInvocation);
    }

    private Object invokeWithoutTransaction(MethodInvocation methodInvocation) {
        LOGGER.debug("Invoking without transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            connection.setAutoCommit(true);
            return methodInvocation.proceed();
        } catch (Throwable e) {
            LOGGER.warn(CANNOT_INVOKE, e);
            throw new DAOException(CANNOT_INVOKE, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private Object invokeWithTransaction(MethodInvocation methodInvocation) {
        LOGGER.debug("Invoking with transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            Object result;
            connection.setAutoCommit(false);
            try {
                result = methodInvocation.proceed();
            } catch (Throwable e) {
                LOGGER.warn(CANNOT_INVOKE, e);
                throw new DAOException(CANNOT_INVOKE, e);
            }
            connection.commit();
            return result;
        } catch (Exception e) {
            rollback(connection);
            LOGGER.warn(CANNOT_COMMIT, e);
            throw new DAOException(CANNOT_COMMIT, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(CANNOT_CLOSE, e.getMessage());
            throw new DAOException(CANNOT_CLOSE, e);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(CANNOT_ROLLBACK, e);
            throw new DAOException(CANNOT_ROLLBACK, e);
        }
    }
}