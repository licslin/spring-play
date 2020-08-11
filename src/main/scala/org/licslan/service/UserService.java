package org.licslan.service;

import org.licslan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional  //给方法加注解  事务回滚操作
    public void insertUser(){
        userDao.insert();
        //int i = 10/0;
        System.out.println("insert end....");
    }




    /**实际上这个没有必要死记硬背  需要理解  用的时候多看复习几次就好  工作不是考试 ^_^  如果面试还是大致看看**/


    /**事务的7中级别*/

    /**
     * Support a current transaction, create a new one if none exists.
     * Analogous to EJB transaction attribute of the same name.
     * <p>This is the default setting of a transaction annotation.
     */
    //1.REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED),

    /**
     * Support a current transaction, execute non-transactionally if none exists.
     * Analogous to EJB transaction attribute of the same name.
     * <p>Note: For transaction managers with transaction synchronization,
     * {@code SUPPORTS} is slightly different from no transaction at all,
     * as it defines a transaction scope that synchronization will apply for.
     * As a consequence, the same resources (JDBC Connection, Hibernate Session, etc)
     * will be shared for the entire specified scope. Note that this depends on
     * the actual synchronization configuration of the transaction manager.
     * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#setTransactionSynchronization
     */
    //2.SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS),

    /**
     * Support a current transaction, throw an exception if none exists.
     * Analogous to EJB transaction attribute of the same name.
     */
    //3.MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY),

    /**
     * Create a new transaction, and suspend the current transaction if one exists.
     * Analogous to the EJB transaction attribute of the same name.
     * <p><b>NOTE:</b> Actual transaction suspension will not work out-of-the-box
     * on all transaction managers. This in particular applies to
     * {@link org.springframework.transaction.jta.JtaTransactionManager},
     * which requires the {@code javax.transaction.TransactionManager} to be
     * made available to it (which is server-specific in standard Java EE).
     * @see org.springframework.transaction.jta.JtaTransactionManager#setTransactionManager
     */
    //4.REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW),

    /**
     * Execute non-transactionally, suspend the current transaction if one exists.
     * Analogous to EJB transaction attribute of the same name.
     * <p><b>NOTE:</b> Actual transaction suspension will not work out-of-the-box
     * on all transaction managers. This in particular applies to
     * {@link org.springframework.transaction.jta.JtaTransactionManager},
     * which requires the {@code javax.transaction.TransactionManager} to be
     * made available to it (which is server-specific in standard Java EE).
     * @see org.springframework.transaction.jta.JtaTransactionManager#setTransactionManager
     */
    //5.NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED),

    /**
     * Execute non-transactionally, throw an exception if a transaction exists.
     * Analogous to EJB transaction attribute of the same name.
     */
    //6.NEVER(TransactionDefinition.PROPAGATION_NEVER),

    /**
     * Execute within a nested transaction if a current transaction exists,
     * behave like {@code REQUIRED} otherwise. There is no analogous feature in EJB.
     * <p>Note: Actual creation of a nested transaction will only work on specific
     * transaction managers. Out of the box, this only applies to the JDBC
     * DataSourceTransactionManager. Some JTA providers might support nested
     * transactions as well.
     * @see org.springframework.jdbc.datasource.DataSourceTransactionManager
     */
    //7.NESTED(TransactionDefinition.PROPAGATION_NESTED);


    /**事务的4隔离级别*/

    /**
     * Use the default isolation level of the underlying datastore.
     * All other levels correspond to the JDBC isolation levels.
     * @see java.sql.Connection
     */
    //DEFAULT(TransactionDefinition.ISOLATION_DEFAULT),  这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别

    /**
     * A constant indicating that dirty reads, non-repeatable reads and phantom reads
     * can occur. This level allows a row changed by one transaction to be read by
     * another transaction before any changes in that row have been committed
     * (a "dirty read"). If any of the changes are rolled back, the second
     * transaction will have retrieved an invalid row.
     * @see java.sql.Connection#TRANSACTION_READ_UNCOMMITTED
     */
    //READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED),  读未提交

    /**
     * A constant indicating that dirty reads are prevented; non-repeatable reads
     * and phantom reads can occur. This level only prohibits a transaction
     * from reading a row with uncommitted changes in it.
     * @see java.sql.Connection#TRANSACTION_READ_COMMITTED
     */
    //READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED),  读已经提交

    /**
     * A constant indicating that dirty reads and non-repeatable reads are
     * prevented; phantom reads can occur. This level prohibits a transaction
     * from reading a row with uncommitted changes in it, and it also prohibits
     * the situation where one transaction reads a row, a second transaction
     * alters the row, and the first transaction rereads the row, getting
     * different values the second time (a "non-repeatable read").
     * @see java.sql.Connection#TRANSACTION_REPEATABLE_READ
     */
    //REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ),  可重复读

    /**
     * A constant indicating that dirty reads, non-repeatable reads and phantom
     * reads are prevented. This level includes the prohibitions in
     * {@code ISOLATION_REPEATABLE_READ} and further prohibits the situation
     * where one transaction reads all rows that satisfy a {@code WHERE}
     * condition, a second transaction inserts a row that satisfies that
     * {@code WHERE} condition, and the first transaction rereads for the
     * same condition, retrieving the additional "phantom" row in the second read.
     * @see java.sql.Connection#TRANSACTION_SERIALIZABLE
     */
    //SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE);  串行化
}
