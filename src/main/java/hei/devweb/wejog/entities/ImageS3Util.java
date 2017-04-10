package hei.devweb.wejog.entities;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
 
public class ImageS3Util {
    private static final String ACCESS_KEY = "XXX";
    private static final String SECRET_KEY = "xxx";
    private static final String END_POINT_URL = "http://s3.amazonaws.com";// e.g http://s3.amazonaws.com
    private static final String BUCKET = "wejog";
    private static final String S3_CACHE = "60"; // e.g 60
    private static AmazonS3 s3;
    public static void uploadImageToAWSS3(Part multipartFile, String fileName) throws IllegalStateException,
               IOException {
        try {
        	//System.out.println("FileName : "+fileName);
            AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            java.security.Security.setProperty("networkaddress.cache.ttl", S3_CACHE);
            s3 = new AmazonS3Client(credentials);	s3.setEndpoint(END_POINT_URL);
            InputStream stream = multipartFile.getInputStream();
            //System.out.println("stream:"+stream);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            //System.out.println("object:"+objectMetadata);
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET,fileName, stream,objectMetadata);
            //skip if do not want to access the image directly from S3
            //System.out.println("put:"+putObjectRequest);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); 
            //skip if do not want to access the image directly from S3
            s3.putObject(putObjectRequest);
        	} catch (AmazonServiceException e) {
                e.printStackTrace();
        }
    }
}