package org.archiveteam.crawler.frontier;

import org.archive.crawler.frontier.QueueAssignmentPolicy;
import org.archive.modules.CrawlURI;
import org.archive.spring.HasKeyedProperties;
import org.archive.spring.KeyedProperties;

/**
 * HashingQueueAssignmentPolicy assigning CrawlURIs to a queue determined
 * by the hash code of the url string.
 * 
 * The CrawlURIs will be uniformly distributed over a configured number
 * of parallel queues.
 */
public class HashingQueueAssignmentPolicy
extends QueueAssignmentPolicy
implements HasKeyedProperties {

    private static final long serialVersionUID = 1L;
    
    KeyedProperties kp = new KeyedProperties();
    public KeyedProperties getKeyedProperties() {
        return kp;
    }
    
    /**
     * The number of parallel queues to split a core key into. By 
     * default is 1. If larger than 1, the URIs will be uniformly
     * distributed over that many separate queues.
     */
    public int getParallelQueues() {
        return (Integer) kp.get("parallelQueues");
    }
    {
        setParallelQueues(1);
    }
    public void setParallelQueues(int count) {
        kp.put("parallelQueues",count);
    }

    @Override
    public String getClassKey(CrawlURI curi) {
        int queueNumber = curi.hashCode() % getParallelQueues();
        return "queue+"+queueNumber;
    }

}
