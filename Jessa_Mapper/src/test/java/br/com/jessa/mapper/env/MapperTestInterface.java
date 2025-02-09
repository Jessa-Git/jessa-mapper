package br.com.jessa.mapper.env;

import br.com.jessa.mapper.ToInterfaceMapper;
import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;

public interface MapperTestInterface extends ToInterfaceMapper{
	@Mapper(mapping = {@CaseMap(source = "name",destiny = "namePerson")})
    public MapperObjectTestDestiny toTestStringSourceMapToDestiny(MapperObjectTestSource obj);


    @Mapper(mapping = {@CaseMap(source = "age",destiny = "agePerson")})
    public MapperObjectTestDestiny toTestIntegerSourceMapToDestiny(MapperObjectTestSource obj);

    @Mapper(mapping = {@CaseMap(source = "moneyPerson",destiny = "moneyPerson")})
    public MapperObjectTestDestiny toTestDoubleSourceMapToDestiny(MapperObjectTestSource obj);
    
    @Mapper(mapping = {@CaseMap(source = "type",destiny = "typins")})
    public MapperObjectTestDestiny toTestToEnumSourceMapToDestiny(MapperObjectTestSource obj);
    @Mapper(mapping = {@CaseMap(source = "typins",destiny = "type")})
    public MapperObjectTestSource toTestEnumSourceMapToDestiny(MapperObjectTestDestiny obj);
    


    public MapperObjectTestDestiny toTestSourceMapToDestinyNoAnnotations(MapperObjectTestSource obj);
    
    @Mapper(mapping = {@CaseMap(source = "moneyPerson",destiny = "moneyPerson")})
    public void toTestVoid(MapperObjectTestSource obj, MapperObjectTestDestiny ob);
    
    @Mapper(mapping = {@CaseMap(source = "t1",destiny = "sub")})
    public MapperObjectTestDestiny toTestSubClass(MapperObjectTestSource obj);
    
    
    @Mapper(mapping = {@CaseMap(source = "cname",destiny = "a.name")})
    public B toTestSubClass3(C obj);
    
    @Mapper(mapping = {@CaseMap(source = "cname",destiny = "b.a.name")})
    public E toTestSubClass4(C obj);
    
    @Mapper(mapping = {@CaseMap(source  = "b.a.name",destiny= "cname")})
    public C toTestSubClass5(E obj);
}
