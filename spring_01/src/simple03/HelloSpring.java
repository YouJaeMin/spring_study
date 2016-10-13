package simple03;
/*
 * factory을 이용하면 결합도는 인터페이스보다는 나지만
 * factory가 정확히 구현되어 있지 않으면 참조하는 객체에 영향이 간다.
 */
public class HelloSpring {
	public static void main(String[] args){
		MessageBean bean = MessageFactory.getInstace("ko");
		bean.sayHello("한글");
	
		bean = MessageFactory.getInstace("en");
		bean.sayHello("ena");
		
	}
}
