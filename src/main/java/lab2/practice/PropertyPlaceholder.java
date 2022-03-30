package lab2.practice;


import lab1.practice.MessagePrinter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Класс должен содержать логику подмены значений филдов заданых по умолчанию в контексте.
 * Заменяет строковые значение в бинах типа
 *
 * @see Printer
 * на значения в
 * @see PropertyRepository
 * Использует изначальные значения как ключи для поиска в PropertyRepository
 */

@Component
public class PropertyPlaceholder implements BeanFactoryPostProcessor {
    private PropertyRepository propertyRepository;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> aClass = Class.forName(beanClassName);
                ReplaceWith annotation = aClass.getAnnotation(ReplaceWith.class);
                if (annotation != null) {
                    beanDefinition.setBeanClassName(annotation.value().getCanonicalName());
                    MessagePrinter bean = beanFactory.getBean(MessagePrinter.class);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
