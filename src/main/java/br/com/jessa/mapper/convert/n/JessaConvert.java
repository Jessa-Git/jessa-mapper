package br.com.jessa.mapper.convert.n;

import br.com.jessa.mapper.MapperSubProcess;
import br.com.jessa.mapper.convert.DtoConvert;
import br.com.jessa.mapper.exception.JessaMapperException;

public class JessaConvert {

	private DtoConvert dto;
	private Boolean isPrimitive = false;

	private Object val;

	public JessaConvert(DtoConvert dto) {
		this.dto = dto;
	}

	public JessaConvert byPrimitiveValue() {
		try {
			val = toInt(val);
			val = toString(val);
			val = toDouble(val);
		} catch (Exception e) {
			throw new JessaMapperException("Falha ao converter [" + dto.getSourceObject() + "] para "
					+ dto.getReturnValueClassDestiny().getSimpleName());
		}
		return this;
	}

	public JessaConvert byClassObject() {
		if (Boolean.TRUE.equals(!isPrimitive) && !dto.getReturnValueClassDestiny().isEnum())
			val = MapperSubProcess.processObject(dto.getSourceObject(), dto.getReturnValueClassDestiny(),
					dto.getMapperMethod());
		return this;
	}

	public JessaConvert byEnumObject() {
		if (!dto.getReturnValueClassDestiny().isEnum())
			return this;
		val = new ConvertEnum().process(dto).getVal();
		return this;

	}

	public JessaConvert byEnumSource() {

		if (dto.getEnumMethodName() != null)
			val = new ConvertEnum().processSource(dto).getVal();

		return this;

	}

	public Object val() {
		return val;
	}

	private String getClassReturnNewValue() {
		try {
			return dto.getReturnValueClassDestiny().getSimpleName();
		} catch (Exception e) {
			throw new JessaMapperException("Falha ao obter class de retorno de Como Obter Conversao (HowToConvert)");
		}
	}
	
	private boolean newValueClassEquals(Object ob) {
		return getClassReturnNewValue().equals(ob);
	}

	private Object toInt(Object val) {
		if (newValueClassEquals("int") || newValueClassEquals(Integer.class.getSimpleName())) {
			isPrimitive = true;
			return Integer.valueOf("" + dto.getSourceObject());
		}
		return val;
	}

	private Object toDouble(Object val) {
		if (newValueClassEquals("double") || newValueClassEquals(Double.class.getSimpleName())) {
			isPrimitive = true;
			return Double.valueOf("" + dto.getSourceObject());
		}
		return val;
	}

	
	private Object toString(Object val) {
		if (newValueClassEquals("String")) {
			isPrimitive = true;
			return "" + dto.getSourceObject();
		}
		return val;
	}

}
