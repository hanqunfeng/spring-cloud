package org.pyf.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启feign功能
@EnableFeignClients
//以下是为了使用turbine聚合监控才引入的hystrix和hystrix-dashboard
//开启断路器功能
@EnableHystrix
//开启断路器仪表盘功能
@EnableHystrixDashboard
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}
}
