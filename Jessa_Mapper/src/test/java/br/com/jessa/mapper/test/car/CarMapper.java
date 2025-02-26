package br.com.jessa.mapper.test.car;

import br.com.jessa.mapper.JessaMapper;
import br.com.jessa.mapper.ToInterfaceMapper;
import br.com.jessa.mapper.annotations.CaseEnumMap;
import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;

public interface CarMapper extends ToInterfaceMapper {
	
	public CarMapper INSTANCE= JessaMapper.implement(CarMapper.class);
	
	@Mapper(mapping = {@CaseMap(source = "numberOfSeats",destiny = "seatCount")})
    public CarDto toTestStringSourceMapToDestiny(Car obj);

	@Mapper(mapping = {@CaseMap(destiny= "numberOfSeats",source  = "seatCount")})
    public Car toTestStringSourceMapToDestiny2(CarDto  obj);
	
	@Mapper(
			mapping = {@CaseMap(source = "numberOfSeats",destiny = "seatCount")}
			,mapEnum = {@CaseEnumMap(fieldNameClass = "type",parameterNameEnum = "c")}
			)
    public CarDto toTestStringSourceMapToDestinyEnum(Car obj);
}
