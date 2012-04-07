//package br.gov.sample.demoiselle.escola.newfeatures;
//
//import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
//import br.gov.framework.demoiselle.core.layer.integration.Injection;
//import br.gov.framework.demoiselle.core.message.IMessageContext;
//import br.gov.framework.demoiselle.view.faces.Navigation;
//import br.gov.framework.demoiselle.view.faces.controller.AbstractManagedBean;
//
//public class NewFeaturesMB extends AbstractManagedBean {
//	@Injection
//	IMessageContext msgCtx;
//	
////	@Navigation(name="tratamento_no_metodo")
//	public String geraExcecaoMetodo(){
//		if (0==0){
//			throw new ApplicationRuntimeException(NewFeaturesMessage.EXCEPTION_MESSAGE_METHOD);
//		}
//		
//		return "";
//
//	}
//	
//	public String geraExcecaoClasse(){
//		if (0==0){
//			throw new NewFeaturesException(NewFeaturesMessage.EXCEPTION_MESSAGE_CLASS);
//		}
//		return "";
//	}
//
//	public String geraMensagens(){
//		msgCtx.addMessage(NewFeaturesMessage.MESSAGE_CONTEXT_1);
//		msgCtx.addMessage(NewFeaturesMessage.MESSAGE_CONTEXT_2);
//		msgCtx.addMessage(NewFeaturesMessage.MESSAGE_CONTEXT_3);
//		
//		return "geraMensagens";
//	}
//}