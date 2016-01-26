/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package frogermcs.io.dagger2metrics.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

import frogermcs.io.dagger2metrics.internal.InitManager;

@Aspect
public class GraphAnalyzer {

    public static final String TAG = "Dagger2Metrics";

    @Pointcut("within(@dagger.Module *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(@javax.inject.Inject *.new(..))")
    public void injectConstructor() {
    }

    @Pointcut("execution(@dagger.Provides * *(..)) && withinAnnotatedClass()")
    public void providesMethod() {
    }

    @Around("providesMethod() || injectConstructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        Class<?> cls = codeSignature.getDeclaringType();

        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long stop = System.nanoTime();
        long took = TimeUnit.NANOSECONDS.toMillis(stop - start);

        if (codeSignature instanceof ConstructorSignature) {
            InitManager.getInstance().addInitMetric(cls, joinPoint.getArgs(), took);
        }

        if (isMethodWithReturnType(codeSignature)) {
            InitManager.getInstance().addInitMetric(result.getClass(), joinPoint.getArgs(), took);
        }

        return result;
    }

    private boolean isMethodWithReturnType(CodeSignature codeSignature) {
        return codeSignature instanceof MethodSignature && ((MethodSignature) codeSignature).getReturnType() != void.class;
    }


}
