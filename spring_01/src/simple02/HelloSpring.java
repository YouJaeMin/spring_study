package simple02;

/*
 * 인터페이스를 이용해서 결합도를 낮춤(의존관계를 약하게 설정함)
 * 
 */
public class HelloSpring {
	public static void main(String[] args){
		MessageBean bean = new MessageBeanKorea();
		bean.sayHello("홍길동");
		
		bean = new MessageBeanEnglish();
		bean.sayHello("kim");
	}
}
