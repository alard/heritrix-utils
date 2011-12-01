This is a repository for utility classes that you can use with the Heritrix crawler (http://crawler.archive.org/).

To use these classes in your crawls:

1. Compile the .java files (you need the Heritrix JARs to do this);
2. Create a JAR file;
3. Add the JAR file to the `lib/` directory of Heritrix;
4. You can now refer to them in your `crawler-beans.cxml`.

## HashingQueueAssignmentPolicy

Use this queue assignment policy if you want Heritrix to create a number of parallel queues. The built-in SurtAuthorityQueueAssignmentPolicy does something similar. However, since it tries to add similar URLs to the same queue, you can still end up with one very long queue. The HashingQueueAssignmentPolicy distributes the URLs using the formula `queue = curi.hashCode() % numberOfQueues`, creating queues of more or less uniform length.

Usage in `crawler-beans.cxml`:

    <bean id="queueAssignmentPolicy" 
      class="org.archiveteam.crawler.frontier.HashingQueueAssignmentPolicy">
      <property name="parallelQueues" value="5" />
    </bean>

