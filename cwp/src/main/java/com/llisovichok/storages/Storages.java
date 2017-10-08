package com.llisovichok.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KUDIN ALEKSANDR on 23.09.2017.
 */

@Service
public class Storages {
    public final MemoryStorage memoryStorage;

    public final JdbcStorage jdbcStorage;

    //public final HibernateStorage hibernateStorage;

    public final SHHibernateStorage shHibernateStorage;


    @Autowired
    public Storages(final MemoryStorage memoryStorage, final JdbcStorage jdbcStorage, final SHHibernateStorage shHibernateStorage){
        this.memoryStorage = memoryStorage;
        this.jdbcStorage = jdbcStorage;
        //this.hibernateStorage = hibernateStorage;
        this.shHibernateStorage = shHibernateStorage;
    }
}
