package br.com.jessa.mapper.test.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CarTest {

	@Test
    public void testMapperStringObject(){

		Car car = new Car();
		car.setMake("Morris");
		car.setNumberOfSeats(5);
		car.setType(CarType.SEDAN);
		
		CarDto carDto= CarMapper.INSTANCE.toTestStringSourceMapToDestiny(car);
        
		assertNotNull(carDto);
        assertEquals("Morris",carDto.getMake() );
        assertEquals( 5,carDto.getSeatCount());
        assertEquals("SEDAN",carDto.getType());
    }
	
	@Test
    public void testMapperStringObject2(){

		Car car = new Car();
		car.setMake("Morris");
		car.setNumberOfSeats(5);
		car.setType(CarType.SEDAN);
		
		CarDto carDto = new CarDto();
		carDto.setMake("Morris");
		carDto.setSeatCount(5);
		carDto.setType("c2");
		
		car= CarMapper.INSTANCE.toTestStringSourceMapToDestiny2(carDto);
        
		assertNotNull(car);
        assertEquals("Morris",car.getMake());
        assertEquals(5,car.getNumberOfSeats());
        assertEquals(CarType.SEDAN,car.getType());
        
    }
	
	@Test
    public void testMapperStringObject3(){

		Car car = new Car();
		car.setMake("Morris");
		car.setNumberOfSeats(5);
		car.setType(CarType.SEDAN);
		
		CarDto carDto = new CarDto();
		carDto.setMake("Morris");
		carDto.setSeatCount(5);
		carDto.setType(CarType.SEDAN.getC());
		
		car= CarMapper.INSTANCE.toTestStringSourceMapToDestiny2(carDto);
        
		assertNotNull(car);
        assertEquals("Morris",car.getMake());
        assertEquals(5,car.getNumberOfSeats());
        assertEquals(CarType.SEDAN,car.getType());
        
        
        carDto= CarMapper.INSTANCE.toTestStringSourceMapToDestinyEnum(car);
        
        assertNotNull(carDto);
        assertEquals("Morris",carDto.getMake());
        assertEquals(5,carDto.getSeatCount());
        assertEquals("c2",carDto.getType());
    }
	
}
