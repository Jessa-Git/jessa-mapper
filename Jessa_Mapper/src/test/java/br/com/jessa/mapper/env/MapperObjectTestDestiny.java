package br.com.jessa.mapper.env;

public class MapperObjectTestDestiny {

	public enum typePerson{
        HUMAM,GOBLIN
    }

    private String namePerson;

    private Integer agePerson;

    private Double moneyPerson;

    private typePerson typins;

    private String code;
    
    private MapperSubObjectTest sub;
    
    

    public typePerson getTypins() {
		return typins;
	}

	public void setTypins(typePerson typins) {
		this.typins = typins;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public Integer getAgePerson() {
        return agePerson;
    }

    public void setAgePerson(Integer agePerson) {
        this.agePerson = agePerson;
    }

    public Double getMoneyPerson() {
        return moneyPerson;
    }

    public void setMoneyPerson(Double moneyPerson) {
        this.moneyPerson = moneyPerson;
    }

 
	public MapperSubObjectTest getSub() {
		return sub;
	}

	public void setSub(MapperSubObjectTest sub) {
		this.sub = sub;
	}

	@Override
	public String toString() {
		return "MapperObjectTestDestiny [namePerson=" + namePerson + ", agePerson=" + agePerson + ", moneyPerson="
				+ moneyPerson + ", type=" + typins + ", code=" + code + ", sub=" + sub + "]";
	}
	
	
}
