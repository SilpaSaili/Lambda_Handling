package com.demo.lambda.Lambda_Learning.sns;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

public class LambdaSNSHandler implements RequestHandler<SNSEvent, String> {

	@Override
	public String handleRequest(SNSEvent snsEvent, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("Message from SNS is " + snsEvent.getRecords().get(0).getSNS().getMessage());
		return "SNS event handled";
	}

	public void publishSNSMsgFromLambda() {
		System.out.println("publishSNSMsgFromLambda :::::");
		SnsClient snsClient = SnsClient.builder().region(Region.US_EAST_1).build();
		System.out.println("publishSNSMsgFromLambda ::::: after snsClient creation");
		snsClient.publish(pubReq -> pubReq.message("Message from lambda function")
				.topicArn("arn:aws:sns:us-east-1:288761728457:snsTopic1").build());
		System.out.println("publishSNSMsgFromLambda ::::: after message creation");
	}
}
