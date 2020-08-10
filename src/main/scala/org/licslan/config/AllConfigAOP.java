package org.licslan.config;

import org.licslan.aop.LogAspects;
import org.licslan.aop.MathCount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author LICSLAN  AOP原理理解（debug源码调试理解）
 * AOP:指的是程序运行期间动态的将某段代码切入到指定方法指定位置进行编程方式【动态代理】
 *
 * 1.导入aop模块
 * 2.定义一个业务逻辑类C:\Users\licslan\Desktop\scala\licslan-scala\src\main\scala\org\licslan\aop\MathCount.java
 * 实现在计算过程之前打印日志/计算之后打印日志/计算异常.....
 * 3.定义一个日志切面类，切面里面的方法需要动态感知 div 运行到哪一步了 然后执行相应的方法
 *  --  前置(@Before)/后置(@After)/返回(@AfterReturning)/异常(@AfterThrowing)/环绕通知 JoinPoint.procced()(@Around).....
 *  4.给切面类的目标方法标注何时何地运行（通知注解）
 *  5.将切面类和业务逻辑类（目标所在类）都加入到容器中
 *  6.告诉spring容器中哪一个是切面类（给切面类加注解@Aspect）
 *  7.给配置类加 @EnableAspectJAutoProxy 注解 开启基于注解的aop模式
 *
 *
 *
 *  a,业务类和切面类加入容器中  告诉spring哪个是切面类
 *  b,在切面类标明注解 切入点表达式
 *  c,开启基于注解活着xml配置的 aop模式
 *
 *
 *
 *  AOP 原理：【主要看给容器注册了什么组件，这个组件主要干什么，什么时候工作？】
 *      -- @EnableAspectJAutoProxy
 *      -- @Import(AspectJAutoProxyRegistrar.class)
 *      -- class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar  ImportBeanDefinitionRegistrar接口 可以给容器自定义注册组件
 *
 *      利用AspectJAutoProxyRegistrar自定义给容器中注册bean
 *      internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *      debug源码下去 发现要给容器注册一个AnnotationAwareAspectJAutoProxyCreator
 *  AnnotationAwareAspectJAutoProxyCreator
 *  AnnotationAwareAspectJAutoProxyCreator extends
 *  AspectJAwareAdvisorAutoProxyCreator extends
 *  AbstractAdvisorAutoProxyCreator  extends AbstractAutoProxyCreator extends
 *  ProxyProcessorSupport implements SmartInstantiationAwareBeanPostProcessor（后置处理器）, BeanFactoryAware（set beanFactory 创建bean工厂）
 *
 *  请关注后置处理器（bean初始化完成前后做的事情）
 *  自动装配 beanFactory
 *
 *  AbstractAutoProxyCreator.setBeanFactory()  bean工厂
 *  AbstractAutoProxyCreator.postProcessBeforeInitialization()  后置处理逻辑
 *  AbstractAutoProxyCreator.postProcessAfterInstantiation()    后置处理逻辑
 *
 *
 *
 * public AnnotationConfigApplicationContext(Class<?>... componentClasses) {
 * 		this();
 * 		register(componentClasses);
 * 		refresh();
*        }
 *  流程：
 *  1.传入配置类，创建ioc容器
 *  2.注册配置类 调用refresh() 刷新容器
 *  3.registerBeanPostProcessor(beanFactory) 注册bean的后置处理器来方便拦截bean的创建
 *    1.-- 先获取ioc容器已经定义的需要创建对象的所有BeanPostProcessor  只是一些定义  还没有创建对象
 *    2.-- 给容器加别的BeanPostProcessor
 *    3.-- 优先注册实现了priorityOrdered接口的BeanPostProcessor
 *    4.-- 再给容器中注册实现了Ordered接口的BeanPostProcessor
 *    5.-- 最后给容器注册没有实现优先级接口的BeanPostProcessor
 *    6.-- 注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象 保存在容器中
 *        -- 如何创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]？
 *          1.创建bean实例
 *          2.populatebean,给bean的各种属性赋值
 *          3.initializeBean初始化bean  后置处理器就是初始化bean前后工作的
 *              1).invokeAwareMethods() 处理Aware接口的方法回调
 *              2).applyBeanPostProcessorsBeforeInitialization(),应用后置处理器的postProcessBeforeInitialization()
 *              3).invokeInitMethods() 执行自定义的初始化方法
 *              4).applyBeanPostProcessorsAfterInitalization(),应用后置处理器的postProcessAfterInitialization()
 *          4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功  aspectJAdvisorBuilder
 *    7.-- 把BeanPostProcessor注册到BeanFactory中  beanFactory.addBeanPostProcessor(postProcessor)
 *
 *   =======================以上步骤是创建和注册AnnotationAwareAspectJAutoProxyCreator（它是一个后置处理器）的步骤过程
 *   AnnotationAwareAspectJAutoProxyCreator 是这种类型的后置处理器  InstaniationAwareBeanPostProcessor
 *   4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例bean
 *      1).遍历获取容器中的所有bean，依次创建对象getBean(beanName) getBean -->doGetBean() -->getSingleton()
 *      2).创建bean
 *             AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截 InstantiationAwareBeanPostProcessor会调用 postProcessorBeforeInstantiation()
 *          --1.先从缓存中获取当前bean 如果能获取到说明bean是之前被创建过的，直接使用，否则再创建。只要创建好的bean都会被缓存起来的
 *          --2.createBean() 创建bean  AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean的实例
 *               beanPostProcessor是在bean对象创建完成初始化前后调用
 *               InstantiationAwareBeanPostProcessor是在创建bean实例之前先尝试使用后置处理器返回对象的
 *              --1.resolveBeforeInstantiation(beanName，mbdToUse) 解析 BeforeInstantiation 希望后置处理器此前返回一个代理对象，如果能返回代理对象就使用，如果不能就能就继续
 *                  --1.后置处理器先尝试返回对象
 *                      bean= applyBeanPostProcessorBeforeInstantiation() 拿到所有后置处理器，如是InstatiationAwareBeanPostProcessor
 *                      if(bean!=null){bean= applyBeanPostProcessorAfterInstantiation()}
 *
 *              --2.doCreatBean(beanName,mbdToUse,args);真正的创建一个bean实例 与上面的3.6流程一样的
 *
 * AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截 InstantiationAwareBeanPostProcessor 的作用
 * 1.每一个bean创建之前，调用postProcessorBeforeInstantiation();
 *   我们关心 LogAspects && MathCount 的创建
 *   --1.判断当前bean是否在advisedBeans中（保存了所有需要增强bean）
 *   --2.判断当前bean是否是基础类型的Advice,pointcut,Advisor,AopInfrastructureBean或者是否是切面（@Apsect）
 *   --3.是否需要跳过
 *      --1.获取候选的增强器（切面里面的通知方法） List<Advisor> candidateAdvisors
 *        每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor,判断每一个增强器是否是ApsetcJPointcutAdvisor类型（是的话返回true）
 *     --2.永远返回false
 * 2.创建对象
 * postProcessorAfterInitialization
 *      return warpIfNecessary(bean,beanName,cacheKey); 如果需要的情况下
 *      --1.获取当前bean的所有增强器（通知方法）
 *          --1.找到能在当前bean使用的增强器（找到哪些方法是需要切入当前bean方法的）
 *          --2.获取到能在bean使用的增强器
 *          --3.给增强器排序
 *     --2.保存当前bean在advisedBeans中
 *     --3.如果当前bean需要增强，创建当前bean的代理对象
 *          --1.获取所有增强器（通知方法）
 *          --2.保存到proxyFactory
 *          --3.创建代理对象 spring自动决定创建那种代理对象  JDK动态代理  和  cglib动态代理
 *              JDKDynamicAopProxy(config);
 *              ObjenesisCglibAopProxy(config);
 *     --4.给容器中返回当前组件使用cglib增强了的代理对象
 *     --5.以后容器中获取到的就是这个组件的代理对象了，执行目标方法的时候，代理对象就会执行通知方法的流程
 * 3.目标方法的执行 容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存详细信息 增强器，目标对象
 *   --1.CglibAopProxy.intercept();拦截目标方法的执行
 *   --2.根据PorxyFactory对象huo'qu将要执行的目标方法拦截链
 *         List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdice()
 *         --1.List<Object> interceptorList保存s所有拦截器5 一个默认的ExposeInvocationInterceptor 和4个增强器
 *         --2.遍历所有的增强器，将其转为Interceptor;  registry.getInterceptor(advisor)
 *         --3.将增强器转为List<MethodInterceptor> 如果是MethodInterceptor,直接加入集合中 如果不是 使用AdvisorAdapter将增强器转为 MethodInterceptor,返回MethodInterceptor数组
 *   --3.如果没有拦截器链，直接执行目标方法 （拦截器链 每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *   --4.如果有拦截器链，把需要执行的目标对象，目标方法，拦截器链等信息传入创建一个CglibMethodInvocation对象 并调用Object retVal=mi.proceed();
 *   --5.拦截器链的触发过程
 *      1)、如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法；
 *      2)、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 *   				拦截器链的机制，保证通知方法与目标方法的执行顺序；
*
 *
 *   总结：
 *  * 		1）、  @EnableAspectJAutoProxy 开启AOP功能
 *  * 		2）、 @EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
 *  * 		3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器；
 *  * 		4）、容器的创建流程：
 *  * 			1）、registerBeanPostProcessors（）注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象
 *  * 			2）、finishBeanFactoryInitialization（）初始化剩下的单实例bean
 *  * 				1）、创建业务逻辑组件和切面组件
 *  * 				2）、AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *  * 				3）、组件创建完之后，判断组件是否需要增强
 *  * 					是：切面的通知方法，包装成增强器（Advisor）;给业务逻辑组件创建一个代理对象（cglib）；
 *  * 		5）、执行目标方法：
 *  * 			1）、代理对象执行目标方法
 *  * 			2）、CglibAopProxy.intercept()；
 *  * 				1）、得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *  * 				2）、利用拦截器的链式机制，依次进入每一个拦截器进行执行；
 *  * 				3）、效果：
 *  * 					正常执行：前置通知-》目标方法-》后置通知-》返回通知
 *  * 					出现异常：前置通知-》目标方法-》后置通知-》异常通知
 *  *
 *
 *
 * */
@Configuration
@EnableAspectJAutoProxy//开启注解
public class AllConfigAOP {

    //将业务逻辑类加入到容器中
    @Bean
    public MathCount mathCount(){
        return new MathCount();
    }

    //将切面类也加入容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
