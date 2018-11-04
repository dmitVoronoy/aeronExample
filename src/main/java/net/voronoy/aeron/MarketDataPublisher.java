package net.voronoy.aeron;

import io.aeron.Aeron;
import io.aeron.Publication;
import io.aeron.driver.MediaDriver;
import org.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteBuffer;

public class MarketDataPublisher {

    public static void main(String[] args) {
        MediaDriver driver = MediaDriver.launch();
        Aeron aeron = Aeron.connect();
        Publication publication = aeron.addPublication("aeron:udp?endpoint=localhost:50505", 1);
        UnsafeBuffer buffer = new UnsafeBuffer(ByteBuffer.allocateDirect(256));
        buffer.putBytes(0, "ping".getBytes());
        long result = publication.offer(buffer);
        PublicationResult publicationResult = PublicationResult.create((int) result);
        System.out.println("RESULT: " + publicationResult);
        driver.close();
    }

}
