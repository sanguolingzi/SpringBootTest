package com.yinhetianze.jms.activemq;

import java.util.Date;

public class AmqQueueMessage extends AmqMessage
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -8490520984773065231L;

    public AmqQueueMessage()
    {
        initMessage();
    }
    
    public AmqQueueMessage(String destinationName)
    {
        initMessage();
        setDestinationName(destinationName);
    }
    
    private void initMessage()
    {
        setCreateTime(new Date());
        setIsPubMode(false);
    }
    
}
