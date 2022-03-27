package lab1.practice;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * класс спрингового пост процессора, должен имплементировать интерфейс
 *
 * @see BeanPostProcessor
 * <p>
 * Класс отвечает за логику инжекта случайного числа в поле проаннотированное, специально обученной аннотацией
 */
public class InjectRandomPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        for (Field declaredField : aClass.getDeclaredFields()) {
            InjectRandomInt annotation = declaredField.getDeclaredAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                Random random = new Random();
                int randomInteger = random.nextInt(annotation.max());
                declaredField.setAccessible(true);
                ReflectionUtils.setField(declaredField, bean, randomInteger);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
