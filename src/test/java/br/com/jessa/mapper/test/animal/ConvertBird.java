package br.com.jessa.mapper.test.animal;

import br.com.jessa.mapper.JessaMapper;
import br.com.jessa.mapper.ToInterfaceMapper;
import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;

public interface ConvertBird extends ToInterfaceMapper {
	
	ConvertBird INSTANCE = JessaMapper.implement(ConvertBird.class);
	
	@Mapper(mapping = {@CaseMap(destiny = "birdAge",source = "age")})
    public WebBird convertBirdToWebBird(Bird obj);

}
