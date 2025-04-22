package br.com.jessa.mapper.test.thread;

import br.com.jessa.mapper.JessaMapper;
import br.com.jessa.mapper.ToInterfaceMapper;
import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;

public interface MultiThreadMapInterface extends ToInterfaceMapper {

	public MultiThreadMapInterface INSTANCE = JessaMapper.implement(MultiThreadMapInterface.class);

	@Mapper(mapping = { @CaseMap(source = "age", destiny = "ageString") })
	public PersonDTO toTestStringSourceMapToDestiny(PersonObj obj);
}
