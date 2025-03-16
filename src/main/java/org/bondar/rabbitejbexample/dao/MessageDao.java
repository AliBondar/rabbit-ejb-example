package org.bondar.rabbitejbexample.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bondar.rabbitejbexample.entity.Message;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class MessageDao extends BaseDao<Message> {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());


}
