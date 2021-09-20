README

This examples shows how to destroy prototype scoped beans. A couple of changes are required.

1. Create a custom bean processor. This bean processor will keep track of prototype scoped beans. During shutdown it will call the destroy() method on the prototype scoped beans. The custom processor is configured in the spring config file.

	<!-- Bean custom processor to handle calling destroy methods on prototype scoped beans -->
    <bean id="customProcessor"
    		class="com.luv2code.springdemo.MyCustomBeanProcessor">
   	</bean>


2. The prototype scoped beans MUST implement the DisposableBean interface. This interface defines a "destory()" method. This method should be used instead of the @PostDestroy annotation.

public class TrackCoach implements Coach, DisposableBean {

	...
	
	// add a destroy method
	@Override
	public void destroy() throws Exception {
		System.out.println("TrackCoach: inside method doMyCleanupStuffYoYo");		
	}

}


3. The spring configuration must be updated to use the destroy-method of "destroy". 

 	<bean id="myCoach"
 		class="com.luv2code.springdemo.TrackCoach"
 		init-method="doMyStartupStuff"
 		destroy-method="destroy"
 		scope="prototype">	
 		
 		<!-- set up constructor injection -->
 		<constructor-arg ref="myFortuneService" />
 	</bean>


4. In this app, BeanLifeCycleDemoApp.java is the main program.  TrackCoach.java is the prototype scoped bean. TrackCoach implements the DisposableBean interface and provides the destroy() method. The custom bean processing is handled in the MyCustomBeanProcessor class.

