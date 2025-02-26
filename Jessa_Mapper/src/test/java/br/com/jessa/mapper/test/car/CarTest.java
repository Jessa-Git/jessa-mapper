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
		
		//CarDto carDto= JessaMapper.implement(CarMapper.class).toTestStringSourceMapToDestiny(car);
		CarDto carDto= CarMapper.INSTANCE.toTestStringSourceMapToDestiny(car);
        
		assertNotNull(carDto);
        assertEquals(carDto.getMake(), "Morris");
        assertEquals(carDto.getSeatCount(), 5);
        assertEquals(carDto.getType(), "SEDAN");
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
		carDto.setType(CarType.SEDAN.getC());
		
		//CarDto carDto= JessaMapper.implement(CarMapper.class).toTestStringSourceMapToDestiny(car);
		car= CarMapper.INSTANCE.toTestStringSourceMapToDestiny2(carDto);
        
		assertNotNull(car);
        assertEquals(car.getMake(), "Morris");
        assertEquals(car.getNumberOfSeats(), 5);
        assertEquals(car.getType(), CarType.SEDAN);
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
		
		//CarDto carDto= JessaMapper.implement(CarMapper.class).toTestStringSourceMapToDestiny(car);
		car= CarMapper.INSTANCE.toTestStringSourceMapToDestiny2(carDto);
        
		assertNotNull(car);
        assertEquals(car.getMake(), "Morris");
        assertEquals(car.getNumberOfSeats(), 5);
        assertEquals(car.getType(), CarType.SEDAN);
        
        
        carDto= CarMapper.INSTANCE.toTestStringSourceMapToDestinyEnum(car);
        
        assertNotNull(carDto);
        assertEquals(carDto.getMake(), "Morris");
        assertEquals(carDto.getSeatCount(), 5);
        assertEquals(carDto.getType(), "c");
    }
	
}
