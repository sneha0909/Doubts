package com.techshard.graphql.dao.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.techshard.graphql.dao.entity.Vehicle;



@Repository
public class VehicleRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	
	public Vehicle createVehicle(final String type,final String modelCode, final String brandName, final String launchDate) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate((launchDate));
        dynamoDBMapper.save(vehicle);
        return vehicle;
    }
	
	
	public Vehicle findVehicleById(String id)
	{
		return dynamoDBMapper.load(Vehicle.class,id);
	}
	
	public List<Vehicle> findAllVehicles(){
		
		return dynamoDBMapper.scan(Vehicle.class, new DynamoDBScanExpression());
	}
	
	public Vehicle updateVehicle(final String id, final String type,final String modelCode, final String brandName, final String launchDate) 
	{
		
		Vehicle vehicle = dynamoDBMapper.load(Vehicle.class, id);
		vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate((launchDate));
       
        dynamoDBMapper.save(vehicle);			
     			
        return vehicle;

	}
	 
	public Vehicle updateVehicleType(String id, String type) {
		Vehicle update = new Vehicle();
		update.setId(id);
		update.setType(type);
		//dynamoDBMapper.save(update);
		dynamoDBMapper.save(update, DynamoDBMapperConfig.builder()
    			.withSaveBehavior(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
    			.build());
		return update;
	}
	
	//public String update(String id, Vehicle vehicle)
	//{
		//dynamoDBMapper.save(vehicle,
				//new DynamoDBSaveExpression()
	   //  .withExpectedEntry("id",
	           // new ExpectedAttributeValue(
	            		
	            	//new AttributeValue().withS(id)
				
			
				//)));
		
		//return id;
	//}
	
	
	public Vehicle deleteVehicle(String id)
	{
		Vehicle vehicle = dynamoDBMapper.load(Vehicle.class,id);
		dynamoDBMapper.delete(vehicle);
		return vehicle;
	}
	
}

