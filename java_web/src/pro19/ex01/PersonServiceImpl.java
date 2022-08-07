package pro19.ex01;

public class PersonServiceImpl implements PersonService {
	private String name;
	private int age;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sayHello() {
		System.out.println("ÀÌ¸§: " + name);
		System.out.println("³ªÀÌ: " + age);
	}
}