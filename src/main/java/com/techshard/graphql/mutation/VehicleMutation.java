package com.techshard.graphql.mutation;

import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.dao.repository.VehicleRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class VehicleMutation implements GraphQLMutationResolver {

	 @Autowired
	 private VehicleRepository vehicleRepository;

	 public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate)
	 {
	    return this.vehicleRepository.createVehicle(type, modelCode, brandName, launchDate);
	 }
	 
	 public Vehicle updateVehicle(final String id, final String type, final String modelCode, final String brandName, final String launchDate)
	 {
	    return this.vehicleRepository.updateVehicle(id, type, modelCode, brandName, launchDate);
	 }
	 
	 public Vehicle updateVehicleType(String id, String type) {
		 return this.vehicleRepository.updateVehicleType(id, type);
	 }
	

	 public Vehicle deleteVehicle (String id)
	 {
		return vehicleRepository.deleteVehicle(id);
	 }
    
	
}
