import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingTest.class);
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		logger.debug("default Log Level Debug");
		logger.info("default Log Level Info");
		logger.warn("default Log Level Warn");
		logger.error("default Log Level Error");
		// 각기 Log Level에 따른 출력
		
	}
	
}
