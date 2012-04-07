//package br.gov.sample.demoiselle.escola.newfeatures;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import org.apache.log4j.Logger;
//
//import br.gov.framework.demoiselle.core.context.ContextLocator;
//import br.gov.framework.demoiselle.core.transaction.ITransactionContext;
//import br.gov.framework.demoiselle.persistence.JPATransactionResource;
//import br.gov.framework.demoiselle.persistence.PersistenceException;
//import br.gov.framework.demoiselle.persistence.transaction.DefaultTransactionContextHandler;
//import br.gov.framework.demoiselle.persistence.transaction.ITransactionContextHandler;
//
//public class NewFeaturesHandlerEntityManager implements ITransactionContextHandler {
//
//	private static final long serialVersionUID = 1L;
//
//	private static Logger log = Logger.getLogger(DefaultTransactionContextHandler.class);
//
//	public EntityManager handler(String persistenceUnit, EntityManagerFactory emFactory) {
//		log.debug("NewFeaturesHandlerEntityManager - Retrieving an entity manager" + ("".equals(persistenceUnit) ? "" : " ["+persistenceUnit+"]"));
//
//		ITransactionContext ctx = ContextLocator.getInstance().getTransactionContext();
//
//		JPATransactionResource res;
//		
//		if (ctx.hasResource()) {
//			if (ctx.getResource() instanceof JPATransactionResource) {
//				res = ((JPATransactionResource) ctx.getResource());
//				String activePersistenceUnit = res.getPersistenceUnit();
//				if (!persistenceUnit.equals(activePersistenceUnit)) {
//					throw new PersistenceException(
//							"Fail on creating transaction resource for persistence unit ["
//									+ persistenceUnit
//									+ "], there is already another for ["
//									+ activePersistenceUnit + "]");
//				}
//			} else {
//				throw new PersistenceException(
//						"It is necessary that the TransactionContext has a "
//								+ JPATransactionResource.class.getName()
//								+ ", but was found a "
//								+ ctx.getResource().getClass().getName());
//			}
//		} else {
//			res = new JPATransactionResource(persistenceUnit, emFactory.createEntityManager());
//			ctx.setResource(res);
//		}
//
//		log.debug("NewFeaturesHandlerEntityManager - End of Handler");
//
//		return res.getEntityManager();
//	}
//
//}
