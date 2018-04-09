package org.wildfly.swarm.camunda.bpm.rest.runtime;

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
public class CamundaBpmRestProducer {

  public static final String MODULE = "org.wildfly.swarm.camunda.bpm.swarm.fraction.rest";        //TODO why this module looks like this???

  @Inject
  ArtifactLookup lookup;

  @Produces
  Archive camundaBpmEngineRest() throws Exception {
    String deploymentName = "camunda-engine-rest.war";
    String group = "org.camunda.bpm";
    String artifact = "camunda-engine-rest";
    String classifier = "wildfly";
    String packaging = "war";
    String version = lookupVersion(group, artifact, packaging, classifier);
    String gav = group + ":" + artifact + ":" + packaging + ":" + classifier + ":" + version;

    Archive deployment = this.lookup.artifact(gav, deploymentName);
    System.out.println("deployment " + deployment);

    return deployment;
  }

  /**
   * The version of the WAR should not be hard-coded. The WAR is included in the
   * fraction manifest file as dependency. The default ArtifactLookup only looks
   * into these fraction manifest files in UBERJAR-mode.
   */
  private String lookupVersion(String group, String artifact, String packaging, String classifier) {
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
            if (classifier.equals(depClassifier)) {
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
