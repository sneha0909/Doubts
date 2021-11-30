package com.techshard.graphql.query;

import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.dao.repository.VehicleRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;





@Component
public class VehicleQuery implements GraphQLQueryResolver {

	@Autowired
    private VehicleRepository vehicleRepository;
	
	public Vehicle findVehicleById(final String id )
	{
		return vehicleRepository.findVehicleById(id);
	}
	
	
	public List<Vehicle> findAllVehicles(){
		return vehicleRepository.findAllVehicles();
	}
    
 
	
}
