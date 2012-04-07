package contactlist.util;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class BundleUtil {

	@Inject
	private ResourceBundle defaultBundle;

	@Inject
	@Name("messages-core")
	private ResourceBundle coreBundle;

	private static BundleUtil instance = Beans.getReference(BundleUtil.class);

	public static ResourceBundle getCore() {
		return instance.coreBundle;
	}

	public static ResourceBundle getDefault() {
		return instance.defaultBundle;
	}
}
