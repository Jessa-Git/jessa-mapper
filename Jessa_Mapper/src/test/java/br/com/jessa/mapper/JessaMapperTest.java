package br.com.jessa.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.jessa.mapper.env.MapperObjectTestDestiny;
import br.com.jessa.mapper.env.MapperObjectTestSource;
import br.com.jessa.mapper.env.MapperTestInterface;
import br.com.jessa.mapper.exception.JessaMapperException;

public class JessaMapperTest {

	
	@Test
    public void testMapperStringObject(){

        MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setName("Test Name");
        MapperObjectTestDestiny destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestStringSourceMapToDestiny(sourceTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getName(),destinyTest.getNamePerson());
    }
	
	@Test
    public void testMapperIntegerObject(){
        MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setAge("10");
        MapperObjectTestDestiny destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestIntegerSourceMapToDestiny(sourceTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getAge(),destinyTest.getAgePerson().toString());
    }


    @Test
    public void testMapperDoubleObject(){
        MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setMoneyPerson("10.16");
        MapperObjectTestDestiny destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestDoubleSourceMapToDestiny(sourceTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getMoneyPerson(),destinyTest.getMoneyPerson().toString());
    }


    @Test(expected = JessaMapperException.class)
    public void testMapperIntegerObjectFail(){
        MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setAge("a");
        JessaMapper.implement(MapperTestInterface.class).toTestIntegerSourceMapToDestiny(sourceTest);
        
    }
    @Test
    public void testMapperFromEnumObject(){
        MapperObjectTestDestiny sourceTest = new MapperObjectTestDestiny();
        sourceTest.setType(MapperObjectTestDestiny.typePerson.GOBLIN);
        MapperObjectTestSource destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestEnumSourceMapToDestiny(sourceTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getType().toString(),destinyTest.getType());
    }
 
 
 @Test
    public void testMapperToEnumObject(){
	 MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setType(MapperObjectTestDestiny.typePerson.GOBLIN.toString());
        MapperObjectTestDestiny destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestToEnumSourceMapToDestiny(sourceTest);
        
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getType(),destinyTest.getType().toString());
    }

    @Test
    public void testMapperVoid(){
        MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setMoneyPerson("10.16");
        MapperObjectTestDestiny destinyTest = new MapperObjectTestDestiny();
        JessaMapper.implement(MapperTestInterface.class).toTestVoid(sourceTest,destinyTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getMoneyPerson(),destinyTest.getMoneyPerson().toString());
    }


    @Test
    public void testMapperObjectNoAnnotation(){
        MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setCode("10.16");
        MapperObjectTestDestiny destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestSourceMapToDestinyNoAnnotations(sourceTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getCode(),destinyTest.getCode());
    }
}
