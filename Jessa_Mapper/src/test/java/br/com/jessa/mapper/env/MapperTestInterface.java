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


    public MapperObjectTestDestiny toTestSourceMapToDestinyNoAnnotations(MapperObjectTestSource obj);
    
    @Mapper(mapping = {@CaseMap(source = "moneyPerson",destiny = "moneyPerson")})
    public void toTestVoid(MapperObjectTestSource obj, MapperObjectTestDestiny ob);
}
