package part03_exam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//@Aspect
public class LogginAspect {

	private Log log = LogFactory.getLog(getClass());

	// @Around("execution(* part03_exam.ServiceImp.prn(..))")
	public void loggin(ProceedingJoinPoint joinPoint) {
		StopWatch sw = new StopWatch();
		log.info("기록시작");
		sw.start();

		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		sw.stop();
		log.info("기록종료");
		log.info(joinPoint.getSignature().getName() + "_메서드 실행시간" + sw.getTotalTimmillis());

	}

}
