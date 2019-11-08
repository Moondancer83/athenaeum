package com.kalee.athenaeum.data;


import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectAclRequest;
import software.amazon.awssdk.services.s3.model.GetObjectAclResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class S3Service {
    Logger logger = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private S3Client s3Client;

    public S3Service(S3Client client) {
        this.s3Client = client;
    }

    private static final String bucket = "innoday15documentstorage"; // hardocded for now


    public String upload(Document document) {
        String objectName = document.getName();
        try {
            PutObjectResponse putObjectResult = s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(objectName)
                            .contentLength((long) document.getData().length)
                            .build(),
                    RequestBody.fromByteBuffer(ByteBuffer.wrap(document.getData())));
            final URL reportUrl = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucket).key(objectName).build());
            return reportUrl.toString();
        } catch (SdkServiceException ase) {
            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason: " + objectName, ase);
            throw ase;
        } catch (SdkClientException ace) {
            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network: " + objectName, ace);
            throw ace;
        }
    }

    public Document retrieve(String objectName) {
        try {
            System.out.println("Retrieving file from S3 for key: " + bucket + "/" + objectName);
            ResponseBytes<GetObjectResponse> s3Object = s3Client.getObject(
                    GetObjectRequest.builder().bucket(bucket).key(objectName).build(),
                    ResponseTransformer.toBytes());

            GetObjectAclResponse aclInfo = s3Client.getObjectAcl(GetObjectAclRequest.builder().bucket(bucket).key(objectName).build());

            Document doc = new Document(objectName, s3Object.asByteArray());
            doc.setModifiedAt(Date.from(s3Object.response().lastModified()));
            doc.setSize(s3Object.response().contentLength());
            doc.setOwner(aclInfo.owner().displayName());
            URL reportUrl = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucket).key(objectName).build());
            doc.setUrl(reportUrl.toString());
            return doc;

        } catch (SdkClientException ase) {
            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason: " + objectName, ase);
            throw ase;
        } catch (SdkServiceException ace) {
            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network: " + objectName, ace);
            throw ace;
        }
    }


    public List<Document> list() {
        try {
            ListObjectsResponse response = s3Client.listObjects(ListObjectsRequest.builder().bucket(bucket).build());
            return response.contents().stream().map(obj -> toEmptyDocument(obj)).collect(Collectors.toList());
        } catch (SdkClientException ase) {
            logger.error("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason: " , ase);
            throw ase;
        } catch (SdkServiceException ace) {
            logger.error("Caught an AmazonClientException, which " + "means the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network", ace);
            throw ace;
        }
    }


    private Document toEmptyDocument(S3Object object) {
        Document doc = new Document(object.key(), new byte[0]); //do not get the content yet
        doc.setSize(object.size());
        doc.setOwner(object.owner().displayName());
        doc.setModifiedAt(Date.from(object.lastModified()));
        return doc;
    }
}