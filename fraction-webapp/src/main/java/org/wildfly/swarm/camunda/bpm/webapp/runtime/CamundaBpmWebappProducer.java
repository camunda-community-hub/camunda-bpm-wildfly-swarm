package org.wildfly.swarm.camunda.bpm.webapp.runtime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.jboss.shrinkwrap.api.Archive;
import org.wildfly.swarm.bootstrap.env.ApplicationEnvironment;
import org.wildfly.swarm.bootstrap.env.FractionManifest;
import org.wildfly.swarm.spi.api.ArtifactLookup;

/**
 * @author Svetlana Dorokhova.
 */
@ApplicationScoped
public class CamundaBpmWebappProducer {

  public static final String MODULE = "org.camunda.bpm.camunda.bpm.swarm.fraction.webapp";

  @Inject
  ArtifactLookup lookup;

  @Produces
  Archive camundaBpmEngineRest() throws Exception {
    String deploymentName = "camunda-webapp.war";
    String group = "org.camunda.bpm.webapp";
    String artifact = "camunda-webapp-jboss";
    String packaging = "war";
    String version = lookupVersion(group, artifact, packaging);
    String gav = group + ":" + artifact + ":" + packaging + ":" + version;

    Archive deployment = this.lookup.artifact(gav, deploymentName);

    return deployment;
  }

  /**
   * The version of the WAR should not be hard-coded. The WAR is included in the
   * fraction manifest file as dependency. The default ArtifactLookup only looks
   * into these fraction manifest files in UBERJAR-mode.
   */
  private String lookupVersion(String group, String artifact, String packaging) {
    FractionManifest fractionManifest = getFractionManifest();

    for (String dep : fractionManifest.getDependencies()) {
      String[] parts = dep.split(":");

      String depGroupId = parts[0];
      String depArtifactId = parts[1];
      String depPackaging = parts[2];
      String depVersion = null;
      String depClassifier = null;
      if (parts.length == 4) {
        depVersion = parts[3];
      } else {
        depClassifier = parts[3];
        depVersion = parts[4];
      }

      if (group.equals(depGroupId)) {
        if (artifact.equals(depArtifactId)) {
          if (packaging.equals(depPackaging)) {
            if (depClassifier == null) {
              return depVersion;
            }
          }
        }
      }
    }

    throw new RuntimeException("Failed to lookup version.");
  }

  private FractionManifest getFractionManifest() {
    FractionManifest fractionManifest = ApplicationEnvironment.get()
      .fractionManifests()
      .stream()
      .filter(mf -> MODULE.equals(mf.getModule()))
      .findFirst()
      .get();
    return fractionManifest;
  }

}
