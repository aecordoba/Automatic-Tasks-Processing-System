package ar.com.dynamicmcs.app.atps;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.dynamicmcs.app.atps.web.controllers.EngineStateMonitorController;
import ar.com.dynamicmcs.app.atps.web.controllers.LoginController;
import ar.com.dynamicmcs.app.atps.web.controllers.SystemControlController;
import ar.com.dynamicmcs.app.atps.web.controllers.UserRegisterController;

@SpringBootTest
class AutomaticTasksProcessingSystemApplicationTests {
	@Autowired
	private LoginController loginController;
	@Autowired
	private UserRegisterController userRegisterController;
	@Autowired
	private SystemControlController systemControlController;
	@Autowired
	private EngineStateMonitorController engineStateMonitorController;

	@Test
	@Tag("Context")

	void contextLoads() {
		assertThat(loginController).isNotNull();
		assertThat(userRegisterController).isNotNull();
		assertThat(systemControlController).isNotNull();
		assertThat(engineStateMonitorController).isNotNull();
	}

}
