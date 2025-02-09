package br.com.jessa.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.jessa.mapper.env.A;
import br.com.jessa.mapper.env.B;
import br.com.jessa.mapper.env.C;
import br.com.jessa.mapper.env.E;
import br.com.jessa.mapper.env.MapperObjectTestDestiny;
import br.com.jessa.mapper.env.MapperObjectTestSource;
import br.com.jessa.mapper.env.MapperSubObjectTest;
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
        sourceTest.setTypins(MapperObjectTestDestiny.typePerson.GOBLIN);
        MapperObjectTestSource destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestEnumSourceMapToDestiny(sourceTest);
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getTypins().toString(),destinyTest.getType());
    }
 
 
 @Test
    public void testMapperToEnumObject(){
	 MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setType(MapperObjectTestDestiny.typePerson.GOBLIN.toString());
        MapperObjectTestDestiny destinyTest;
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestToEnumSourceMapToDestiny(sourceTest);
        
        assertNotNull(destinyTest);
        assertEquals(sourceTest.getType(),destinyTest.getTypins().toString());
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
    
    
    @Test
    public void testMapperObjectSubClass(){
    	String subName="Teste sub";
    	MapperSubObjectTest sub=new MapperSubObjectTest();
    	sub.setSubName(subName);
    	MapperObjectTestSource sourceTest = new MapperObjectTestSource();
        sourceTest.setT1(sub);
        MapperObjectTestDestiny destinyTest;
        
        destinyTest= JessaMapper.implement(MapperTestInterface.class).toTestSubClass(sourceTest);
        assertNotNull(destinyTest.getSub());
        assertEquals(sourceTest.getT1().getSubName(),destinyTest.getSub().getSubName());
    }
    
    
    
    @Test
    public void testMapperObjectSubClass3(){
    	String subName="Teste sub";
    
    	A a = new A();
        B b = new B();
        C c = new C();
        
        a.setName(subName);
        b.setA(a);
        c.setCname(subName);
        System.out.println("ORigem>>>>>>"+b);
        b= JessaMapper.implement(MapperTestInterface.class).toTestSubClass3(c);
        System.out.println("Destiny>>"+b);
        assertEquals(b.getA().getName(),c.getCname());
        assertEquals(a.getName(), subName);
        assertEquals(b.getA().getName(), subName);
    }
    
    @Test
    public void testMapperObjectSubClass4(){
    	String subName="Teste sub";
    
    	A a = new A();
        B b = new B();
        C c = new C();
        E e = new E();
        a.setName(subName);
        b.setA(a);
        c.setCname(subName);
        System.out.println("ORigem>>>>>>"+c);
        e= JessaMapper.implement(MapperTestInterface.class).toTestSubClass4(c);
        
        System.out.println("Destiny>>"+e);
        
        assertEquals(e.getB().getA().getName(),c.getCname());
        assertEquals(a.getName(), subName);
        assertEquals(e.getB().getA().getName(), subName);
    }
    @Test
    public void testMapperObjectSubClass5(){
    	String subName="Teste sub";
    
    	A a = new A();
        B b = new B();
        C c = new C();
        E e = new E();
        a.setName(subName);
        b.setA(a);
        c.setCname(subName);
        e.setB(b);
        
        System.out.println("ORigem>>>>>>"+e);
        c= JessaMapper.implement(MapperTestInterface.class).toTestSubClass5(e);
        
        System.out.println("Destiny>>"+c);
        
        assertEquals(e.getB().getA().getName(),c.getCname());
        assertEquals(a.getName(), subName);
        assertEquals(subName,e.getB().getA().getName());
    }
}
