package com.demo.lambda.Lambda_Learning.s3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3Entity;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;

import software.amazon.awssdk.services.s3.S3Client;

public class LambdaS3Handler implements RequestHandler<S3Event, String> {

	@Override
	public String handleRequest(S3Event s3Event, Context context) {
		if (null != s3Event && null != s3Event.getRecords()) {
			S3EventNotificationRecord s3EvntNotRec = s3Event.getRecords().getFirst();
			System.out.println("EVent name :: " + s3EvntNotRec.getEventName());
			System.out.println("EVent source :: " + s3EvntNotRec.getEventSource());
			S3Entity s3Entity = s3EvntNotRec.getS3();
			System.out.println("S3 bukcet name :: " + s3Entity.getBucket().getName());
			System.out.println("S3 Object key :: " + s3Entity.getObject().getKey());
		}
		return "S3 event access success";
	}

	public void pushToS3FromLambda(Map<String, String> input) throws IOException {

		System.out.println("pushToS3FromLambda begin >>>>>>> input " + input);
		input.get("bucketName");
		S3Client s3Client = S3Client.builder().build();
		String filePath = input.get("filePath");
		System.out.println("Filepath >>> " + filePath);
		Path path = Paths.get(filePath);
		System.out.println("path details >>>>>>>>> "+ path.toString());
		/*
		 * File file = new File(filePath);
		 * System.out.println("file.getClass().getName() "+ file.getClass().getName());
		 * System.out.println("pushToS3FromLambda middle >>>>>>> is it a file??? " +
		 * file.isFile());
		 */
		s3Client.putObject(putObjReq -> putObjReq.bucket(input.get("bucketName")).key(path.getFileName().toString()), path);
		System.out.println("pushToS3FromLambda end >>>>>>> ");
	}

}
