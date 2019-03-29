package org.camunda.bpm;

import org.wildfly.swarm.Swarm;

/**
 * @author Svetlana Dorokhova.
 */
public class ExampleMain {

  public static void main(String...args) throws Exception {

    // Instantiate the container
    Swarm swarm = new Swarm();

    //customize Camunda engine configuration
    final CamundaBpmPlatformFraction fraction = configureFraction();

    swarm.fraction(fraction);

    swarm.start();
    swarm.deploy();
  }

  private static CamundaBpmPlatformFraction configureFraction() {
    CamundaBpmPlatformFraction fraction = new CamundaBpmPlatformFraction();
    fraction.applyDefaults();
    fraction.subresources().processEngines("default").historyLevel("full");
    return fraction;
  }

}
