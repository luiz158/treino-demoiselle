package contactlist.pages;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import contactlist.Bind;
import contactlist.tests.TestDriver;
 
abstract class EditPage<E> extends Page {
 
	void setBean(E entity) {
		Field[] fields = entity.getClass().getDeclaredFields()
		for(Field field:fields) {
			if (field.isAnnotationPresent(Bind.class)) {
				Bind webElement = field.getAnnotation(Bind.class)
				try {
					WebElement element = TestDriver.getDriver().findElement(By.xpath(webElement.xpath()))
					element.clear()

					def accessible = field.isAccessible()
					field.setAccessible(true)
					element.sendKeys(field.get(entity).toString())
					field.setAccessible(accessible)
				} catch (IllegalArgumentException e) {
					e.printStackTrace()
				} catch (IllegalAccessException e) {
					e.printStackTrace()
				} catch (NoSuchElementException e) {
					continue
				}
			}
		}
	}

}
