package org.camunda.bpm.pizza;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Stateless
@Named
public class OrderBusinessLogic {

	private static Logger LOGGER = Logger.getLogger(OrderBusinessLogic.class.getName());

	public void rejectOrder(DelegateExecution delegateExecution) {
		String customer = (String) delegateExecution.getVariable("customer");
		String pizza = (String) delegateExecution.getVariable("pizza");
		LOGGER.log(Level.INFO, "\n\n\nSending Email:\nDear {0}, your order of a {1} pizza has been rejected.\n\n\n",
				new String[] { customer, pizza });
	}
}
