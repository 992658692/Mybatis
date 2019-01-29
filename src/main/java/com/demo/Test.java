package org.pay.uen.ncpay;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Test {
	
	public static void generator () throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configfile = new File("E:\\workspace\\work_ups\\ups-pay-parent\\pay-uen-ncpay\\src\\main\\resource\\test.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configfile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator mybatis = new MyBatisGenerator(config, callback, warnings);
		mybatis.generate(null);
	}
			<dependency>
		    <groupId>org.mybatis.generator</groupId>
		    <artifactId>mybatis-generator-core</artifactId>
		    <version>1.3.2</version>
		</dependency>
	public static void main(String[] args) throws Exception {
//		generator();
		String str = "0203-013299";
		System.out.println(str.substring(0,4));
		
				Demo d = new Demo();
		DemoD DD = new DemoD();
		EventBus eventBus = new EventBus();
		eventBus.register(DD);
//		eventBus.post();
		System.out.println("Test");
	}
}
