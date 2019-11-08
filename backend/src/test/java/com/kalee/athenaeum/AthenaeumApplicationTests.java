package com.kalee.athenaeum;

import java.io.IOException;
import java.util.List;

import com.kalee.athenaeum.data.Document;
import com.kalee.athenaeum.data.S3Service;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootTest
class AthenaeumApplicationTests {

	@Test
	void contextLoads() {
	}


	public  void aws() throws IOException {
		// you need to have configured our $USER_HOME/.aws/credentials file containing acees key id and secret
		byte[] content = IOUtils.toByteArray(S3Service.class.getClassLoader().getResourceAsStream("banner.txt"));

		S3Service service = new S3Service(S3Client.builder() .region(Region.EU_WEST_1)
				.build());

		String objectName = "test_vio";
		String result = service.upload(new Document(objectName, content));

		System.out.println("Done: " + result);

		Document document = service.retrieve("broken flask.jpg");
		System.out.println(new String(document.getData()));

		List<Document> docs = service.list();
		for (Document doc: docs){
			System.out.println(doc.getName()+" "+doc.getOwner()  + " "+doc.getSize() + " "+ doc.getModifiedAt());
		}
	}
}
