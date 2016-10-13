package simple03;

public class MessageFactory {
	
	public static MessageBean getInstace(String type){
		if(type.equals("ko")){
			return new MessageBeanKorea();
		}else if(type.equals("en")){
			return new MessageBeanEnglish();
		}else{
			return null;
		}
	}
	
}
