package br.com.jessa.mapper.env;

public class MapperObjectTestSource {
	 private String name;

	    private String age;

	    private String moneyPerson;

	    private String type;

	    private String code;

	    private MapperSubObjectTest t1;
	    private MapperSubObjectTest t2;
	    private MapperSubObjectTest2 t3;

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getAge() {
	        return age;
	    }

	    public void setAge(String age) {
	        this.age = age;
	    }

	    public String getMoneyPerson() {
	        return moneyPerson;
	    }

	    public void setMoneyPerson(String moneyPerson) {
	        this.moneyPerson = moneyPerson;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

		public MapperSubObjectTest getT1() {
			return t1;
		}

		public void setT1(MapperSubObjectTest t1) {
			this.t1 = t1;
		}

		public MapperSubObjectTest getT2() {
			return t2;
		}

		public void setT2(MapperSubObjectTest t2) {
			
			this.t2 = t2;
		}

		public MapperSubObjectTest2 getT3() {
			return t3;
		}

		public void setT3(MapperSubObjectTest2 t3) {
			this.t3 = t3;
		}

		@Override
		public String toString() {
			return "MapperObjectTestSource [name=" + name + ", age=" + age + ", moneyPerson=" + moneyPerson + ", type="
					+ type + ", code=" + code + ", t1=" + t1 + ", t2=" + t2 + ", t3=" + t3 + "]";
		}
		
		
}
