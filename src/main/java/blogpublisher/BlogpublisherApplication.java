package blogpublisher;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogpublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogpublisherApplication.class, args);

	}

}
