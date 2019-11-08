package com.kalee.athenaeum;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kalee.athenaeum.data.Document;
import com.kalee.athenaeum.data.S3Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootTest
class AthenaeumApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public  void aws() throws IOException {
		// you need to have configured our $USER_HOME/.aws/credentials file containing acees key id and secret
		byte[] content = S3Service.class.getClassLoader().getResourceAsStream("banner.txt").readAllBytes();

		S3Service service = new S3Service(S3Client.builder() .region(Region.EU_WEST_1)
				.build());

		String objectName = "test_vio";
		String result = service.upload(new Document(objectName, content, "me"));

		System.out.println("Done: " + result);

		Document document = service.retrieve("broken flask.jpg");
		System.out.println(new String(document.getData()));
	}
}
