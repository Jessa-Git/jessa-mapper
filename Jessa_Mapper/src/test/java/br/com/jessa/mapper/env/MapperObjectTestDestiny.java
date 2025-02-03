package br.com.jessa.mapper.env;

public class MapperObjectTestDestiny {

	public enum typePerson{
        HUMAM,GOBLIN
    }

    private String namePerson;

    private Integer agePerson;

    private Double moneyPerson;

    private typePerson type;

    private String code;

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

    public typePerson getType() {
        return type;
    }

    public void setType(typePerson type) {
        this.type = type;
    }
}
