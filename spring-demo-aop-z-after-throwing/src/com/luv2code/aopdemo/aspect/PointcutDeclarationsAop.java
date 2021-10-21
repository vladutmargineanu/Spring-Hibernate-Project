package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDeclarationsAop {

	// Create pointcut declaration

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}

	// create pointcut for getter methods

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	public void getterDao() {
	}

	// create pointcut for setter methods

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	public void setterDao() {
	}

	// create point: include package, but exclude getter/setter

	@Pointcut("forDaoPackage() && !(getterDao() || setterDao())")
	public void forDaoPackageNoGetterSetter() {
	}
}
