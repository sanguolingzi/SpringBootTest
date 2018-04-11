package com.yinhetianze.jms.activemq;

import java.util.Date;

public class AmqTopicMessage extends AmqMessage
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 2730119033967568851L;

    public AmqTopicMessage()
    {
        initMessage();
    }
    
    public AmqTopicMessage(String destinationName)
    {
        initMessage();
        setDestinationName(destinationName);
    }
    
    private void initMessage()
    {
        setCreateTime(new Date());
        setIsPubMode(true);
    }
    
}
