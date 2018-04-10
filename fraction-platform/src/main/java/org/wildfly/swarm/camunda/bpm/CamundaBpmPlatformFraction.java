package org.wildfly.swarm.camunda.bpm;

import org.wildfly.swarm.config.CamundaBpmPlatform;
import org.wildfly.swarm.config.camunda.bpm.platform.JobExecutor;
import org.wildfly.swarm.config.camunda.bpm.platform.ProcessEngines;
import org.wildfly.swarm.config.camunda.bpm.platform.job_executor.JobAcquisitions;
import org.wildfly.swarm.spi.api.Fraction;
import org.wildfly.swarm.spi.api.annotations.MarshalDMR;
import org.wildfly.swarm.spi.api.annotations.WildFlyExtension;
import org.wildfly.swarm.spi.api.annotations.WildFlySubsystem;

/**
 * @author Svetlana Dorokhova.
 */
@MarshalDMR
@WildFlyExtension(module = "org.camunda.bpm.wildfly.camunda-wildfly-subsystem")
@WildFlySubsystem("camunda-bpm-platform")
public class CamundaBpmPlatformFraction extends CamundaBpmPlatform<CamundaBpmPlatformFraction>
  implements Fraction<CamundaBpmPlatformFraction> {

  private static final String DEFAULT = "default";

  /**
   * Create the default fraction.
   *
   * @return The configured fraction.
   */
  public static CamundaBpmPlatformFraction createDefaultFraction() {
    CamundaBpmPlatformFraction fraction = new CamundaBpmPlatformFraction();
    return fraction.applyDefaults();
  }

  @Override
  public CamundaBpmPlatformFraction applyDefaults() {
    jobExecutor(
      new JobExecutor<>(DEFAULT)
        .jobAcquisitions(
          new JobAcquisitions<>(DEFAULT)
            .name(DEFAULT)
            .property("lockTimeInMillis", "300000")
            .property("waitTimeInMillis", "5000")
            .property("maxJobsPerAcquisition", "3"))
        .coreThreads(3)
        .maxThreads(5)
        .queueLength(10));

    processEngines(
      new ProcessEngines<>(DEFAULT)
        .name(org.camunda.bpm.engine.ProcessEngines.NAME_DEFAULT)
        .attributeDefault(Boolean.TRUE)
        .datasource("java:jboss/datasources/ProcessEngine")
        .historyLevel("full")
        .property("jobExecutorAcquisitionName", "default")
        .property("isAutoSchemaUpdate", "true")
        .property("authorizationEnabled", "true")
        .property("jobExecutorDeploymentAware", "true")
        .property("historyCleanupBatchWindowStartTime", "00:01")
    );
    return this;
  }

}
