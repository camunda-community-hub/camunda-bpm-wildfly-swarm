package org.wildfly.swarm.camunda.bpm.runtime;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Svetlana Dorokhova.
 */
//@Post
@ApplicationScoped
public class CustomizeCamundaConfiguration {
//public class CustomizeCamundaConfiguration implements Customizer {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeCamundaConfiguration.class);

//  @Inject
//  private @Named("CamundaBpmnEngineFraction") CamundaBpmnEngineFraction camundaBpmnEngineFraction;
//  private CamundaBpmnEngineFraction camundaBpmnEngineFraction;

//  @Inject
//  BeanManager beanManager;
//
//  @Inject
//  private ProcessEngine processEngine;

//  @PostConstruct
  public void customize() {
//    final ProcessEngines processEngines = camundaBpmnEngineFraction.subresources().processEngines("default").historyLevel("none");
//    LOGGER.info("History level " + processEngine.getProcessEngineConfiguration().getHistory());
//    System.out.println("History level " + processEngine.getProcessEngineConfiguration().getHistory());
//    Set<Bean<?>> beans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {
//    });
//    for (Bean<?> bean : beans) {
//      LOGGER.info(bean.getBeanClass().getName());
//    }
  }
}
