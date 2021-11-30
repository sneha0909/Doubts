package com.techshard.graphql.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration
public class DynamoDbConfig {

	private static final String REAL = "real";
	@Value("${mode}")
	private String mode;
	@Value("${aws.dynamodb.accessKey}")
	private String awsAccesskey;
	@Value("${aws.dynamodb.secretKey}")
	private String awsSecretKey;
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		
		AmazonDynamoDB client;
		if(mode.equals(REAL)) {
			 client = AmazonDynamoDBClientBuilder.standard()
		              .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                      .withRegion(Regions.US_EAST_1).build();
		} else {
			client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
					 new AwsClientBuilder.EndpointConfiguration("http://localhost:8000/","us-east-1"))
					 .build();
			
		}
		
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
        return dynamoDBMapper;
	}
	
	@Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccesskey, awsSecretKey);
    }
	
	
   //@Bean
	//public AWSCredentialsProvider amazonDynamoDBCredentials()
	//{
		//return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccesskey, awsSecretKey));
	//}
	
	
	
}
